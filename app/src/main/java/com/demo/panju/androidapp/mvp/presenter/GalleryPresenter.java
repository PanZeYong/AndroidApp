package com.demo.panju.androidapp.mvp.presenter;

import com.demo.panju.androidapp.base.BasePresenter;
import com.demo.panju.androidapp.mvp.view.GalleryView;

/**
 * Author : PZY
 * Date : 2016.8.16
 */
public interface GalleryPresenter extends BasePresenter<GalleryView>{
    void getCategories();
}
