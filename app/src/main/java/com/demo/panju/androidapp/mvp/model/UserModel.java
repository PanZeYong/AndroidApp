package com.demo.panju.androidapp.mvp.model;

/**
 * Author : PZY
 * Date : 2016.7.30
 */
public interface UserModel {
    void setUserId(int userId);

    void setUsername(String username);

    void setPassword(String password);

    String query(int userId, String username, String password);

    void load();
}
