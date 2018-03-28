package com.newport.app.util;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by tohure on 11/11/17.
 */

final class KeepAliveService extends Service {
    private static final Binder binder = new Binder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
