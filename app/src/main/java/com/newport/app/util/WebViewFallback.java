package com.newport.app.util;

/**
 * Created by tohure on 11/11/17.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Default {@link CustomTabHelper.CustomTabFallback} implementation
 * that uses {@link WebViewActivity} to display the requested {@link Uri}.
 */
public final class WebViewFallback implements CustomTabHelper.CustomTabFallback {
    /**
     * @param context The {@link Context} that wants to open the Uri
     * @param uri     The {@link Uri} to be opened by the fallback
     */
    @Override
    public void openUri(final Context context, final Uri uri) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_URL, uri.toString());
        context.startActivity(intent);
    }
}
