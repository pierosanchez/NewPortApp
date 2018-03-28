package com.newport.app.ui.profile;

import com.newport.app.data.models.response.UserResponse;

/**
 * Created by tohure on 05/12/17.
 */

public class ProfilePresenter implements ProfileContract.Presenter, ProfileContract.Callback {

    private ProfileContract.View view;

    @Override
    public void getUserInfo() {
        view.showLoading();
        ProfileInteractor.getUserInfo(this);
    }

    @Override
    public void attachedView(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getUserInfoSucces(UserResponse userResponse) {
        view.showUserInfo(userResponse);
        view.hideLoading();
    }

    @Override
    public void getUserInfoError(String error) {
        view.showUserInfoError(error);
        view.hideLoading();
    }

    @Override
    public void getUserInfoFailure(String message) {
        view.showUserInfoError(message);
        view.hideLoading();
    }
}
