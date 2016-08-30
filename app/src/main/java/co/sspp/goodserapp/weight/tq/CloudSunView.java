package co.sspp.goodserapp.weight.tq;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import co.sspp.goodserapp.R;


public class CloudSunView extends View {

    private static Paint paint;
    int degrees;
    float startAngle;
    float sweepAngle;
    Cloud cloud;
    boolean isStatic;
    boolean isAnimated;
    int strokeColor;
    int bgColor;
    private int width, screenH;
    private float X, Y, XSun, YSun;
    private Path path, path1;
    private double count;


    public CloudSunView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SkyIcon);

        // get attributes from layout
        isStatic = a.getBoolean(R.styleable.SkyIcon_isStatic, this.isStatic);
        strokeColor = a.getColor(R.styleable.SkyIcon_strokeColor, this.strokeColor);
        if (strokeColor == 0) {
            strokeColor = Color.BLACK;
        }
        bgColor = a.getColor(R.styleable.SkyIcon_bgColor, this.bgColor);
        if (bgColor == 0) {
            bgColor = Color.WHITE;
        }

        init();
    }


    private void init() {

        // initialize default values
        degrees = 0;
        count = 0;
        startAngle = 45;
        sweepAngle = 165;
        isAnimated = true;
        paint = new Paint();

        paint.setColor(strokeColor);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(0, 0, 0, Color.BLACK);

        cloud = new Cloud();

    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w; //getting Screen Width
        screenH = h;// getting Screen Height

        // center point of Screen
        X = width / 2;
        Y = (screenH / 2);

    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // set canvas background color
        canvas.drawColor(bgColor);
        // set stroke width
        paint.setStrokeWidth((float) (0.02083 * width));

        // initializing paths
        path = new Path();
        path1 = new Path();

        // positioning Sun with respect to cloud
        PointF P5c1 = cloud.getP5c1(X, Y, width, count);
        if (XSun == 0) {
            // center point for Sun
            XSun = P5c1.x;
            YSun = P5c1.y + (int) (0.042 * width);
        }

        //incrementing counter for rotation
        count = count + 0.5;

        //comparison to check 360 degrees rotation
        int retval = Double.compare(count, 360.00);

        if (retval == 0) {

            if (!isAnimated) {
                // mark completion of animation
                isAnimated = true;
                //resetting counter on completion of a rotation
                count = 0;
            } else {
                //resetting counter on completion of a rotation
                count = 0;
            }
        }

        // drawing center circle
        path.addCircle(XSun, YSun, (int) (0.083 * width), Path.Direction.CW);

        // drawing arms of sun
        for (int i = 0; i < 360; i += 45) {
            path1.moveTo(XSun, YSun);
            float x1 = (float) ((int) (0.1146 * width) * Math.cos(Math.toRadians(i + count / 4)) + XSun); //arm pointX at radius 50 with incrementing angle from center of sun
            float y1 = (float) ((int) (0.1146 * width) * Math.sin(Math.toRadians(i + count / 4)) + YSun);//arm pointY at radius 50 with incrementing angle from center of sun
            float X2 = (float) ((int) (0.1563 * width) * Math.cos(Math.toRadians(i + count / 4)) + XSun);//arm pointX at radius 65 with incrementing angle from center of sun
            float Y2 = (float) ((int) (0.1563 * width) * Math.sin(Math.toRadians(i + count / 4)) + YSun);//arm pointY at radius 65 with incrementing angle from center of sun
            path1.moveTo(x1, y1); // draw arms of sun
            path1.lineTo(X2, Y2);

        }

        // drawing sun
        canvas.drawPath(path, paint);
        canvas.drawPath(path1, paint);

        // drawing cloud with fill
        paint.setColor(bgColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(cloud.getCloud(X, Y, width, count), paint);

        // drawing cloud with stroke
        paint.setColor(strokeColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(cloud.getCloud(X, Y, width, count), paint);


        if (!isStatic || !isAnimated) {
            // invalidate if not static or not animating
            invalidate();
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // nothing to do
                return true;
            case MotionEvent.ACTION_MOVE:
                // nothing to do
                break;
            case MotionEvent.ACTION_UP:
                // start animation if it is not animating
                if (isStatic && isAnimated) {
                    isAnimated = false;
                }

                break;
            default:
                return false;
        }

        // Schedules a repaint.
        if (!isAnimated) {
            invalidate();
        }
        return true;
    }
}