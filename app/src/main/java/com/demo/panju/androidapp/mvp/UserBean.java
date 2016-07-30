package com.demo.panju.androidapp.mvp;

/**
 * Author : ZPY
 * Date : 2016.7.30
 */
public class UserBean {

    private int userId;
    private String userName;
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  "id = " + this.userId + "\n" +
                "username = " + this.userName + "\n" +
                "password = " + this.password;
    }
}
