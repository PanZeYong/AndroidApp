package com.demo.panju.androidapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.demo.panju.androidapp.R;
import com.demo.panju.androidapp.base.BaseFragment;
import com.demo.panju.androidapp.mvp.presenter.LoginPresenterImpl;
import com.demo.panju.androidapp.mvp.view.LoginView;

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

    private LoginPresenterImpl mLoginPresenterImpl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        mLoginPresenterImpl = new LoginPresenterImpl(mContext);
        mLoginPresenterImpl.attachView(this);
        return view;
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
}
