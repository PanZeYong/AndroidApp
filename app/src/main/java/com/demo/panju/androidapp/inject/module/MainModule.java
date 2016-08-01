package com.demo.panju.androidapp.inject.module;

import com.demo.panju.androidapp.mvp.presenter.MainPresenterImpl;
import com.demo.panju.androidapp.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author : PZY
 * Date : 2016.8.1
 */
@Module
public class MainModule {
    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @Singleton
    public MainActivity provideMainActivity() {
        return this.mainActivity;
    }
}
