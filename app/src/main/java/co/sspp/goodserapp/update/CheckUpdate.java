//package co.sspp.goodserapp.update;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AlertDialog;
//
//import com.lzy.okhttputils.OkHttpUtils;
//import com.lzy.okhttputils.cache.CacheMode;
//import com.lzy.okhttputils.callback.StringCallback;
//
//import co.sspp.goodserapp.base.App;
//import co.sspp.goodserapp.update.bean.Apkinfo;
//import co.sspp.goodserapp.update.bean.Constant;
//import co.sspp.goodserapp.update.service.DownloadService;
//import co.sspp.goodserapp.utils.GsonUtil;
//import okhttp3.Request;
//import okhttp3.Response;
//
//
///**
// * User: ZiYeYouHu
// * Date: 2016-07-04
// * Time: 12:28
// * Des:
// * FIXME
// */
//public class CheckUpdate {
//
//    //Singleton
//    private CheckUpdate() {
//    }
//
//    private Context mcontext;
//    private volatile static CheckUpdate checkUpdate = null;
//
//
//    public static CheckUpdate getInstance() {
//        if (checkUpdate == null) {
//            synchronized (CheckUpdate.class) {
//                if (checkUpdate == null) {
//                    checkUpdate = new CheckUpdate();
//                }
//            }
//        }
//
//        return checkUpdate;
//    }
//
//
//    public void startCheck(Context context) {
//        mcontext = context;
//        if (App.ignore) {
//            return;
//        }
//
//
//        OkHttpUtils.get(Constant.APK_URL)        // 请求方式和请求url
//                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
//                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
//                .execute(new StringCallback() {
//                    @Override
//                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
//                        Message message = new Message();
//                        message.what = 0;
//                        message.obj = s;
//                        mhanler.sendMessage(message);
//                    }
//                });
//
//
//    }
//
//    private Handler mhanler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 0:
//
//
//                    Apkinfo apkinfo = GsonUtil.GsonToBean(msg.obj.toString(), Apkinfo.class);
//                    compareVersion(apkinfo.getVersion(), apkinfo.getIntroduction(), apkinfo.getUrl());
//
//                    break;
//            }
//        }
//    };
//
//    /**
//     *<p/>Compare the version number has updated the popup Otherwise do not do any processing
//     * @param newVersion
//     * @param intro
//     * @param url
//     */
//    private void compareVersion(int newVersion, String intro, final String url) {
//        int versionCode = getVerCode(mcontext);
//
//        if (newVersion > versionCode) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
//            builder.setTitle("发现更新");
//            builder.setMessage(intro);
//            builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Intent intent = new Intent(mcontext, DownloadService.class);
//                    intent.putExtra("url", url);
//                    mcontext.startService(intent);
//                }
//            });
//            builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    App.ignore = true;
//                }
//            });
//            builder.setCancelable(false).show();
//        } else {
//            return;
//        }
//    }
//
//
//    private int getVerCode(Context ctx) {
//        int currentVersionCode = 0;
//        PackageManager manager = ctx.getPackageManager();
//        try {
//            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
//            currentVersionCode = info.versionCode;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return currentVersionCode;
//    }
//
//}
