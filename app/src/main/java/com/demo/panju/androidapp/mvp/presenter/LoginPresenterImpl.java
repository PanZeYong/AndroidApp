package com.demo.panju.androidapp.mvp.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.demo.panju.androidapp.mvp.model.UserModel;
import com.demo.panju.androidapp.mvp.model.UserModelImpl;
import com.demo.panju.androidapp.mvp.view.LoginView;

import javax.inject.Inject;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public class LoginPresenterImpl implements LoginPresenter {
    private UserModel mUserModel;
    private LoginView mLoginView;
    private Context mContext;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mLoginView.dismissProgress();
                    Toast.makeText(mContext, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Inject
    public LoginPresenterImpl(Context context) {
        this.mUserModel = new UserModelImpl();
        this.mContext = context;
    }

    @Override
    public void attachView(@NonNull LoginView view) {
        this.mLoginView = view;
    }

    @Override
    public void detachView() {
        mLoginView = null;
    }

    @Override
    public void login(final int id, final String username, final String password) {
        mLoginView.showProgress();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Message message = mHandler.obtainMessage();
                    message.what = 0;
                    message.obj = mUserModel.query(id, username, password);
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
