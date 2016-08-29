package co.sspp.goodserapp.home.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import co.sspp.goodserapp.BaseActivity;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.weight.PagerSlidingTabStrip;


/**
 * User: ZiYeYouHu
 * Date: 2016-03-22
 * Time: 10:38
 * FIXME
 */

public class GPushGoodsActivity extends BaseActivity {

    private OkhttpFragment firstFragment;
    private UploadFragment secondFragment;
    private DownloadFragment thirdFragment;


    private PagerSlidingTabStrip tabs;
    private ViewPager pager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.g_activity_pushgoods);
        pager = (ViewPager) findViewById(R.id.pager);



//        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
//        pager.setOffscreenPageLimit(2);
//        tabs.setViewPager(pager);
//        pager.setCurrentItem(0);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);


        setupViewPager(pager);
        // 设置ViewPager的数据等
        tabLayout.setupWithViewPager(pager);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适合很多tab
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//tab均分,适合少的tab
        setupViewPager(pager);

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
//            return MyFragmentFactory.creatFragment(position);
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }




    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        firstFragment = new OkhttpFragment();
        Bundle data = new Bundle();
        data.putInt("id", 0);
        firstFragment.setArguments(data);
        adapter.addFrag(firstFragment, "Frist");

        secondFragment = new UploadFragment();
        data = new Bundle();
        data.putInt("id", 1);
        secondFragment.setArguments(data);
        adapter.addFrag(secondFragment, "Second");


        thirdFragment = new DownloadFragment();
        data = new Bundle();
        data.putInt("id", 2);
        thirdFragment.setArguments(data);
        adapter.addFrag(thirdFragment, "Third");

        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(3);
    }




}

