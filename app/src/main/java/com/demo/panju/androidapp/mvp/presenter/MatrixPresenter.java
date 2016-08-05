package com.demo.panju.androidapp.mvp.presenter;

import com.demo.panju.androidapp.base.BasePresenter;
import com.demo.panju.androidapp.mvp.view.MatrixView;

/**
 * Author : PZY
 * Date : 2016.8.5
 */
public interface MatrixPresenter extends BasePresenter<MatrixView>{
    void click(int position);
}
