package co.sspp.goodserapp.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import co.sspp.goodserapp.BaseFragment;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.home.domain.DoBannerInfo;
import co.sspp.goodserapp.rx.Api;
import co.sspp.goodserapp.rx.RxHelper;
import co.sspp.goodserapp.rx.RxSubscribe;
import co.sspp.goodserapp.utils.AutoUtils;
import rx.Observable;

/**
 * User: ZiYeYouHu
 * Date: 2016-03-11
 * Time: 14:34
 * FIXME
 */

public class GIndexFragment extends BaseFragment {
    private static final String TAG = "GIndexFragment";
    @BindView(R.id.banner_main)
    BGABanner mBannerMain;



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

