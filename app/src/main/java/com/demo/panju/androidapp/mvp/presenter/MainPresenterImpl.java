package com.demo.panju.androidapp.mvp.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.mvp.view.MainView;
import com.demo.panju.androidapp.ui.fragment.LoginFragment;
import com.demo.panju.androidapp.ui.fragment.ViewAnimationFragment;

import javax.inject.Inject;


/**
 * Author : PZY
 * Date : 2016.7.30
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;

    private final static String MVP_DEMO = "MVP Demo";
    private final static String VIEW_ANIMATION = "View Animation";

    @Inject
    public MainPresenterImpl() {
    }

    @Override
    public void clickMenuItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_mvp:
                mainView.showFragment(LoginFragment.newInstance(), R.id.fg_container);
                mainView.setTitle(MVP_DEMO);
                break;

            case R.id.property:
                break;

            case R.id.view:
                mainView.showFragment(ViewAnimationFragment.newInstance(), R.id.fg_container);
                mainView.setTitle(VIEW_ANIMATION);
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
