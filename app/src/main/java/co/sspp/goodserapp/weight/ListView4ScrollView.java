package co.sspp.goodserapp.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * *******************************************************************************************
 * <p>
 * 作者： ZiYeYouHu
 * 时间：2016-08-30 15:43                                                 *
 * <p>
 * *******************************************************************************************
 * <p>
 * 描述：
 * 修订：
 * <p>
 * *******************************************************************************************
 */
public class ListView4ScrollView extends ListView {

    public ListView4ScrollView(Context context) {
        super(context);
    }

    public ListView4ScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListView4ScrollView(Context context, AttributeSet attrs,
                               int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    /**
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}



