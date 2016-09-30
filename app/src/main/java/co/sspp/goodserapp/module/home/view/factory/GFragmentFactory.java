package co.sspp.goodserapp.module.home.view.factory;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

import co.sspp.goodserapp.base.BaseFragment;
import co.sspp.goodserapp.module.home.view.FindShipFragment;
import co.sspp.goodserapp.module.home.view.IndexFragment;
import co.sspp.goodserapp.module.home.view.GMyGoodsFragment;
import co.sspp.goodserapp.module.home.view.MineFrament;


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
                    baseFragment = new IndexFragment();
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
