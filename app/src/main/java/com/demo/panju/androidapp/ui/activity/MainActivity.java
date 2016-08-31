package com.demo.panju.androidapp.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.base.BaseActivity;
import com.demo.panju.androidapp.inject.HasComponent;
import com.demo.panju.androidapp.inject.component.DaggerMainComponent;
import com.demo.panju.androidapp.inject.component.MainComponent;
import com.demo.panju.androidapp.inject.module.MainModule;
import com.demo.panju.androidapp.mvp.presenter.impl.MainPresenterImpl;
import com.demo.panju.androidapp.mvp.view.MainView;

import javax.inject.Inject;

import butterknife.BindView;


public class MainActivity extends BaseActivity
        implements MainView, HasComponent<MainComponent>{

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    public MainComponent mainComponent;

    @Inject MainPresenterImpl mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMainComponent();
        initWidget();
        registerListener();
    }

    private void initMainComponent() {
        mainComponent = DaggerMainComponent.builder()
                        .appComponent(getAppComponent())
                        .mainModule(new MainModule(this))
                        .build();

        mainComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public int layout() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        initToolbar();

        mainPresenter.attachView(this);
    }

    @Override
    public void registerListener() {
        mDrawer.setDrawerListener(mActionBarDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    private void initToolbar() {
        if (null == mToolbar) {
            throw new IllegalStateException("Layout is required to include a Toolbar with id " +
                    "'tool_bar'");
        }

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (null != mDrawer) {
            if (null == mNavigationView) {
                throw new IllegalStateException("Layout is required to include a NavigationView with id " +
                        "'navigation_view'");
            }

            mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_close) {
                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    invalidateOptionsMenu();
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    invalidateOptionsMenu();
                }
            };
            mActionBarDrawerToggle.syncState();
        }
    }

    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            mainPresenter.clickMenuItem(item);
            return true;
        }
    };

    @Override
    public void showFragment(Fragment fragment, int container) {
        replaceFragment(fragment, container);
    }

    @Override
    public void setTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void closeDrawer() {
        mDrawer.closeDrawers();
    }

    @Override
    public void openDrawer() {

    }

    @Override
    public MainComponent getComponent() {
        return this.mainComponent;
    }
}
