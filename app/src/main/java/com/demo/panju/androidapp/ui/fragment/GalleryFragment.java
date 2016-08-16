package com.demo.panju.androidapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.base.BaseFragment;
import com.demo.panju.androidapp.inject.HasComponent;
import com.demo.panju.androidapp.inject.component.MainComponent;
import com.demo.panju.androidapp.mvp.presenter.GalleryPresenterImpl;
import com.demo.panju.androidapp.mvp.view.GalleryView;

import javax.inject.Inject;


/**
 * Author : PZY
 * Date : 2016.8.16
 */
public class GalleryFragment extends BaseFragment implements GalleryView{
    private final static String TAG = GalleryFragment.class.getSimpleName();

    @Inject
    GalleryPresenterImpl mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }

    @Override
    protected void init() {
        getComponent(MainComponent.class).inject(this);
        mPresenter.attachView(this);
        mPresenter.getCategories();
    }

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @SuppressWarnings("unchecked") protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
