package co.sspp.goodserapp.module.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.base.BaseFragment;
import co.sspp.goodserapp.module.login.UI.AppLoginActivity;
import co.sspp.goodserapp.rx.rxbus.RxBus;
import co.sspp.goodserapp.utils.AutoUtils;
import co.sspp.goodserapp.weight.WaveView;
import rx.functions.Action1;

/**
 * User: ZiYeYouHu
 * Date: 2016-03-11
 * Time: 14:37
 * FIXME
 */

public class MineFrament extends BaseFragment {
    private static final String TAG = "MineFrament";
    @BindView(R.id.waveView)
    WaveView mWaveView;

    @BindView(R.id.head)
    ImageView mHead;


    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_mine1, null);
        AutoUtils.auto(view);
        ButterKnife.bind(this, view);

        mWaveView.startWaveLine();




        RxBus.getDefault()
                .toObserverable(String.class)
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mHead.setVisibility(View.GONE);

            }
        });


        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWaveView.stopWaveLine();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.head)
    public void onClick() {
        gotoActivity(AppLoginActivity.class);

    }
}

