package com.demo.panju.androidapp.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : PZY
 * Date : 2016.8.3
 */
public class Data {

    private static List<String> mViewDatas = new ArrayList<>();

    private static List<String> mPropertyDatas = new ArrayList<>();

    private static List<String> mTransform = new ArrayList<>();

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

        mPropertyDatas.add("平移X");
        mPropertyDatas.add("平移Y");
        mPropertyDatas.add("旋转X");
        mPropertyDatas.add("旋转Y");
        mPropertyDatas.add("旋转");
        mPropertyDatas.add("缩放X");
        mPropertyDatas.add("缩放Y");
        mPropertyDatas.add("透明");
        mPropertyDatas.add("x");
        mPropertyDatas.add("y");
        mPropertyDatas.add("values");
        mPropertyDatas.add("动画集合");
        mPropertyDatas.add("自定义动画");
        mPropertyDatas.add("抛物线");
        mPropertyDatas.add("抖动");

        return mPropertyDatas;
    }

    public static List<String> getTransformType() {
        if (mTransform.size() > 0) {
            mTransform.clear();
        }

        mTransform.add("平移");
        mTransform.add("旋转");
        mTransform.add("缩放");
        mTransform.add("错切X");
        mTransform.add("错切Y");
        mTransform.add("错切");
        mTransform.add("关于X轴对称");
        mTransform.add("关于Y轴对称");
        mTransform.add("关于y=x对称");
        mTransform.add("关于y=-x对称");

        return mTransform;
    }
}
