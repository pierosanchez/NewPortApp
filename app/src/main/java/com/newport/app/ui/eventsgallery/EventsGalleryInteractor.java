package com.newport.app.ui.eventsgallery;

import android.support.annotation.NonNull;

import com.google.firebase.perf.metrics.AddTrace;
import com.newport.app.NewPortApplication;
import com.newport.app.R;
import com.newport.app.data.api.NewPortApiManager;
import com.newport.app.data.models.request.PhotoRequest;
import com.newport.app.data.models.response.PhotoGalleryEventResponse;
import com.newport.app.data.models.response.PhotoUploadedResponse;
import com.newport.app.util.PreferencesHeper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tohure on 08/11/17.
 */

class EventsGalleryInteractor {

    @AddTrace(name = "getPhotosGallery")
    static void getPhotosGallery(int newId, final EventsGalleryContract.Callback callback) {

        Call<List<PhotoGalleryEventResponse>> call = NewPortApiManager.apiManager().getPhotosEvent(newId);

        call.enqueue(new Callback<List<PhotoGalleryEventResponse>>() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onResponse(@NonNull Call<List<PhotoGalleryEventResponse>> call, @NonNull Response<List<PhotoGalleryEventResponse>> response) {

                if (response.isSuccessful()) {
                    if (!response.body().isEmpty()) {
                        callback.getPhotosGallerySucces(response.body());
                    } else {
                        callback.getSendPhotosGalleryEmpty(NewPortApplication.getAppContext().getString(R.string.fotos_empty));
                    }
                } else {
                    callback.getPhotosGalleryError(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PhotoGalleryEventResponse>> call, @NonNull Throwable t) {
                //callback.getPhotosGalleryFailure(t.getMessage());
                callback.getPhotosGalleryFailure(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
            }
        });

    }

    @AddTrace(name = "savePhotoGallery")
    static void savePhotoGallery(int newId, String image64, final EventsGalleryContract.Callback callback) {

        PhotoRequest photoRequest = new PhotoRequest();
        photoRequest.setUser_code(PreferencesHeper.getSapCodeUser(NewPortApplication.getAppContext()));
        photoRequest.setImage(image64);
        photoRequest.setNews_id(String.valueOf(newId));

        Call<PhotoUploadedResponse> call = NewPortApiManager.apiManager().sendPhoto(photoRequest);

        call.enqueue(new Callback<PhotoUploadedResponse>() {
            @Override
            public void onResponse(@NonNull Call<PhotoUploadedResponse> call, @NonNull Response<PhotoUploadedResponse> response) {

                if (response.isSuccessful()) {
                    callback.getSendPhotosGallerySucces(response.body());
                } else {
                    callback.getSendPhotosGalleryError("Error al subir foto");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotoUploadedResponse> call, @NonNull Throwable t) {
                //callback.getSendPhotosGalleryFailure(t.getMessage());
                callback.getSendPhotosGalleryFailure(NewPortApplication.getAppContext().getString(R.string.conectivity_error));
            }
        });
    }
}