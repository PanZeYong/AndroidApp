package com.demo.panju.androidapp.inject.component;

import android.content.Context;

import com.demo.panju.androidapp.base.MyApplication;
import com.demo.panju.androidapp.inject.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author : PZY
 * Date : 2016.8.1
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();
}
