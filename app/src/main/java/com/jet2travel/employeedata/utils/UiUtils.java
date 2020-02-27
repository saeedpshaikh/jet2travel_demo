package com.jet2travel.employeedata.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.Deque;

public class UiUtils {
    private static UiUtils instance = null;

    protected UiUtils() {
    }

    public static UiUtils getInstance() {
        if (instance == null) {
            instance = new UiUtils();
        }
        return instance;
    }

    public void switchToActivity(Activity current, Class<? extends Activity> otherActivityClass, Bundle extras) {
        Intent intent = new Intent(current, otherActivityClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        current.startActivity(intent);
        current.finish();
    }

    /**
     * Intent back stack with data with bundle
     */
    public void goToActivity(Activity current, Class<? extends Activity> otherActivityClass, Bundle extras) {
        Intent intent = new Intent(current, otherActivityClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        current.startActivity(intent);
    }

    public void clearActivityStack(Deque<Activity> activityStack) {
        for (Activity activity : activityStack) {
            activity.finish();
        }
        activityStack.clear();
    }

    /**
     * we get status of internate connection.
     */

    public static boolean isConnected(Activity activity) {
        NetworkInfo info = getNetworkInfo(activity);
        return (info != null && info.isConnected());
    }

    public static NetworkInfo getNetworkInfo(Activity activity) {
        ConnectivityManager connectivityManager = getConnectivityManager(activity);
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        } else {
            return null;
        }
    }

    private static ConnectivityManager getConnectivityManager(Activity activity) {
        return (ConnectivityManager) activity.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static void showToastForLongTime(Context context, String msg) {
        if(context == null || TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
