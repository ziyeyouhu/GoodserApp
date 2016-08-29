package co.sspp.goodserapp.utils.less;

import android.content.Context;

import java.io.File;

public final class AppLess {


    /**
     * 删除应用数据： cache, file, share prefs, databases
     *
     * @param context
     */
    public static void $clear(Context context) {
        $clearCache(context);
        $clearFiles(context);
        $clearSharedPreference(context);
        $clearDatabase(context);
    }

    /**
     * 删除应用缓存目录
     *
     * @param context
     */
    public static void $clearCache(Context context) {
        FileLess.$del(context.getCacheDir(), true);
        FileLess.$del(context.getExternalCacheDir(), true);
    }

    /**
     * 删除应用文件目录
     *
     * @param context
     */
    public static void $clearFiles(Context context) {
        FileLess.$del(context.getFilesDir(), true);
    }

    /**
     * 删除应用Shared Prefrence目录
     *
     * @param context
     */
    public static void $clearSharedPreference(Context context) {
        FileLess.$del(new File("/data/data/" + context.getPackageName() + "/shared_prefs"), true);
    }

    /**
     * 删除应用数据库目录
     *
     * @param context
     */
    public static void $clearDatabase(Context context) {
        FileLess.$del(new File("/data/data/" + context.getPackageName() + "/databases"), true);
    }
}