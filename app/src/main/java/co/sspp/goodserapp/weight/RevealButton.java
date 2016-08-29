package co.sspp.goodserapp.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class RevealButton extends Button {

    private static final int INVALIDATE_DURATION = 10; //每次刷新的时间间隔
    private static int DIFFUSE_GAP = 35;                  //扩散半径增量

    private Paint paint;
    private MotionEvent motionEvent;

    private float maxRadio = 0;//最大半径
    private float radio = 0;//绘制半径
    private float pointX = 0;//被点击的坐标点x
    private float pointY = 0;//被点击的坐标点y
    private int viewHeight;//View高度
    private int viewWidth;//View宽度

    private boolean actionUpFlag = false;//actionUp标志位，标识是否可以相应actionUp事件
    private boolean actionDownFlag = false;//手指依然点击本view的标志位
    private boolean actionCancelFlag = false;//点击取消标志位

    public RevealButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public RevealButton(Context context) {
        super(context);
        initPaint();
    }

    public void initPaint() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAlpha(50);
    }

    /**
     * 设置水波纹画笔
     * setColor控制颜色
     * setAlpha控制透明度
     * **/
    public void setPaint(Paint p){
        paint = p;
    }

    //布局发生变化时回调
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.viewWidth = w;
        this.viewHeight = h;
    }

    //计算波纹最大半径
    private void countMaxRadio() {
        if (viewWidth >= viewHeight) {
            if (pointX <= viewWidth / 2) {
                maxRadio = viewWidth - pointX;
            } else {
                maxRadio = pointX;
            }
        } else {
            if (pointY <= viewHeight / 2) {
                maxRadio = viewHeight - pointY;
            } else {
                maxRadio = pointY;
            }
        }
        maxRadio = maxRadio + 20;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pointX = event.getX();
                pointY = event.getY();
                actionDownFlag = true;
                countMaxRadio();
                postInvalidateDelayed(INVALIDATE_DURATION);
                break;
            case MotionEvent.ACTION_UP:
                //flag用做判断是否适合触发点击事件，false则不适合，true则适合
                if (!actionUpFlag && !actionCancelFlag) {
                    actionUpFlag = true;
                    actionCancelFlag = false;
                    motionEvent = event;
                    return true;
                }
                actionUpFlag = false;
                clearData();
                postInvalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                actionCancelFlag = true;
                clearData();
                postInvalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!actionDownFlag) {
            return;
        }

        canvas.save();
        canvas.clipRect(0, 0, viewWidth, viewHeight);
        canvas.drawCircle(pointX, pointY, radio, paint);
        canvas.restore();

        if (radio <= maxRadio) {
            //播放动画
            radio = radio + DIFFUSE_GAP;
            postInvalidateDelayed(INVALIDATE_DURATION);
        } else {
            //动画播放完成
            if (actionUpFlag) {
                //如果为true，则说明此时actionUp已经被触发过，则重新触发
                RevealButton.this.dispatchTouchEvent(motionEvent);
            } else {
                //为false则说明actionUp未被触发，手指仍保留在点击状态，设flag为true，当actionUp被触发时，直接清空动画，响应点击事件
                actionUpFlag = true;
            }
//            clearData();
        }
    }

    private void clearData() {
        actionDownFlag = false;
        maxRadio = 0;//最大半径
        radio = 0;//绘制半径
        pointX = 0;//被点击的坐标点x
        pointY = 0;//被点击的坐标点y
    }
}