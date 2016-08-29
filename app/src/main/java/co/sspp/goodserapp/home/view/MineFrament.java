package co.sspp.goodserapp.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.sspp.goodserapp.BaseFragment;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.utils.AutoUtils;
import co.sspp.goodserapp.weight.WaveView;

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


    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_mine1, null);
        AutoUtils.auto(view);
        ButterKnife.bind(this, view);

        mWaveView.startWaveLine();

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
}

