package com.demo.panju.androidapp.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.demo.panju.androidapp.inject.component.AppComponent;

import butterknife.ButterKnife;

/**
 * Basic activity of all activities,including common function
 * Author : PZY
 * Date : 2016.7.11
 */
public abstract class BaseActivity extends AppCompatActivity{

    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());

        ButterKnife.bind(this);

        AppManager.getInstance().addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
        AppManager.getInstance().finishAllActivity();
    }

    public abstract int layout();

    public abstract void initWidget();

    public abstract void registerListener();

    protected void replaceFragment(Fragment fragment, int containerViewId) {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();

        mFragmentTransaction.replace(containerViewId, fragment);
        mFragmentTransaction.commit();

    }

    protected void hideFragment(Fragment fragment) {
        if (null != fragment) {
            mFragmentTransaction.hide(fragment);
        }
    }

    protected AppComponent getAppComponent() {
        return ((MyApplication)getApplication()).getAppComponent();
    }
}
