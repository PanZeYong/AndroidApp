package com.demo.panju.androidapp.ui.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.base.BaseFragment;
import com.demo.panju.androidapp.inject.HasComponent;
import com.demo.panju.androidapp.inject.component.MainComponent;
import com.demo.panju.androidapp.mvp.presenter.LoginPresenterImpl;
import com.demo.panju.androidapp.mvp.view.LoginView;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public class LoginFragment extends BaseFragment implements LoginView {
    @BindView(R.id.et_username)
    EditText mUsername;
    @BindView(R.id.et_password)
    EditText mPassword;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Inject LoginPresenterImpl mLoginPresenterImpl;

    private String absolutePath;
    private String path;
    private String publicPath;
    private String free;
    private String total;

    private String sd;

    @TargetApi(Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        this.absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        this.path = Environment.getExternalStorageDirectory().getPath();
        this.publicPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
        this.free = Environment.getExternalStorageDirectory().getFreeSpace() / 1024 / 1024 + "M";
        this.total = Environment.getExternalStorageDirectory().getTotalSpace() / 1024 / 1024 + "M";
        try {
            StorageManager manager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);

            Method methodGetPaths = manager.getClass().getMethod("getVolumePaths");

            String [] paths = (String[]) methodGetPaths.invoke(manager);

            for (int i = 0; i < paths.length; i++) {
                Log.e("TAG", paths[i]);
            }

            StatFs statFs = new StatFs(paths[1] + "/");
            Log.e("TAG", statFs.getAvailableBytes()+"");
            Log.e("TAG", statFs.getBlockSizeLong() + "");
            Log.e("TAG", statFs.getFreeBytes() + "");


            Log.e("TAG", checkMounted(mContext, paths[1])+"");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    protected void init() {
        getComponent(MainComponent.class).inject(this);
        mLoginPresenterImpl.attachView(this);
    }

    @Override
    public int getUserId() {
        return 0;
    }

    @Override
    public String getUsername() {
        return mUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString().trim();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_login)
    public void login() {
        mLoginPresenterImpl.login(getUserId(), getUsername(), getPassword());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLoginPresenterImpl.detachView();
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @SuppressWarnings("unchecked") protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    /** * 检查是否挂载 */
    public static boolean checkMounted(Context context, String mountPoint) {
        if (mountPoint == null) {
            return false;
        }
        StorageManager storageManager = (StorageManager) context
                .getSystemService(Context.STORAGE_SERVICE);
        try {
            Method getVolumeState = storageManager.getClass().getMethod(
                    "getVolumeState", String.class);
            String state = (String) getVolumeState.invoke(storageManager,
                    mountPoint);
            return Environment.MEDIA_MOUNTED.equals(state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



}
