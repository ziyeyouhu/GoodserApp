package co.sspp.goodserapp.module.home.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.sspp.goodserapp.R;

/**
 * User: ZiYeYouHu
 * Date: 2016-07-19
 * Time: 19:37
 * Des:
 * FIXME
 */

public class DownloadFragment extends Fragment {
    private boolean mHasLoadedOnce = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.yinhangka, container, false);

    }



    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (this.isVisible()) {
            // we check that the fragment is becoming visible
            if (isVisibleToUser && mHasLoadedOnce ) {

                System.out.println("耗时");
                mHasLoadedOnce = false;
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


}
