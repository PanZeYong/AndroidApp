package com.demo.panju.androidapp.mvp.presenter;

import com.demo.panju.androidapp.base.BasePresenter;
import com.demo.panju.androidapp.mvp.view.PropertyAnimationView;

/**
 * Author : PZY
 * Date : 2016.8.3
 */
public interface PropertyAnimationPresenter extends BasePresenter<PropertyAnimationView>{
    void click(int position);
}
