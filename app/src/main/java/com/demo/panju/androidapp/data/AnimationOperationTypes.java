package com.demo.panju.androidapp.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : PZY
 * Date : 2016.8.3
 */
public class AnimationOperationTypes {
    private static List<String> mViewDatas = new ArrayList<>();

    private static List<String> mPropertyDatas = new ArrayList<>();

    public static List<String> getViewAnimationOperationType() {
        if (mViewDatas.size() > 0) {
            mViewDatas.clear();
        }

        mViewDatas.add("透明");
        mViewDatas.add("平移");
        mViewDatas.add("旋转");
        mViewDatas.add("缩放");
        mViewDatas.add("抛物线");
        mViewDatas.add("自由落体");

        return mViewDatas;
    }

    public static List<String> getPropertyAnimationOperationType() {
        if (mPropertyDatas.size() > 0) {
            mPropertyDatas.clear();
        }

        mPropertyDatas.add("translationX");
        mPropertyDatas.add("translationY");
        mPropertyDatas.add("rotationX");
        mPropertyDatas.add("rotationY");
        mPropertyDatas.add("rotation");
        mPropertyDatas.add("scaleX");
        mPropertyDatas.add("scaleY");
        mPropertyDatas.add("alpha");
        mPropertyDatas.add("x");
        mPropertyDatas.add("y");
        mPropertyDatas.add("propertyValuesHolder");
        mPropertyDatas.add("animatorSet");
        mPropertyDatas.add("自定义view动画");


        return mPropertyDatas;
    }
}
