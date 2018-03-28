package com.newport.app.util;

/**
 * Created by tohure on 06/11/17.
 */

public interface BasePresenter<V> {
    void attachedView(V view);

    void detachView();
}
