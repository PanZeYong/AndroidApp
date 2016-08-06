package com.demo.panju.androidapp.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.demo.panju.androidapp.animation.CustomInterpolator;
import com.demo.panju.androidapp.bean.Point;
import com.demo.panju.androidapp.animation.PointEvaluator;

/**
 * Author : PZY
 * Date : 2016.8.4
 */
public class CircleView extends View{
    private static final float LENGTH = 20f;

    private Paint mPaint;

    private Point mPoint1;
    private Point mPoint2;

    private Point mCurrentPoint;

    private boolean mFlag = true;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mPaint.setColor(Color.YELLOW);

        this.mPoint1 = new Point(LENGTH, LENGTH);
        this.mPoint2 = new Point(6 * LENGTH, 6 * LENGTH);

        this.mCurrentPoint = this.mPoint1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mFlag) {
            drawSquare(canvas);
            startAnimation();
        } else {
            drawSquare(canvas);
        }
    }

    private void drawSquare(Canvas canvas) {
        float x1;
        float y1;
        float x2;
        float y2;

        if (mFlag) {
            x1 = this.mPoint1.getX();
            y1 = this.mPoint1.getY();

            x2 = this.mPoint2.getX();
            y2 = this.mPoint2.getY();
            this.mFlag = false;
        } else {
            x1 = this.mPoint1.getX() + this.mCurrentPoint.getX();
            y1 = this.mPoint1.getY() + this. mCurrentPoint.getY();

            x2 = this.mPoint2.getX() + this.mCurrentPoint.getX();
            y2 = this.mPoint2.getY() + this. mCurrentPoint.getY();
        }

        canvas.drawRect(x1, y1, x2, y2, mPaint);
    }

    private void startAnimation() {
        Point startPoint = this.mPoint1;
        Point endPoint = new Point(getWidth() / 2, getHeight() / 2);
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentPoint = (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        animator.setDuration(5000);
        animator.setInterpolator(new CustomInterpolator());
        animator.start();
    }
}
