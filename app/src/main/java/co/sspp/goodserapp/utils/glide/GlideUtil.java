package co.sspp.goodserapp.utils.glide;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import co.sspp.goodserapp.R;


public class GlideUtil {


    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private GlideUtil() {

    }


    // 将资源ID转为Uri
    public static Uri resourceIdToUri(int resourceId, Context mContext) {
        return Uri.parse(ANDROID_RESOURCE + mContext.getPackageName() + FOREWARD_SLASH + resourceId);
    }


    /***************************************
     * 普通加载
     *********************************************/

    // 加载网络图片
    public static void loadUrlImage(String url, ImageView imageView, Context mContext) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.color.colorAccent)
                .error(R.color.colorAccent)
                .crossFade()
                .into(imageView);
    }

    // 加载drawable图片
    public static void loadResImage(int resId, ImageView imageView, Context mContext) {
        Glide.with(mContext)
                .load(resId)
                .placeholder(R.color.colorAccent)
                .error(R.color.colorAccent)
                .crossFade()
                .into(imageView);
    }


    // 加载本地图片
    public static void loadLocalImage(String path, ImageView imageView, Context mContext) {
        Glide.with(mContext)
                .load("file://" + path)
                .placeholder(R.color.colorAccent)
                .error(R.color.colorAccent)
                .crossFade()
                .into(imageView);
    }


    /***************************************
     * 加载圆形图片
     *********************************************/

    // 加载网络圆型图片
    public static void loadCircleImage(String url, ImageView imageView, Context mContext) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    // 加载drawable圆型图片
    public static void loadCircleResImage(int resId, ImageView imageView, Context mContext) {
        Glide.with(mContext)
                .load(resId)
                .placeholder(R.color.colorAccent)
                .error(R.color.colorAccent)
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    // 加载本地圆型图片
    public static void loadCircleLocalImage(String path, ImageView imageView, Context mContext) {
        Glide.with(mContext)
                .load("file://" + path)
                .placeholder(R.color.colorAccent)
                .error(R.color.colorAccent)
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }


    /***************************************加载圆角图片*********************************************/

    // 加载网络圆型图片
//    public static void loadRoundImage(String url, ImageView imageView, Context mContext,int dp) {
//        Glide.with(mContext)
//                .load(url)
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .crossFade()
//                .transform(new GlideRoundTransform(mContext, dp))
//                .into(imageView);
//    }


}
