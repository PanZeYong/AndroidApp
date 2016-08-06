package com.demo.panju.androidapp.animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Author : PZY
 * Date : 2016.8.6
 */
public class ShakeAnimation extends Animation{
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        t.getMatrix().setTranslate((float) Math.cos(interpolatedTime * 5) * 20,
                (float)Math.cos(interpolatedTime * 5) * 20);
    }
}
