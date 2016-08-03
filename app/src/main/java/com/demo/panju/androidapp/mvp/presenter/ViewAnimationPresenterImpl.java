package com.demo.panju.androidapp.mvp.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.demo.panju.androidapp.mvp.view.ViewAnimationView;

import javax.inject.Inject;

/**
 * Author : panju
 * Date : 16-7-30
 */
public class ViewAnimationPresenterImpl implements ViewAnimationPresenter {
    private ViewAnimationView mViewAnimationView;
    private int mWidth;
    private int mHeight;

    @Inject
    public ViewAnimationPresenterImpl (Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWidth = windowManager.getDefaultDisplay().getWidth();
        mHeight = windowManager.getDefaultDisplay().getHeight();
    }

    @Override
    public void click(int position) {
        switch (position) {
            case 0:
                alpha();
                break;

            case 1:
                translate();
                break;

            case 2:
                rotate();
                break;

            case 3:
                scale();
                break;

            case 4:
                animationSet();
                break;

            case 5:
                freeFall();
                break;

            default:
                break;
        }
    }

    @Override
    public void attachView(@NonNull ViewAnimationView view) {
        this.mViewAnimationView = view;
    }

    @Override
    public void detachView() {
        mViewAnimationView = null;
    }

    private void alpha() {
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(1000);
        alpha.willChangeBounds();
        mViewAnimationView.startAnimation(alpha);
    }

    private void rotate() {
        RotateAnimation rotate = new RotateAnimation(0, 360, 100, 100);
        rotate.setDuration(1000);
        mViewAnimationView.startAnimation(rotate);

    }

    private void translate() {
        TranslateAnimation translate = new TranslateAnimation(0, mWidth/2, 0, mHeight/2);
        translate.setDuration(1000);
        mViewAnimationView.startAnimation(translate);
    }

    private void scale() {
        ScaleAnimation scale = new ScaleAnimation(0, 2, 0, 2);
        scale.setDuration(1000);
        mViewAnimationView.startAnimation(scale);
    }

    private void freeFall() {
        TranslateAnimation freeFall = new TranslateAnimation(0, 0, 0, mHeight);
        freeFall.setInterpolator(new AccelerateInterpolator());
        freeFall.setDuration(1500);
       mViewAnimationView.startAnimation(freeFall);
    }

    private void animationSet() {
        AnimationSet set = new AnimationSet(false);
        set.setDuration(2000);

        TranslateAnimation translateX = new TranslateAnimation(0, mWidth/4, 0, 0);
        translateX.setInterpolator(new AccelerateInterpolator());
        translateX.setDuration(2000);
        set.addAnimation(translateX);

        TranslateAnimation translateY = new TranslateAnimation(0, 0, 0, mHeight/4);
        translateY.setInterpolator(new LinearInterpolator());
        translateY.setDuration(2000);
        set.addAnimation(translateY);

        ScaleAnimation scale = new ScaleAnimation(1, 1.5f, 1, 1.5f);
        scale.setInterpolator(new AccelerateDecelerateInterpolator());
        scale.setDuration(2000);
        set.addAnimation(scale);

        mViewAnimationView.startAnimation(set);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                alpha();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
