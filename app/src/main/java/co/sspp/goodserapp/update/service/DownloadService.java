//package co.sspp.goodserapp.update.service;
//
//import android.app.IntentService;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.support.annotation.Nullable;
//import android.support.v4.app.NotificationCompat.Builder;
//
//import com.lzy.okhttputils.OkHttpUtils;
//import com.lzy.okhttputils.callback.FileCallback;
//
//import java.io.File;
//
//import co.sspp.goodserapp.R;
//import co.sspp.goodserapp.update.util.StorageUtils;
//import okhttp3.Request;
//import okhttp3.Response;
//
///**
// * User: ZiYeYouHu
// * Date: 2016-07-04
// * Time: 12:28
// *  Des:
// * FIXME
// */
//public class DownloadService extends IntentService {
//    private NotificationManager mNotifyManager;
//    private Builder mBuilder;
//    int oldprogress = 0;
//
//    public DownloadService() {
//        super("DownloadService");
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//
//
//        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mBuilder = new Builder(this);
//        String appName = getString(getApplicationInfo().labelRes);
//        int icon = getApplicationInfo().icon;
//        mBuilder.setContentTitle(appName).setSmallIcon(icon);
//        String apkurl = intent.getStringExtra("url");
//        final String apkName = apkurl.substring(apkurl.lastIndexOf("/") + 1, apkurl.length());
//        final String path = StorageUtils.getCacheDirectory(this).getPath();
//
//
//        OkHttpUtils
//                .get(apkurl)
//                .tag(this)//
//                .execute(new FileCallback(path, apkName) {
//                    @Override
//                    public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
//
//                        mBuilder.setContentText(getString(R.string.download_success)).setProgress(0, 0, false);
//                        mNotifyManager.cancelAll();
//
//                        Intent installAPKIntent = new Intent(Intent.ACTION_VIEW);
//                        installAPKIntent.setDataAndType(Uri.fromFile(new File(path, apkName)), "application/vnd.android.package-archive");
//                        installAPKIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(installAPKIntent);
//
//
//
//                    }
//
//                    @Override
//                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
//                        super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
//
//                        System.out.println(progress);
//                        System.out.println(totalSize);
//
//                        int p = (int) (100 * progress);
//                        if (p == oldprogress) {
//                            //When progress as not update notification bar, avoid excessive caton operation
//                        } else {
//                            updateProgress(p);
//                        }
//                        oldprogress = p;
//
//                    }
//                });
//
//
//
//
//
//
//
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//    }
//
//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//        return super.onStartCommand(intent, flags, startId);
//
//    }
//
//    /**
//     * updateProgress
//     *
//     * @param progress   current progress
//     */
//    private void updateProgress(int progress) {
//        mBuilder.setContentText(this.getString(R.string.download_progress, progress)).setProgress(100, progress, false);
//        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
//        mBuilder.setContentIntent(pendingintent);
//        mNotifyManager.notify(0, mBuilder.build());
//    }
//
//}
