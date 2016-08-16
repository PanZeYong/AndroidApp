package com.demo.panju.androidapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.base.BaseFragment;
import com.demo.panju.androidapp.bean.Category;
import com.demo.panju.androidapp.network.GalleryApi;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author : PZY
 * Date : 2016.8.16
 */
public class GalleryFragment extends BaseFragment{
    private final static String TAG = GalleryFragment.class.getSimpleName();

    private GalleryApi mGalleryApi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }

    @Override
    protected void init() {
        this.mGalleryApi = new GalleryApi(mContext);
        category();
    }

    private void category() {
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
                        for (int i = 0; i < category.getTngou().size(); i++) {
                            Log.e(TAG, category.getTngou().get(i).getDescription());
                        }

                    }
                });
    }

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }
}
