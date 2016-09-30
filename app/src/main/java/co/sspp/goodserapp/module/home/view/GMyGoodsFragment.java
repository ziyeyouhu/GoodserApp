package co.sspp.goodserapp.module.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import co.sspp.goodserapp.base.BaseFragment;


/**
 * User: ZiYeYouHu
 * Date: 2016-03-11
 * Time: 14:36
 * FIXME
 */

public class GMyGoodsFragment extends BaseFragment {
    private static final String TAG = "GMyGoodsFragment";

    @Override
    protected View initView(LayoutInflater inflater) {
        TextView view = new TextView(context);

        view.setText("我的货盘");

        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}


