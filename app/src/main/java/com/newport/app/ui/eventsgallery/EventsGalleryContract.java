package com.newport.app.ui.eventsgallery;

import com.newport.app.data.models.response.PhotoGalleryEventResponse;
import com.newport.app.data.models.response.PhotoUploadedResponse;
import com.newport.app.util.BasePresenter;

import java.util.List;

/**
 * Created by tohure on 08/11/17.
 */

interface EventsGalleryContract {


    interface View {

        void showLoading();

        void hideLoading();

        void showPhotosEvent(List<PhotoGalleryEventResponse> photoGalleryEventResponseList);
        void reloadGalleryPhotos();

        void showPhotosEmpty(String message);
        void showPhotosError(String error);

    }

    interface Presenter extends BasePresenter<View> {

        void getPhotosGalleryEvent(int newId);
        void savePhotoGalleryEvent(int newId, String image64);

    }

    interface Callback {

        void getPhotosGallerySucces(List<PhotoGalleryEventResponse> body);
        void getPhotosGalleryError(String error);
        void getPhotosGalleryFailure(String message);

        void getSendPhotosGallerySucces(PhotoUploadedResponse photo);
        void getSendPhotosGalleryError(String error);
        void getSendPhotosGalleryEmpty(String message);
        void getSendPhotosGalleryFailure(String message);
    }

}
