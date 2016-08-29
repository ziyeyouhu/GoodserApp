package co.sspp.goodserapp.home.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.txusballesteros.widgets.FitChart;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.sspp.goodserapp.R;

/**
 * User: ZiYeYouHu
 * Date: 2016-07-19
 * Time: 19:34
 * Des:
 * FIXME
 */

public class OkhttpFragment extends Fragment {
    @BindView(R.id.fitChart)
    FitChart mFitChart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zichan, container, false);
        ButterKnife.bind(this, view);

        mFitChart.setMinValue(0f);
        mFitChart.setMaxValue(10000f);
        mFitChart.setValue(8000f);
        System.out.println("1创建");
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("1销毁");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("1销毁view");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("1可见");
    }
}
