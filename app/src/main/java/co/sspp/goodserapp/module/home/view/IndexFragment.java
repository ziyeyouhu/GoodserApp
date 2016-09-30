package co.sspp.goodserapp.module.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.base.BaseFragment;
import co.sspp.goodserapp.module.home.domain.DoBannerInfo;
import co.sspp.goodserapp.rx.RxHelper;
import co.sspp.goodserapp.rx.RxSubscribe;
import co.sspp.goodserapp.rx.api.Api;
import co.sspp.goodserapp.utils.AutoUtils;
import co.sspp.goodserapp.weight.ListView4ScrollView;
import rx.Observable;

/**
 * User: ZiYeYouHu
 * Date: 2016-03-11
 * Time: 14:34
 * FIXME
 */

public class IndexFragment extends BaseFragment {
    private static final String TAG = "IndexFragment";
    @BindView(R.id.banner_main)
    BGABanner mBannerMain;
    @BindView(R.id.lv_tuijian)
    ListView4ScrollView mLvTuijian;
    @BindView(R.id.lv_zuixin)
    ListView4ScrollView mLvZuixin;



    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_index, null);
        ButterKnife.bind(this, view);

        AutoUtils.auto(view);


        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        loadBanner();


//        mStateLayout.showProgressView();
//        mStateLayout.setErrorAction(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mStateLayout.showContentView();
//            }
//        });
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mStateLayout.showErrorView();
//            }
//        },2000);


        List<String> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(i + "");
        }


        mLvTuijian.setAdapter(new CommonAdapter<String>
                (context, R.layout.home_item_tuijian, list) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {

            }
        });


        mLvZuixin.setAdapter(new CommonAdapter<String>
                (context, R.layout.home_item_zuixin, list) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {

            }
        });

    }

    /**
     * banner
     */
    private void loadBanner() {
        Api.getDefault()
                .banners()
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscribe<ArrayList<DoBannerInfo>>(getActivity()) {
                    @Override
                    protected boolean showDialog() {
                        return false;
                    }

                    @Override
                    protected void _onNext(ArrayList<DoBannerInfo> doBannerInfos) {
                        faction(doBannerInfos);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });


    }

    /**
     * @param doBannerInfos
     */
    private void faction(ArrayList<DoBannerInfo> doBannerInfos) {

        mBannerMain.setAdapter((banner, view, model, position) -> Glide.with(context)
                .load(model)
                .centerCrop()
                .into((ImageView) view));

        Observable.from(doBannerInfos)
                .map(DoBannerInfo::getUrl)
                .toList()
                .subscribe(strings -> {
                    mBannerMain.setData(strings, null);
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


}

