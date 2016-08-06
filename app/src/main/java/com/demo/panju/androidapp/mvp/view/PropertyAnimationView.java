package com.demo.panju.androidapp.mvp.view;

import android.app.Activity;
import android.widget.ImageView;

import com.demo.panju.androidapp.base.BaseView;

/**
 * Author : PZY
 * Date : 2016.8.3
 */
public interface PropertyAnimationView extends BaseView{
    ImageView getImageView();

    Activity getCurrentActivity();

    void addView();

    void removeView();
}
