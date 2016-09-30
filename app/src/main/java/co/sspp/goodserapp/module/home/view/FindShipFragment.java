package co.sspp.goodserapp.module.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.base.BaseFragment;
import co.sspp.goodserapp.module.SelectGoodsActivity;
import co.sspp.goodserapp.module.home.FindShipDetails;
import co.sspp.goodserapp.module.home.domain.DoFindshipInfo;
import co.sspp.goodserapp.rx.RxHelper;
import co.sspp.goodserapp.rx.RxSubscribe;
import co.sspp.goodserapp.rx.api.Api;
import co.sspp.goodserapp.rx.cache.RxRetrofitCache;
import co.sspp.goodserapp.weight.HidingScrollListener;
import rx.Observable;


/**
 * User: ZiYeYouHu
 * Date: 2016-03-11
 * Time: 14:35
 * FIXME
 */

public class FindShipFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        MultiItemTypeAdapter.OnItemClickListener {


    private static final String TAG = "FindShipFragment";
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.textView4)
    TextView mTextView4;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.btn_pushgoods)
    Button btn_pushgoods;
    @BindView(R.id.botton_bar)
    RelativeLayout mBottonBar;



    private CommonAdapter mAdapter;


    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.g_fragment_findship, null);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setOnRefreshListener(FindShipFragment.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                showViews();
            }

            @Override
            public void onShow() {
                hideViews();
            }
        });


        hideViews();



        btn_pushgoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(SelectGoodsActivity.class);
            }
        });

        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        loadList();
    }

    /**
     * "Page", pageNu
     * "PageSize", pa
     * "MobileLogKey"
     */
    private void loadList() {


        Observable<ArrayList<DoFindshipInfo>> compose = Api.getDefault()
                .findShip(9999, 1)
                .compose(RxHelper.<ArrayList<DoFindshipInfo>>handleResult());

        RxRetrofitCache.load(context, "cacheKey", 992991, compose, false)
                .subscribe(new RxSubscribe<ArrayList<DoFindshipInfo>>(getActivity()) {

                               @Override
                               protected boolean showDialog() {
                                   return false;
                               }


                               @Override
                               protected void _onNext(ArrayList<DoFindshipInfo> doFindshipInfos) {
                                   setAdapter(doFindshipInfos);

                                   Log.e(TAG, doFindshipInfos.toString());

                               }

                               @Override
                               protected void _onError(String message) {
                                   Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                               }


                           }

                );


    }

    /**
     * init  Adapter
     *
     * @param doFindshipInfos
     */
    private void setAdapter(ArrayList<DoFindshipInfo> doFindshipInfos) {

        mAdapter = new CommonAdapter<DoFindshipInfo>(getActivity()
                , R.layout.g_item_findship_listview, doFindshipInfos) {
            @Override
            protected void convert(ViewHolder holder, DoFindshipInfo doFindshipInfo, int position) {

                holder.setText(R.id.name, doFindshipInfo.getShipName())
                        .setText(R.id.loaddun, doFindshipInfo.getLoadTon() + "吨")
                        .setText(R.id.data, doFindshipInfo.getEditDate())
                        .setText(R.id.empty_port, doFindshipInfo.getLoadDate() + "日-在"
                                + doFindshipInfo.getEmptyPort() + "空船");

                CheckBox cb = holder.getView(R.id.cb);

                cb.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    doFindshipInfo.setChecked(isChecked);
                    Observable.from(doFindshipInfos)
                            .filter(DoFindshipInfo::isChecked)
                            .toList()
                            .map(List::size)
                            .subscribe((Integer integer) -> {

                                mTvCount.setText(integer.toString());
                                if (integer >= 1) showViews();
                                else hideViews();
                            });

                });


                cb.setChecked(doFindshipInfo.isChecked());

            }

        };

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> {
            mSwipeRefreshLayout.setRefreshing(false);
            loadList();

        }, 2000);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    private void hideViews() {

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mBottonBar.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        mBottonBar.animate().translationY(mBottonBar.getHeight() + fabBottomMargin)
                .setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        mBottonBar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }


    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

        startActivity(new Intent(getActivity(), FindShipDetails.class));
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }


}

