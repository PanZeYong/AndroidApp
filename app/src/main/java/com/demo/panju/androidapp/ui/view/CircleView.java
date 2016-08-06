package com.demo.panju.androidapp.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.demo.panju.androidapp.evaluator.PointEvaluator;

/**
 * Author : PZY
 * Date : 2016.8.4
 */
public class CircleView extends View{
    private static final int R = 20;

    private Paint mPaint;

    private Point mCurrentPoint;
//    private Point mPoint2;


    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mPaint.setColor(Color.YELLOW);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == this.mCurrentPoint) {
            this.mCurrentPoint = new Point(R, R);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        int x = this.mCurrentPoint.x;
        int y= this.mCurrentPoint.y;

        canvas.drawCircle(x, y, R, mPaint);
    }

    private void startAnimation() {
        Point startPoint = new Point(20f, 20f);
        Point endPoint = new Point(320f, 720f);
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentPoint = (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        animator.setDuration(1000);
        animator.start();
    }
}
