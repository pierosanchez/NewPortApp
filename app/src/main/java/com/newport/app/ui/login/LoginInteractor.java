package com.newport.app.ui.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.perf.metrics.AddTrace;
import com.newport.app.NewPortApplication;
import com.newport.app.R;
import com.newport.app.data.api.NewPortApiManager;
import com.newport.app.data.models.response.LoginResponse;
import com.newport.app.util.PreferencesHeper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tohure on 30/11/17.
 */

class LoginInteractor {

    @AddTrace(name = "login")
    static void login(String dni, final LoginContract.Callback callback) {
        Call<LoginResponse> call = NewPortApiManager.apiManager().login(dni);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("success")) {
                        callback.getLoginSucces();
                        saveUserData(response.body());
                    } else {
                        callback.getLoginError(NewPortApplication.getAppContext().getString(R.string.login_error));
                    }
                } else if (response.code() == 401) {
                    callback.getLoginError(NewPortApplication.getAppContext().getString(R.string.dni_unauthorized));
                } else {
                    callback.getLoginError(NewPortApplication.getAppContext().getString(R.string.login_error));
                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                //callback.getLoginFailure(t.getMessage());
                callback.getLoginFailure(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
            }
        });
    }

    private static void saveUserData(LoginResponse loginResponse) {
        PreferencesHeper.setDniUser(NewPortApplication.getAppContext(), loginResponse.getDni());
        PreferencesHeper.setSapCodeUser(NewPortApplication.getAppContext(), loginResponse.getSap_code());
    }
}
