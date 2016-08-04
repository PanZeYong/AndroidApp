package com.demo.panju.androidapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.base.BaseActivity;

/**
 * Author : PZY
 * Date : 2016.8.4
 */
public class CustomAnimationActivity extends BaseActivity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public int layout() {
        return R.layout.activity_custom_animation;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void registerListener() {

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CustomAnimationActivity.class);
        context.startActivity(intent);
    }
}
