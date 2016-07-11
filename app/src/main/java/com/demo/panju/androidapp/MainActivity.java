package com.demo.panju.androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.panju.androidapp.base.BaseActivity;

import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int layout() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void registerListener() {

    }
}
