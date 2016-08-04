package com.demo.panju.androidapp.animation;

import android.animation.TimeInterpolator;

/**
 * Author : PZY
 * Date : 2016.8.3
 */
public class CustomInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float v) {
        float result;

        if (v < 0.5) {
            result = v;
        } else {
            result = (float) Math.pow(v, v);
        }
        return result;
    }
}
