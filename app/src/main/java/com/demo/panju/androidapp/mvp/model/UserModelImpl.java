package com.demo.panju.androidapp.mvp.model;

import android.util.SparseArray;

import com.demo.panju.androidapp.bean.UserBean;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public class UserModelImpl implements UserModel {
    private int userId;
    private String username;
    private String password;

    private SparseArray<UserBean> mUserList = new SparseArray<>();

    private final static String SUCCESS = "恭喜你，登陆成功！";
    private final static String FAIL = "密码或用户名不正确";
    private final static String NOT_EXIST = "该用户名不存在";

    public UserModelImpl() {
        initDate();
    }

    @Override
    public void setUserId(int userId) {
        this.userId= userId;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String query(int userId, String username, String password) {
        String message = null;

        if (null != mUserList.get(userId)) {
            if (username.equals(mUserList.get(userId).getUserName()) &&
                    password.equals(mUserList.get(userId).getPassword())) {
                message = SUCCESS;
            } else {
                message = FAIL;
            }
        } else {
            message = NOT_EXIST;
        }

        return message;
    }


    @Override
    public void load() {

    }

    private void initDate() {
        UserBean bean = new UserBean();
        bean.setId(0);
        bean.setUserName("pzy");
        bean.setPassword("159357+");
        mUserList.append(0, bean);
    }
}
