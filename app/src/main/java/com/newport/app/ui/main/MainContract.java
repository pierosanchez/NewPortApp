package com.newport.app.ui.main;

import com.newport.app.util.BasePresenter;

/**
 * Created by tohure on 07/12/17.
 */

interface MainContract {

    interface View {

        void expireCarnet();
    }

    interface Presenter extends BasePresenter<View> {

        void calculateDiferencesDay();

    }

}
