package com.newport.app.ui.home;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.perf.metrics.AddTrace;
import com.newport.app.NewPortApplication;
import com.newport.app.R;
import com.newport.app.data.api.NewPortApiManager;
import com.newport.app.data.models.response.HomeResponse;
import com.newport.app.util.PreferencesHeper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tohure on 06/11/17.
 */

class NewsInteractor {

    @AddTrace(name = "getHomeItems")
    static void getHomeItems(final NewsContract.Callback callback) {

        String currentDni = PreferencesHeper.getDniUser(NewPortApplication.getAppContext());

        Call<HomeResponse> call = NewPortApiManager.apiManager().getHome(currentDni);

        call.enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(@NonNull Call<HomeResponse> call, @NonNull Response<HomeResponse> response) {

                if (response.isSuccessful()) {
                    callback.getHomeSucces(response.body());
                } else {
                    callback.getHomeError(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
                }

            }

            @Override
            public void onFailure(@NonNull Call<HomeResponse> call, @NonNull Throwable t) {
                //callback.getHomeFailure(t.getMessage());
                callback.getHomeFailure(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
            }
        });
    }
}
