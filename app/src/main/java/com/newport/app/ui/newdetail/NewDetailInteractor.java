package com.newport.app.ui.newdetail;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.perf.metrics.AddTrace;
import com.newport.app.NewPortApplication;
import com.newport.app.R;
import com.newport.app.data.api.NewPortApiManager;
import com.newport.app.data.models.response.NewResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tohure on 17/11/17.
 */

class NewDetailInteractor {

    @AddTrace(name = "getNew")
    static void getNew(int idNew, final NewDetailContract.Callback callback) {

        Call<NewResponse> call = NewPortApiManager.apiManager().getNewDetail(idNew);

        call.enqueue(new Callback<NewResponse>() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onResponse(@NonNull Call<NewResponse> call, @NonNull Response<NewResponse> response) {

                if (response.isSuccessful()) {
                    callback.getNewSucces(response.body());
                } else {
                    callback.getNewError(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
                }

            }

            @Override
            public void onFailure(@NonNull Call<NewResponse> call, @NonNull Throwable t) {
                //callback.getNewFailure(t.getMessage());
                callback.getNewFailure(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
            }
        });
    }
}
