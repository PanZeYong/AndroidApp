package com.demo.panju.androidapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.demo.panju.androidapp.animation.fragment.ViewAnimationFragment;
import com.demo.panju.androidapp.base.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.fg_container)
    FrameLayout mContainer;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    @BindString(R.string.view_animation) String mViewAnimation;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
        registerListener();
    }

    @Override
    public int layout() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        mToolbar = (Toolbar)findViewById(R.id.tool_bar);
        mContainer = (FrameLayout) findViewById(R.id.fg_container);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        initToolbar();
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
            switch (item.getItemId()) {
                case R.id.property:
                    break;

                case R.id.view:
                    replaceFragment(ViewAnimationFragment.newInstance(), R.id.fg_container);
                    mToolbar.setTitle(mViewAnimation);
                    break;

                default:
                    break;
            }
            item.setChecked(true);
            mDrawer.closeDrawers();
            return true;
        }
    };
}
