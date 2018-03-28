package com.newport.app.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import com.newport.app.R;

/**
 * Created by tohure on 05/11/17.
 */

@SuppressLint("Registered")
public class BaseActivity extends Activity {

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

}