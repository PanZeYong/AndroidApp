package com.demo.panju.androidapp.inject.component;

import com.demo.panju.androidapp.inject.module.MainModule;
import com.demo.panju.androidapp.inject.scope.ActivityScope;
import com.demo.panju.androidapp.ui.activity.MainActivity;
import com.demo.panju.androidapp.ui.fragment.LoginFragment;
import com.demo.panju.androidapp.ui.fragment.PropertyAnimationFragment;
import com.demo.panju.androidapp.ui.fragment.ViewAnimationFragment;

import dagger.Component;

/**
 * Author : PZY
 * Date : 2016.8.1
 */
@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);

    void inject(LoginFragment fragment);
    void inject(ViewAnimationFragment fragment);
    void inject(PropertyAnimationFragment fragment);
}
