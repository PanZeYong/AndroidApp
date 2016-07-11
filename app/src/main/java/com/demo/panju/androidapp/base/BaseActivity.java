package com.demo.panju.androidapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Basic activity of all activities,including common function
 * Author : PZY
 * Date : 2016.7.11
 */
public abstract class BaseActivity extends AppCompatActivity{
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layout());
        AppManager.getInstance().addActivity(this);
        initWidget();

        this.context = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishAllActivity();
    }

    public abstract int layout();

    public abstract void initWidget();

    public abstract void registerListener();
}
