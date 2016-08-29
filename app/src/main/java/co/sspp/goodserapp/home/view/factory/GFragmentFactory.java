package co.sspp.goodserapp.home.view.factory;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

import co.sspp.goodserapp.BaseFragment;
import co.sspp.goodserapp.home.view.FindShipFragment;
import co.sspp.goodserapp.home.view.GIndexFragment;
import co.sspp.goodserapp.home.view.GMyGoodsFragment;
import co.sspp.goodserapp.home.view.MineFrament;


/**
 * @author shn
 * @Description Fragment's Factory
 */
public class GFragmentFactory {

    private static Map<Integer, BaseFragment> baseFragments = new HashMap<>();

    /**
     * 用于创建fragment
     *
     * @param position
     * @return
     */
    public static Fragment creatFragment(int position) {
        BaseFragment baseFragment = baseFragments.get(position);

        if (baseFragment == null) {

            switch (position) {
                case 0:
                    baseFragment = new GIndexFragment();
                    break;
                case 1:
                    baseFragment = new FindShipFragment();
                    break;

                case 2:
                    baseFragment = new GMyGoodsFragment();
                    break;
                case 3:
                    baseFragment = new MineFrament();       //

            }

            baseFragments.put(position, baseFragment);    // Will have to create the fragments of records to the collection
        }
        return baseFragment;
    }
}
