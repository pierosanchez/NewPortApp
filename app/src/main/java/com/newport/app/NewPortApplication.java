package com.newport.app;

import android.app.Application;
import android.content.Context;

import com.google.firebase.messaging.FirebaseMessaging;
import com.newport.app.util.CustomTabsActivityLifecycleCallbacks;

/**
 * Created by tohure on 11/11/17.
 */

public class NewPortApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseMessaging.getInstance().subscribeToTopic("general");
        registerActivityLifecycleCallbacks(new CustomTabsActivityLifecycleCallbacks());
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}