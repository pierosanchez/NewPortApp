package com.newport.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.newport.app.NewPortApplication;
import com.newport.app.ui.login.LoginActivity;
import com.newport.app.ui.main.MainActivity;
import com.newport.app.util.PreferencesHeper;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        //Save Current Display Phone
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        PreferencesHeper.setWidthSystem(this, metrics.widthPixels);
        PreferencesHeper.setHeightSystem(this, metrics.heightPixels);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;

                if (PreferencesHeper.getDniUser(NewPortApplication.getAppContext()).equals("")) {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }

                startActivity(intent);
                finish();
            }
        }, 1000);

    }
}
