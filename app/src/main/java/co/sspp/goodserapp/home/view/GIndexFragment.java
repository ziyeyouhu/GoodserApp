package co.sspp.goodserapp.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
import co.sspp.goodserapp.weight.tq.SunView;
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
    @BindView(R.id.loc)
    TextView mLoc;
    @BindView(R.id.tqi)
    TextView mTqi;
    @BindView(R.id.sunview)
    SunView mSunview;
    @BindView(R.id.skyicons)
    FrameLayout mSkyicons;


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


//    private void setWeatherValue() {
//        Weather weather = WeatherProxy.getTodayWeather();
//        if (weather != null) {
//            JSONObject weatherInfo = weather.weather_data;
////            titleCity.setText(weather.currentCity + "   " + weatherInfo.optString("weather"));
////            titleWeather.setText(weatherInfo.optString("wind") + "   " + weatherInfo.optString("temperature"));
//
//            mSkyicons.removeAllViews();
//
//            String viewParam = weatherInfo.optString("weather");
//            WeatherTemplateView weatherTemplateView ;
//            Time time = new Time();
//            time.setToNow();
//
//            Context context = this.getActivity();
//
//            if ("晴".equals(viewParam)) {
//                if (time.hour > 19 || time.hour < 5) {
//                    weatherTemplateView = new MoonView(context);
//                } else {
//                    weatherTemplateView = new SunView(context);
//                }
//            } else if ("多云".equals(viewParam)) {
//                weatherTemplateView = new CloudView(context);
//            } else if ("雷阵雨".equals(viewParam) || "雷阵雨伴有冰雹".equals(viewParam)) {
//                weatherTemplateView = new CloudThunderView(context);
//            } else if ("小雨".equals(viewParam) || "中雨".equals(viewParam) || "小雨转中雨".equals(viewParam)) {
//                weatherTemplateView = new CloudRainView(context);
//            } else if (viewParam != null && viewParam.contains("雪")) {
//                weatherTemplateView = new CloudSnowView(context);
//            } else if (viewParam != null && (viewParam.contains("大雨") || viewParam.contains("暴雨"))) {
//                weatherTemplateView = new CloudHvRainView(context);
//            } else {
//                weatherTemplateView = new WindView(context);
//            }
//
//            if (weatherTemplateView != null) {
//                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
//                mSkyicons.addView(weatherTemplateView, layoutParams);
//            }
//
//        } else {
//            Object object = mTqi.getTag();
//            if (object == null) {
//                mTqi.setTag(1);
//                object = 1;
//            } else {
//                object = ((Integer) object) + 1;
//                mTqi.setTag(object);
//            }
//            if ((Integer) object < 5) {
//                mHandler.postDelayed(() -> setWeatherValue(), 5000);
//            }
//        }
//    }
}

