package co.sspp.goodserapp;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import co.sspp.goodserapp.weight.ColorSnackbar;


public abstract class BaseActivity extends RxAppCompatActivity {


    /**
     * 全局应用
     */
    public Application application;

    /**
     * 全局上下文
     */
    public Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ActivityContainer.getInstance().addActivity(this);

        context = getApplicationContext();
        application = this.getApplication();


    }


    public enum CSnackBar {
        GREEN, ORANGE, RED, BLUE, DEFAULT

    }


    /**
     * @param contener
     * @param string
     * @param bar
     */
    public void showSnackBar(View contener, String string, CSnackBar bar) {
        Snackbar snackbar = Snackbar.make(contener, string, Snackbar.LENGTH_SHORT);

        switch (bar) {
            case GREEN:
                ColorSnackbar.confirm(snackbar).show();
                break;

            case RED:
                ColorSnackbar.alert(snackbar).show();
                break;

            case BLUE:
                ColorSnackbar.info(snackbar).show();
                break;
            case ORANGE:
                ColorSnackbar.warning(snackbar).show();

                break;

            case DEFAULT:

                snackbar.show();
                break;
        }


    }









    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




    /**
     * 打开一个Activity 默认 不关闭当前activity
     *
     * @param clz
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(this, clz);
        if (ex != null)
            intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }

}
