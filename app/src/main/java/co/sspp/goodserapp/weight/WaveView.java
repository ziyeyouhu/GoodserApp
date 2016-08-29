package co.sspp.goodserapp.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author
 */
public class WaveView extends View {

    private float mViewWidth;
    private int mViewHeight;
    private float mLevelLine;
    private float mWaveHeight = 15;
    private float mWaveWidth = 200;

    private float mLeftSide;
    private float mRightSide;
    private float mMoveLen;
    private float mMoveLen1;
    /**
     * 水波平移速度
     */
    public static final float SPEED = 0.6f;
    public static final float SPEEDX = 1.0f;
    public static final int SPEEDWAVE = 12;   //时间速度   越小越快

    private List<Point> mPointsList;
    private List<Point> mPointsList1;
    private Paint mPaint;
    private Paint mPaint1;
    private Path mWavePath;
    private Path mWavePath1;
    private boolean isMeasured = false;
    private Timer timer;
    private MyTimerTask mTask;

    private void resetPoints() {
        mLeftSide = -mWaveWidth;
        for (int i = 0; i < mPointsList.size(); i++) {
            mPointsList.get(i).setX(i * mWaveWidth / 4 - mWaveWidth);
        }
    }

    private void resetPoints1() {
        mRightSide = mWaveWidth * 2;
        for (int i = 0; i < mPointsList.size(); i++) {
            mPointsList1.get(i).setX(i * mWaveWidth / 4);
        }
    }

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPointsList = new ArrayList<>();
        mPointsList1 = new ArrayList<>();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#ffffff"));  //白色

        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setColor(Color.parseColor("#DFF2FF")); //蓝白色

        mWavePath = new Path();
        mWavePath1 = new Path();
    }

    public void startWaveLine() {
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new Timer();
        mTask = new MyTimerTask(updateHandler);
        timer.schedule(mTask, 0, SPEEDWAVE);
    }

    public void stopWaveLine() {
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isMeasured) {
            isMeasured = true;

            mViewHeight = getMeasuredHeight();

            mViewWidth = getMeasuredWidth() / 4.0f;

            mLevelLine = mViewHeight / 2;
            // 根据View宽度计算波形峰值
            mWaveHeight = mViewWidth / 10.0f;
            // 波长等于四倍View宽度也就是View中只能看到四分之一个波形，这样可以使起伏更明显
            mWaveWidth = mViewWidth * 4;
            // 左边隐藏的距离预留一个波形
            mLeftSide = -mWaveWidth;
            mRightSide = mWaveWidth * 2;
            // 这里计算在可见的View宽度中能容纳几个波形，注意n上取整
            int n = (int) Math.round(mViewWidth / mWaveWidth + 0.5);
            // n个波形需要4n+1个点，但是我们要预留一个波形在左边隐藏区域，所以需要4n+5个点
            for (int i = 0; i < (4 * n + 5); i++) {
                // 从P0开始初始化到P4n+4，总共4n+5个点
                float x = i * mWaveWidth / 4 - mWaveWidth;
                float y = 0;
                switch (i % 4) {
                    case 0:
                    case 2:
                        // 零点位中线上
                        y = mLevelLine;
                        break;
                    case 1:
                        // 往下波动的控制点
                        y = mLevelLine + mWaveHeight;
                        break;
                    case 3:
                        // 往上波动的控制点
                        y = mLevelLine - mWaveHeight;
                        break;
                }
                mPointsList.add(new Point(x, y));
                mPointsList1.add(new Point(x, y));
            }
            int h = 1;
            for (int t = 1; t < mPointsList1.size(); t = t + 2) {
                if (h % 2 == 1) {
                    mPointsList1.get(t).setY(mPointsList.get(t).getY() - mWaveHeight * 2);
                } else {
                    mPointsList1.get(t).setY(mPointsList.get(t).getY() + mWaveHeight * 2);
                }
                h++;
            }
            for (int x = 0; x < mPointsList.size(); x++) {
                mPointsList1.get(x).setX(mPointsList.get(x).getX() + mWaveWidth);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mWavePath1.reset();

        int j = 5;
        mWavePath1.moveTo(mPointsList1.get(8).getX(), mPointsList1.get(8).getY());
        for (; j >= -1; j = j - 2) {
            mWavePath1.quadTo(mPointsList1.get(j + 2).getX(), mPointsList1.get(j + 2).getY(), mPointsList1.get(j + 1).getX(), mPointsList1.get(j + 1).getY());
        }
        mWavePath1.lineTo(mPointsList.get(0).getX(), mViewHeight);
        mWavePath1.lineTo(mRightSide, mViewHeight);
        mWavePath1.close();
        canvas.drawPath(mWavePath1, mPaint1);


        mWavePath.reset();
        int i = 0;
        mWavePath.moveTo(mPointsList.get(0).getX(), mPointsList.get(0).getY());
        for (; i < mPointsList.size() - 2; i = i + 2) {
            mWavePath.quadTo(mPointsList.get(i + 1).getX(), mPointsList.get(i + 1).getY(), mPointsList.get(i + 2).getX(), mPointsList.get(i + 2).getY());
        }
        mWavePath.lineTo(mPointsList.get(8).getX(), mViewHeight);
        mWavePath.lineTo(mLeftSide, mViewHeight);
        mWavePath.close();
        // mPaint的Style是FILL，会填充整个Path区域
        canvas.drawPath(mWavePath, mPaint);
    }

    class MyTimerTask extends TimerTask {
        Handler handler;

        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            handler.sendMessage(handler.obtainMessage());
        }

    }

    Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 记录平移总位移
            mMoveLen += SPEED;
            mMoveLen1 += SPEEDX;

            if (mLevelLine < mViewHeight / 2)
                mLevelLine = mViewHeight / 2;
            mLeftSide += SPEED;
            mRightSide -= SPEEDX;
            // 波形平移
            for (int i = 0; i < mPointsList.size(); i++) {
                mPointsList.get(i).setX(mPointsList.get(i).getX() + SPEED);
            }
            for (int i = 0; i < mPointsList1.size(); i++) {
                mPointsList1.get(i).setX(mPointsList1.get(i).getX() - SPEEDX);
            }
            if (mMoveLen >= mWaveWidth) {
                // 波形平移超过一个完整波形后复位
                mMoveLen = 0;
                resetPoints();
            }
            if (mMoveLen1 >= mWaveWidth) {
                // 波形平移超过一个完整波形后复位
                mMoveLen1 = 0;
                resetPoints1();
            }
            invalidate();
        }
    };

    class Point {
        private float x;
        private float y;

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

    }

}
