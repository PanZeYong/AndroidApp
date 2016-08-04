package com.demo.panju.androidapp.bean;

/**
 * Author : PZY
 * Date : 2016.8.4
 */
public class Point {
    private float x;

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }


}
