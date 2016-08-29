package co.sspp.goodserapp.home;

import android.os.Bundle;

import co.sspp.goodserapp.BaseActivity;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.utils.AutoUtils;
import co.sspp.goodserapp.weight.StatusBarCompat;

/**
 * *******************************************************************************************
 * <p>
 * 作者： ZiYeYouHu
 * 时间：2016-08-29 14:32
 * <p>
 * *******************************************************************************************
 * <p>
 * 描述：
 * 修订：
 * <p>
 * *******************************************************************************************
 */

public class FindShipDetails extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_activity_findship_details);
        AutoUtils.auto(this);
        StatusBarCompat.translucentStatusBar(this);

    }
}
