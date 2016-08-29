package co.sspp.goodserapp.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.sspp.goodserapp.BaseFragment;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.home.FindShipDetails;
import co.sspp.goodserapp.home.domain.DoFindshipInfo;
import co.sspp.goodserapp.rx.Api;
import co.sspp.goodserapp.rx.RxHelper;
import co.sspp.goodserapp.rx.RxRetrofitCache;
import co.sspp.goodserapp.rx.RxSubscribe;
import co.sspp.goodserapp.weight.HidingScrollListener;
import rx.Observable;


/**
 * User: ZiYeYouHu
 * Date: 2016-03-11
 * Time: 14:35
 * FIXME
 */

public class FindShipFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


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
    Button mBtnPushgoods;
    @BindView(R.id.botton_bar)
    RelativeLayout mBottonBar;


    private FindgoodsAdapter mFindgoodsAdapter;
    private int mTotalPage = 111;
    int page = 1;
    private ArrayList<DoFindshipInfo> mData = new ArrayList<>();

    private ArrayList<DoFindshipInfo> mFactData = new ArrayList<>();


    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.g_fragment_findship, null);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setOnRefreshListener(FindShipFragment.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });

        initAdapter();

        hideViews();


        mFindgoodsAdapter.setOnRecyclerViewItemClickListener((view1, position) ->
                startActivity(new Intent(getActivity(), FindShipDetails.class)));

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
                .findShip(10000, 1)
                .compose(RxHelper.<ArrayList<DoFindshipInfo>>handleResult());

        RxRetrofitCache.load(context, "cacheKey", 1, compose, false)
                .subscribe(new RxSubscribe<ArrayList<DoFindshipInfo>>(getActivity()) {

                    @Override
                    protected boolean showDialog() {
                        return false;
                    }


                    @Override
                    protected void _onNext(ArrayList<DoFindshipInfo> doFindshipInfos) {


                        mFactData.addAll(doFindshipInfos);
                        mFindgoodsAdapter.notifyDataChangedAfterLoadMore(mFactData, true);
                    }

                    @Override
                    protected void _onError(String message) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                });


    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> {
            mSwipeRefreshLayout.setRefreshing(false);
            loadList();


        }, 1000);
    }

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.post(() -> {
            if (page >= mTotalPage) {
                mFindgoodsAdapter.notifyDataChangedAfterLoadMore(false);
                View view = getLayoutInflater(new Bundle()).inflate(R.layout.not_loading,
                        (ViewGroup) mRecyclerView.getParent(), false);
                mFindgoodsAdapter.addFooterView(view);

                View view2 = getLayoutInflater(new Bundle()).inflate(R.layout.def_loading,
                        (ViewGroup) mRecyclerView.getParent(), false);
                mFindgoodsAdapter.setLoadingView(view2);


            } else {
                new Handler().postDelayed(() -> {
//                    page+=1;
                    loadList();

                }, 800);
            }
        });
    }

    private void initAdapter() {
        mFindgoodsAdapter = new FindgoodsAdapter(mData);
//        mFindgoodsAdapter.openLoadAnimation(BaseQuickAdapter.);
        mFindgoodsAdapter.isFirstOnly(false);
        mRecyclerView.setAdapter(mFindgoodsAdapter);
        mFindgoodsAdapter.setOnLoadMoreListener(this);
        mFindgoodsAdapter.openLoadMore(page, true);
        mFindgoodsAdapter.setOnRecyclerViewItemClickListener((view, position) -> {

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        page = 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    private void hideViews() {
//        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mBottonBar.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        mBottonBar.animate().translationY(mBottonBar.getHeight() + fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
//        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        mBottonBar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }


    /**
     * Findgoods Adapter
     */
    class FindgoodsAdapter extends BaseQuickAdapter<DoFindshipInfo> {

        public FindgoodsAdapter(ArrayList<DoFindshipInfo> a) {
            super(R.layout.g_item_findship_listview, a);
        }


        @Override
        protected void convert(BaseViewHolder helper, DoFindshipInfo item) {
            //        helper.setText(R.id.tweetName, item.getUserName())
//                .setText(R.id.tweetText, item.getText())
//                .setText(R.id.tweetDate, item.getCreatedAt())
//                .setVisible(R.id.tweetRT, item.isRetweet())
//                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
//                .setOnClickListener(R.id.tweetName, new OnItemChildClickListener())
//                .linkify(R.id.tweetText);

//        helper.setText(R.id.tv_findgoods_loaddate, item.getLoadDate())
//                .setText(R.id.tv_findgoods_startport,item.getStartPort());

            helper.setText(R.id.name, item.getShipName())
                    .setText(R.id.loaddun, item.getLoadTon()+"吨")
                    .setText(R.id.data, item.getEditDate())
                    .setText(R.id.empty_port,item.getLoadDate()+"日-在"+item.getEmptyPort()+"空船");


            CheckBox cb = helper.getView(R.id.cb);

            cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
                item.setChecked(isChecked);


                Observable.from(mFactData)
                        .filter(DoFindshipInfo::isChecked)
                        .toList()
                        .map(List::size)
                        .subscribe((Integer integer) -> {

                            mTvCount.setText(integer.toString());
                            if (integer >= 1) showViews();
                            else hideViews();
                        });

            });


            cb.setChecked(item.isChecked());

        }


    }
}

