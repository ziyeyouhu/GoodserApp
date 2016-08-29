package co.sspp.goodserapp;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

import co.sspp.goodserapp.hotfix.PatchUtils;


/**
 * User: ZiYeYouHu
 * Date: 2016-07-04
 * Time: 10:54
 * Des:
 * FIXME
 */
public class App extends Application {

    private static final String TAG ="App" ;
    private static App instance;
    private static App sApp;

    /**
     * apatch文件
     */
    private static final String APATCH_PATH = "/Dennis.apatch";

    private PatchManager mPatchManager;




    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;




//        mPatchManager = new PatchManager(this);
//        mPatchManager.init("1.0");
//
//
//        mPatchManager.loadPatch();
//
//
//
//
//        String patchFileString = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
//        File apatchPath = new File(patchFileString);
//
//        if (apatchPath.exists()) {
//            Log.i(TAG, "补丁文件存在");
//            try {
//
//                mPatchManager.addPatch(patchFileString);
//            } catch (IOException e) {
//                Log.i(TAG, "打补丁出错了");
//                e.printStackTrace();
//            }
//        } else {
//            Log.i(TAG, "补丁文件不存在");
//        }
//
//        mPatchManager.removeAllPatch();

        PatchUtils.loadPatch(this);


    }




    public static App get() {
        App inst = sApp;  // <<< 在这里创建临时变量
        if (inst == null) {
            synchronized (App.class) {
                inst = sApp;
                if (inst == null) {
                    inst = new App();
                    sApp = inst;
                }
            }
        }
        return inst;  // <<< 注意这里返回的是临时变量
    }

}
