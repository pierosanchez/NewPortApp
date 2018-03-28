package com.newport.app.util;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.newport.app.R;

/**
 * Created by tohure on 11/11/17.
 */

public final class WebViewActivity extends Activity {
    /**
     * Optional title resource for the actionbar / toolbar.
     */
    public static final String EXTRA_TITLE = WebViewActivity.class.getName() + ".EXTRA_TITLE";

    /**
     * Mandatory file to load and display.
     */
    public static final String EXTRA_URL = WebViewActivity.class.getName() + ".EXTRA_URL";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String url = getIntent().getStringExtra(EXTRA_URL);

        final ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (title != null) {
                actionBar.setTitle(title);
                actionBar.setSubtitle(url);
            } else {
                actionBar.setTitle(url);
            }
        }

        WebView webView = findViewById(R.id.web_view);
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);

        // No title provided. Use the website's once it's loaded...
        if (title == null) {
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if (actionBar != null) {
                        actionBar.setTitle(view.getTitle());
                        actionBar.setSubtitle(url);
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}