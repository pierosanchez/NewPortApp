package com.newport.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.newport.app.R;
import com.newport.app.ui.main.MainActivity;

public class ValidationCodeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_code);
    }

    public void accesLogin(View view) {
        Intent intent = new Intent(ValidationCodeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
