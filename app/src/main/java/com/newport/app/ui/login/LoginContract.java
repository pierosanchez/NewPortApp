package com.newport.app.ui.login;

import com.newport.app.util.BasePresenter;

/**
 * Created by tohure on 29/11/17.
 */

public interface LoginContract {

    interface View {

        void showLoading();

        void hideLoading();

        void loginSucces();

        void loginError(String error);

    }

    interface Presenter extends BasePresenter<View> {

        void login(String dni);

    }

    interface Callback {

        void getLoginSucces();

        void getLoginError(String error);

        void getLoginFailure(String message);
    }
}
