package com.demo.panju.androidapp.mvp.presenter.impl;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.constant.Constant;
import com.demo.panju.androidapp.mvp.presenter.interfaces.MainPresenter;
import com.demo.panju.androidapp.mvp.view.MainView;
import com.demo.panju.androidapp.ui.fragment.GalleryFragment;
import com.demo.panju.androidapp.ui.fragment.InstalledAppInfoFragment;
import com.demo.panju.androidapp.ui.fragment.LoginFragment;
import com.demo.panju.androidapp.ui.fragment.MatrixFragment;
import com.demo.panju.androidapp.ui.fragment.PropertyAnimationFragment;
import com.demo.panju.androidapp.ui.fragment.ViewAnimationFragment;

import javax.inject.Inject;


/**
 * Author : PZY
 * Date : 2016.7.30
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;


    @Inject
    public MainPresenterImpl() {
    }

    @Override
    public void clickMenuItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_mvp:
                mainView.showFragment(LoginFragment.newInstance(), R.id.fg_container);
                mainView.setTitle(Constant.MVP_DEMO);
                break;

            case R.id.item_matrix:
                mainView.showFragment(MatrixFragment.newInstance(), R.id.fg_container);
                mainView.setTitle(Constant.MATRIX_DEMO);
                break;

            case R.id.item_rrc:
                mainView.showFragment(GalleryFragment.newInstance(), R.id.fg_container);
                mainView.setTitle(Constant.RXJAVA_RETROFIT_CACHE);
                break;

            case R.id.item_app_info:
                mainView.showFragment(InstalledAppInfoFragment.newInstance(), R.id.fg_container);
                mainView.setTitle(Constant.INSTALLED_APP_INFO);
                break;

            case R.id.property:
                mainView.showFragment(PropertyAnimationFragment.newInstance(), R.id.fg_container);
                mainView.setTitle(Constant.PROPERTY_ANIMATION);
                break;

            case R.id.view:
                mainView.showFragment(ViewAnimationFragment.newInstance(), R.id.fg_container);
                mainView.setTitle(Constant.VIEW_ANIMATION);
                break;

            default:
                break;
        }
        item.setChecked(true);
        mainView.closeDrawer();
    }

    @Override
    public void attachView(@NonNull MainView view) {
        this.mainView = view;
    }

    @Override
    public void detachView() {
        mainView = null;
    }
}
