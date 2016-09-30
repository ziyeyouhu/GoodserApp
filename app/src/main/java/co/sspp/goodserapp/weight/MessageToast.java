package co.sspp.goodserapp.weight;

import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;

import co.sspp.goodserapp.base.App;
import co.sspp.goodserapp.R;

/**
 ********************************************************************************************
 *
 * 作者： ZiYeYouHu
 * 时间：2016-08-30 11:19                                                 *
 *
 ********************************************************************************************
 *
 *描述：
 *修订：
 *
 ********************************************************************************************
 */
public final class MessageToast {

    public static void show(String msg, int style) {
        SuperToast superToast = SuperToast.create(App.getInstance(), msg, SuperToast.Duration.SHORT);
        TextView superToastTextView = superToast.getTextView();
        superToastTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        superToastTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        superToast.setBackground(style);
        superToast.setAnimations(SuperToast.Animations.SCALE);
        superToast.show();
    }

    public static void cancelAllToast() {
        SuperToast.cancelAllSuperToasts();
    }

    public static class Style {
        public static final int INFO = R.drawable.supertoast_blue;
        public static final int ALERT = R.drawable.supertoast_red;
    }

}
