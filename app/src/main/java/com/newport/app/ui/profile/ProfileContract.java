package com.newport.app.ui.profile;

import com.newport.app.data.models.response.UserResponse;
import com.newport.app.util.BasePresenter;

/**
 * Created by tohure on 05/12/17.
 */

interface ProfileContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showUserInfo(UserResponse userResponse);

        void showUserInfoError(String error);

    }

    interface Presenter extends BasePresenter<View> {

        void getUserInfo();

    }

    interface Callback {

        void getUserInfoSucces(UserResponse userResponse);

        void getUserInfoError(String error);

        void getUserInfoFailure(String message);
    }
}
