package com.demo.panju.androidapp.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Activity manager class of application : manager activity and application exited
 * Author : PZY
 * PZY : 2016.7.7
 */
public class AppManager {
    private final static String TAG = "AppManager";

    private static AppManager instance;

    private Stack<Activity> mActivityStack;

    private AppManager() {}

    /**
     * Singleton Pattern
     * @return
     */
    public static AppManager getInstance() {
        if (null == instance) {
            synchronized (AppManager.class) {
                if (null == instance) {
                    instance = new AppManager();
                }
            }
        }

        return instance;
    }

    /**
     * Add activity to stack
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (null == mActivityStack) {
            mActivityStack = new Stack<>();
        }

        mActivityStack.add(activity);
    }

    /**
     * Get current activity (that is to say,the stack top element)
     * @return
     */
    public Activity getCurrentActivity() {
        return mActivityStack.lastElement();
    }

    /**
     * Finish current activity
     */
    public void finishActiviy() {
        finishActivity(mActivityStack.lastElement());
    }

    /**
     * Finish assigned class name activity
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * Finish assigned activity
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (null != activity) {
            mActivityStack.remove(activity);
            activity.finish();
        }

    }

    /**
     * Finish all activities
     */
    public void finishAllActivity() {
        for (Activity activity : mActivityStack) {
            if (null != activity) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }

        mActivityStack.clear();
    }

    /**
     * Exit application
     * @param context
     */
    public void exit(Context context) {
        try {
            finishAllActivity();
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            manager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {

        }

    }
}
