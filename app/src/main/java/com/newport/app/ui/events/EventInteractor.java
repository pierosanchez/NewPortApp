package com.newport.app.ui.events;

import android.support.annotation.NonNull;
import android.util.Log;

import com.newport.app.NewPortApplication;
import com.newport.app.R;
import com.newport.app.data.api.NewPortApiManager;
import com.newport.app.data.models.response.EventsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tohure on 15/11/17.
 */

class EventInteractor {

    static void getEvents(final EventsContract.Callback callback) {
        Call<List<EventsResponse>> call = NewPortApiManager.apiManager().getEvents();

        call.enqueue(new Callback<List<EventsResponse>>() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onResponse(@NonNull Call<List<EventsResponse>> call, @NonNull Response<List<EventsResponse>> response) {

                if (response.isSuccessful()) {
                    if (!response.body().isEmpty()) {

                        callback.getEventsSucces(response.body());
                        Log.d("getEventsIteractor", response.body().get(0).getSubcategory());

                    } else {
                        callback.getEventsError("No hay noticias");
                    }
                } else {
                    callback.getEventsError(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<EventsResponse>> call, @NonNull Throwable t) {
                //callback.getEventsFailure(t.getMessage());
                callback.getEventsFailure(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
            }
        });
    }
}