package com.demo.panju.androidapp.animation;

import android.animation.TypeEvaluator;

import com.demo.panju.androidapp.bean.Point;


/**
 * Author : PZY
 * Date : 2016.8.4
 */
public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point)startValue;
        Point endPoint = (Point)endValue;

        float x = startPoint.getX() + (endPoint.getX() - startPoint.getX()) * fraction;
        float y = startPoint.getY() + (endPoint.getY() - startPoint.getY()) * fraction;

        Point point = new Point(x, y);

        return point    ;
    }
}
