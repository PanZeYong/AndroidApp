package com.demo.panju.androidapp.mvp.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.panju.androidapp.bean.Category;
import com.demo.panju.androidapp.mvp.presenter.interfaces.GalleryPresenter;
import com.demo.panju.androidapp.mvp.view.GalleryView;
import com.demo.panju.androidapp.network.GalleryApi;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author : PZY
 * Date : 2016.8.16
 */
public class GalleryPresenterImpl implements GalleryPresenter {
    private final static String TAG = GalleryPresenterImpl.class.getSimpleName();

    private GalleryView mGalleryView;

    GalleryApi mGalleryApi;

    @Inject
    public GalleryPresenterImpl() {}

    @Override
    public void getCategories() {
        mGalleryApi.category()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Category>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Category category) {
                        Log.e(TAG, category.getTngou().size()+"");
                        //                        for (int i = 0; i < category.getTngou().size(); i++) {
                        //                            Log.e(TAG, category.getTngou().get(i).getDescription());
                        //                        }

                    }
                });
    }

    @Override
    public void attachView(@NonNull GalleryView view) {
        this.mGalleryApi = new GalleryApi();
        if (null == mGalleryView) {
            this.mGalleryView = view;
        }
    }

    @Override
    public void detachView() {
        this.mGalleryView = null;
    }
}