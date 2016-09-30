package co.sspp.goodserapp.module.home.view;


import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.telephony.TelephonyManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.sspp.goodserapp.R;
import co.sspp.goodserapp.module.home.view.factory.GFragmentFactory;
import co.sspp.goodserapp.utils.AutoUtils;
import co.sspp.goodserapp.weight.StatusBarCompat;


/**
 * 货主版 架构页
 * <p>
 * User: Song(41492367@qq.com)
 * Date: 2015-11-13
 * Time: 09:26
 */
public class HomeActivity extends FragmentActivity {

    @BindView(R.id.framelayout)
    FrameLayout mFramelayout;
    @BindView(R.id.rb_index)
    RadioButton mRbIndex;
    @BindView(R.id.rb_findship)
    RadioButton mRbFindship;
    @BindView(R.id.rb_mygoods)
    RadioButton mRbMygoods;
    @BindView(R.id.rb_mine)
    RadioButton mRbMine;
    @BindView(R.id.main_radio)
    RadioGroup mMainRadio;



    private FrameLayout frameLayout;
    private RadioGroup group;
    private RadioButton rb_index;
    private RadioButton rb_findship;             //找船
    private RadioButton rb_mygoods;             //我的货盘
    private RadioButton rb_mine;
    private int index;

//    private ImageView iv_pushGoods;


    private void getIMEI() {
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (tm != null)
            Toast.makeText(this, ("读imei成功：" + tm.getDeviceId()), Toast.LENGTH_SHORT).show();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

//        Acp.getInstance(this).request(new AcpOptions.Builder()
//                        .setPermissions(
//                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                                Manifest.permission.READ_PHONE_STATE,
//                                Manifest.permission.SEND_SMS)
//                /*以下为自定义提示语、按钮文字
//                .setDeniedMessage()
//                .setDeniedCloseBtn()
//                .setDeniedSettingBtn()
//                .setRationalMessage()
//                .setRationalBtn()*/
//                        .build(),
//                new AcpListener() {
//                    @Override
//                    public void onGranted() {
////                        writeSD();
//                        getIMEI();
//                    }
//
//                    @Override
//                    public void onDenied(List<String> permissions) {
//                        Toast.makeText(HomeActivity.this, (permissions.toString() + "权限拒绝"), Toast.LENGTH_SHORT).show();
//                    }
//                });

        RxPermissions.getInstance(this)
                .request(Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) {
                        getIMEI();
                        // All requested permissions are granted
                    } else {
                        // At least one permission is denied
                    }
                });


        AutoUtils.setSize(this, true, 750, 1334);//有状态栏,设计尺寸的宽高
        setContentView(R.layout.g_activity_home);
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.dark_blue));


        initView();

        group.setOnCheckedChangeListener((group1, checkedId) -> {
            if (checkedId == rb_index.getId()) {
                index = 0;
            }

            if (checkedId == rb_findship.getId()) {
                index = 1;
            }

            if (checkedId == rb_mygoods.getId()) {
                index = 2;
            }
            if (checkedId == rb_mine.getId()) {
                index = 3;
            }

            exchangeFragement();
        });


        group.check(R.id.rb_index);


    }

    /**
     * 切换底部导航栏的选中按钮，仅需要在Activity回归后者是被销毁后再调用
     *
     * @param childCheckedPostion
     */
    private void checkButton(int childCheckedPostion) {
        switch (childCheckedPostion) {
            case 0:
                group.check(rb_index.getId());
                break;
            case 1:
                group.check(rb_findship.getId());
                break;
            case 2:
                group.check(rb_mygoods.getId());
                break;
            case 3:
                group.check(rb_mine.getId());
                break;
        }
    }

    /**
     * 切换Fragement
     */
    private void exchangeFragement() {
        Fragment fragment = (Fragment) fragmentStatePagerAdapter
                .instantiateItem(frameLayout, index);
        fragmentStatePagerAdapter.setPrimaryItem(frameLayout, 0,
                fragment);
        fragmentStatePagerAdapter.finishUpdate(frameLayout);
    }


    private void initView() {

        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        group = (RadioGroup) findViewById(R.id.main_radio);
        rb_index = (RadioButton) findViewById(R.id.rb_index);
        rb_findship = (RadioButton) findViewById(R.id.rb_findship);              //找船
        rb_mygoods = (RadioButton) findViewById(R.id.rb_mygoods);               //我的货盘
        rb_mine = (RadioButton) findViewById(R.id.rb_mine);
//        iv_pushGoods = (ImageView) findViewById(R.id.giv_push_goods);
//        iv_pushGoods.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, PickPortActivity.class)));




//            RxView.clicks(mCrossview)
//                    .throttleFirst(1, TimeUnit.SECONDS)
//                    .subscribe(aVoid -> {
//                        mCrossview.toggle(250);
//
//                        Observable.timer(250, TimeUnit.MILLISECONDS)
//                                .subscribe(aLong -> {
//                                    startActivity(new Intent(HomeActivity.this, PickPortActivity.class));
//                                });
//
//                    });




//            Observable.timer(250, TimeUnit.MILLISECONDS)
//                    .subscribe(aLong -> {
//                        startActivity(new Intent(HomeActivity.this, PickPortActivity.class));
//                    });



    }


    FragmentStatePagerAdapter fragmentStatePagerAdapter = new FragmentStatePagerAdapter(
            getSupportFragmentManager()) {

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            return GFragmentFactory.creatFragment(position);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        index = savedInstanceState.getInt("postion");   //获取保存的位置
        exchangeFragement();    //切换位置
        checkButton(index);     //切换底部导航栏的选中状态
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("postion", index);  //保存位置
        //删除Super回调父类的方法，防止在被回收后来调用去把所有的视图都保存，然后通过获取保存的位置来重新绘制界面
        //Activity中的onSaveInstanceState() 里面有一句super.onRestoreInstanceState(savedInstanceState)，
        //Google对于这句话的解释是“Always call the superclass so it can save the view hierarchy state”，大概意思是“
        //总是执行这句代码来调用父类去保存视图层的状态”。
    }


}