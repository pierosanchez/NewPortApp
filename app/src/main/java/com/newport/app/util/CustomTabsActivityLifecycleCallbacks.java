package com.newport.app.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by tohure on 11/11/17.
 */

public final class CustomTabsActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private CustomTabHelper customTabsHelper;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        customTabsHelper = new CustomTabHelper();
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        customTabsHelper.bindCustomTabsService(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        customTabsHelper.unbindCustomTabsService(activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}