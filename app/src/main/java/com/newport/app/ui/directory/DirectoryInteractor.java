package com.newport.app.ui.directory;

import android.support.annotation.NonNull;

import com.newport.app.NewPortApplication;
import com.newport.app.R;
import com.newport.app.data.api.NewPortApiManager;
import com.newport.app.data.models.response.DirectoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tohure on 12/11/17.
 */

class DirectoryInteractor {

    static void getDirectory(final DirectoryContract.Callback callback) {

        Call<List<DirectoryResponse>> call = NewPortApiManager.apiManager().getDirectory();

        call.enqueue(new Callback<List<DirectoryResponse>>() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onResponse(@NonNull Call<List<DirectoryResponse>> call, @NonNull Response<List<DirectoryResponse>> response) {

                if (response.isSuccessful()) {
                    if (!response.body().isEmpty()) {
                        callback.getDirectorySucces(response.body());
                    } else {
                        callback.getDirectoryError(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
                    }
                } else {
                    callback.getDirectoryError(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DirectoryResponse>> call, @NonNull Throwable t) {
                //callback.getDirectoryFailure(t.getMessage());
                callback.getDirectoryFailure(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
            }
        });
    }
}
