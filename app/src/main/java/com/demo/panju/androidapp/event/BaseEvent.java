package com.demo.panju.androidapp.event;

import com.demo.panju.androidapp.constant.Constant;

/**
 * Author : PZY
 * Date : 2016.8.31
 */
public class BaseEvent {
    private Constant.Result result;

    public Constant.Result getResult() {
        return result;
    }

    public void setResult(Constant.Result result) {
        this.result = result;
    }
}
