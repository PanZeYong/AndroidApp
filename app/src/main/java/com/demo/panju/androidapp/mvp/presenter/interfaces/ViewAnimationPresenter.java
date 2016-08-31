package com.demo.panju.androidapp.mvp.presenter.interfaces;

import com.demo.panju.androidapp.base.BasePresenter;
import com.demo.panju.androidapp.mvp.view.ViewAnimationView;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public interface ViewAnimationPresenter extends BasePresenter<ViewAnimationView>{
    void click(int position);
}
