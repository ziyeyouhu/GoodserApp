package co.sspp.goodserapp.module.home.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import co.sspp.goodserapp.base.BaseFragment;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.weight.PagerSlidingTabStrip;

/**
 * User: ZiYeYouHu
 * Date: 2016-03-11
 * Time: 14:37
 * FIXME
 */

public class GMineFrament extends BaseFragment {
    private static final String TAG = "GMineFrament";


    private ArrayList<Fragment> fragments;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;

    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_mine, null);


        pager = (ViewPager) view.findViewById(R.id.pager);
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);


        fragments = new ArrayList<>();
        fragments.add(new OkhttpFragment());
        fragments.add(new DownloadFragment());
        fragments.add(new UploadFragment());

        pager.setOffscreenPageLimit(2);
        pager.setAdapter(new MyAdapter(getActivity().getSupportFragmentManager()));
        tabs.setViewPager(pager);
        pager.setCurrentItem(0);


        return view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private class MyAdapter extends FragmentPagerAdapter {

        private String[] titles = {"资产", "银行卡", "账单"};

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {

            System.out.println(position);
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }




    }




}

