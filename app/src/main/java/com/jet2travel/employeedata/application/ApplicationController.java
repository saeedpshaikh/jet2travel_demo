package com.jet2travel.employeedata.application;

import android.app.Activity;
import android.content.Context;

import androidx.multidex.MultiDexApplication;

import java.util.ArrayDeque;
import java.util.Deque;

public class ApplicationController extends MultiDexApplication {

    private static ApplicationController applicationController;
    private static Context context;
    private static Deque<Activity> activityStack = new ArrayDeque<>();

    public static synchronized ApplicationController getApplicationController() {
        return applicationController;
    }

    public static synchronized void setApplicationController(ApplicationController applicationController) {
        ApplicationController.applicationController = applicationController;
    }


    public static synchronized Context getContext() {
        return context;
    }

    public static synchronized void setContext(Context context) {
        ApplicationController.context = context;
    }

    public static synchronized Deque<Activity> getActivityStack() {
        return activityStack;
    }


    public void onCreate() {
        super.onCreate();
        setContext(this);
        setApplicationController(this);
    }
}
