package com.demo.panju.androidapp.mvp.presenter;

import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.demo.panju.androidapp.animation.ShakeAnimation;
import com.demo.panju.androidapp.bean.Point;
import com.demo.panju.androidapp.mvp.view.PropertyAnimationView;
import com.demo.panju.androidapp.ui.activity.CustomAnimationActivity;

import javax.inject.Inject;

/**
 * Author : PZY
 * Date : 2016.8.3
 */
public class PropertyAnimationPresenterImpl implements PropertyAnimationPresenter{
    private PropertyAnimationView mView;

    private int mWidth;
    private int mHeight;

    @Inject
    public PropertyAnimationPresenterImpl(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWidth = windowManager.getDefaultDisplay().getWidth();
        mHeight = windowManager.getDefaultDisplay().getHeight();
    }

    @Override
    public void attachView(@NonNull PropertyAnimationView view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void click(int position) {
        switch (position) {
            case 0:
                translateX();
                break;

            case 1:
                translateY();
                break;

            case 2:
                rotationX();
                break;

            case 3:
                rotationY();
                break;

            case 4:
                rotation();
                break;

            case 5:
                scaleX();
                break;

            case 6:
                scaleY();
                break;

            case 7:
                alpha();
                break;

            case 8:
                x();
                break;

            case 9:
                y();
                break;

            case 10:
                propertyValuesHolder();
                break;

            case 11:
                animatorSet();
                break;

            case 12:
                mView.addView();
                break;

            case 13:
                parabola();
                break;

            case 14:
                shake();
                break;

            default:
                break;
        }
    }

    private void translateX() {
        reset();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView.getImageView(), "translationX", 240);
        animator.setDuration(500);
        animator.start();
    }

    private void translateY() {
        reset();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView.getImageView(), "translationY", 480);
        animator.setDuration(500);
        animator.start();
    }

    private void rotationX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView.getImageView(), "rotationX", 0f, 240f);
        animator.setDuration(500);
        animator.start();
    }

    private void rotationY() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView.getImageView(), "rotationY", 240f, 120f);
        animator.setDuration(500);
        animator.start();
    }

    private void rotation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView.getImageView(), "rotation", 0f, 180f);
        animator.setDuration(500);
        animator.start();
    }

    private void scaleX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView.getImageView(), "scaleX", 1.0f, 0.8f, 1.0f);
        animator.setDuration(500);
        animator.start();
    }

    private void scaleY() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView.getImageView(), "scaleY", 0.5f, 0.8f, 0.4f);
        animator.setDuration(500);
        animator.start();
    }

    private void alpha() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView.getImageView(), "alpha", 1, 0.5f);
        animator.setDuration(500);
        animator.start();
    }

    private void x() {
        reset();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView.getImageView(), "x", 88f);
        animator.setDuration(500);
        animator.start();
    }

    private void y() {
        reset();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView.getImageView(), "y", 144f);
        animator.setDuration(500);
        animator.start();
    }

    private void propertyValuesHolder() {
        reset();
        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", 480f, 120f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 3f, 1f, 2f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 2.0f, 1.5f);
        PropertyValuesHolder rotationX = PropertyValuesHolder.ofFloat("rotationX", 0f, 240f);
        ObjectAnimator.ofPropertyValuesHolder(mView.getImageView(), translationX, scaleX, scaleY,rotationX)
                .setDuration(500).start();
    }

    private void animatorSet() {
        reset();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(mView.getImageView(), "translationX", -200f, 280f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mView.getImageView(), "scaleX", 2.0f, 1.0f, 2.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mView.getImageView(), "scaleY", 1.0f, 2.0f, 1.5f);
        ObjectAnimator rotationX = ObjectAnimator.ofFloat(mView.getImageView(), "rotationX", 0f, 240f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(5000);
        animatorSet.play(translationX).before(scaleX).before(scaleY).before(rotationX);
        animatorSet.start();
    }

    private void parabola() {
        reset();
        ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator<Point>() {
            @Override
            public Point evaluate(float v, Point start, Point end) {
                float x = 200 * 3 * v;
                float y = 0.5f * 150 * (3 * v) * (3 * v);
                return new Point(x, y);
            }
        }, new Point(0, 0));
        animator.setDuration(3000);
        animator.start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Point point = (Point) valueAnimator.getAnimatedValue();
                mView.getImageView().setX(point.getX());
                mView.getImageView().setY(point.getY());
            }
        });
    }

    private void shake() {
        ShakeAnimation animation = new ShakeAnimation();
        animation.setDuration(5000);
        animation.setRepeatCount(5);
        mView.getImageView().startAnimation(animation);
    }

    private void reset() {
        mView.removeView();
        mView.getImageView().setX(0);
        mView.getImageView().setY(0);
    }
}
