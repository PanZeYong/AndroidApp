package com.demo.panju.androidapp.inject.component;

import com.demo.panju.androidapp.inject.module.MainModule;
import com.demo.panju.androidapp.inject.scope.ActivityScope;
import com.demo.panju.androidapp.mvp.view.GalleryView;
import com.demo.panju.androidapp.network.GalleryApi;
import com.demo.panju.androidapp.ui.activity.MainActivity;
import com.demo.panju.androidapp.ui.fragment.GalleryFragment;
import com.demo.panju.androidapp.ui.fragment.LoginFragment;
import com.demo.panju.androidapp.ui.fragment.MatrixFragment;
import com.demo.panju.androidapp.ui.fragment.PropertyAnimationFragment;
import com.demo.panju.androidapp.ui.fragment.ViewAnimationFragment;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Author : PZY
 * Date : 2016.8.1
 */
@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    GalleryApi getGalleryApi();
    OkHttpClient getOkHttpClient();

    void inject(MainActivity mainActivity);

    void inject(LoginFragment fragment);
    void inject(ViewAnimationFragment fragment);
    void inject(PropertyAnimationFragment fragment);
    void inject(MatrixFragment fragment);
    void inject(GalleryFragment fragment);
}
