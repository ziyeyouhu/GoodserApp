//package co.sspp.goodserapp;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.view.ViewCompat;
//import android.support.v4.view.ViewPager;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.bingoogolapple.bgabanner.BGABanner;
//import cn.bingoogolapple.bgabanner.BGABannerUtil;
//import co.sspp.goodserapp.home.view.HomeActivity;
//
//public class GuideActivity extends Activity implements View.OnClickListener {
//    private static final String TAG = GuideActivity.class.getSimpleName();
//    private TextView mSkipTv;
//    private Button mEnterBtn;
//    private BGABanner mContentBanner;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_guide);
//
//        initView();
//        setListener();
//        processLogic();
//    }
//
//    private void initView() {
//        mSkipTv = (TextView) findViewById(R.id.tv_guide_skip);
//        mEnterBtn = (Button) findViewById(R.id.btn_guide_enter);
//        mContentBanner = (BGABanner) findViewById(R.id.banner_guide_content);
//    }
//
//    private void setListener() {
//        mSkipTv.setOnClickListener(this);
//        mEnterBtn.setOnClickListener(this);
//        // 监听广告 item 的单击事件
//        mContentBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
//            @Override
//            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
//                Log.i(TAG, "点击了第" + (position + 1) + "页");
//            }
//        });
//        mContentBanner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position == mContentBanner.getItemCount() - 2) {
//                    ViewCompat.setAlpha(mEnterBtn, positionOffset);
//                    ViewCompat.setAlpha(mSkipTv, 1.0f - positionOffset);
//
//                    if (positionOffset > 0.5f) {
//                        mEnterBtn.setVisibility(View.VISIBLE);
//                        mSkipTv.setVisibility(View.GONE);
//                    } else {
//                        mEnterBtn.setVisibility(View.GONE);
//                        mSkipTv.setVisibility(View.VISIBLE);
//                    }
//                } else if (position == mContentBanner.getItemCount() - 1) {
//                    mSkipTv.setVisibility(View.GONE);
//                    mEnterBtn.setVisibility(View.VISIBLE);
//                    ViewCompat.setAlpha(mEnterBtn, 1.0f);
//                } else {
//                    mSkipTv.setVisibility(View.VISIBLE);
//                    ViewCompat.setAlpha(mSkipTv, 1.0f);
//                    mEnterBtn.setVisibility(View.GONE);
//                }
//            }
//        });
//    }
//
//    private void processLogic() {
//        mContentBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
//
//        // 初始化方式1：通过传入数据模型并结合Adapter的方式初始化
////        mContentBanner.setAdapter(new BGABanner.Adapter() {
////            @Override
////            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
////                ((ImageView) view).setImageResource((int) model);
////            }
////        });
////        mContentBanner.setData(Arrays.asList(R.drawable.ic_guide_1, R.drawable.ic_guide_2, R.drawable.ic_guide_3), null);
//
//
//        // 初始化方式2：通过直接传入视图集合的方式初始化
//        List<View> views = new ArrayList<>();
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_1));
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_2));
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_3));
//        mContentBanner.setData(views);
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.btn_guide_enter || view.getId() == R.id.tv_guide_skip) {
//            startActivity(new Intent(GuideActivity.this, HomeActivity.class));
//            finish();
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mContentBanner.setBackgroundResource(android.R.color.white);
//    }
//}