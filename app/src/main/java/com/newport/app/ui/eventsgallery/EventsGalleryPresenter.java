package com.newport.app.ui.eventsgallery;

import com.newport.app.data.models.response.PhotoGalleryEventResponse;
import com.newport.app.data.models.response.PhotoUploadedResponse;

import java.util.List;

/**
 * Created by tohure on 08/11/17.
 */

public class EventsGalleryPresenter implements EventsGalleryContract.Presenter, EventsGalleryContract.Callback {

    private EventsGalleryContract.View view;

    @Override
    public void getPhotosGalleryEvent(int newId) {
        view.showLoading();
        EventsGalleryInteractor.getPhotosGallery(newId, this);
    }

    @Override
    public void savePhotoGalleryEvent(int newId, String image64) {
        //view.showLoading();
        EventsGalleryInteractor.savePhotoGallery(newId, image64, this);
    }

    @Override
    public void attachedView(EventsGalleryContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getPhotosGallerySucces(List<PhotoGalleryEventResponse> body) {
        view.showPhotosEvent(body);
        view.hideLoading();
    }

    @Override
    public void getPhotosGalleryError(String error) {
        view.hideLoading();
        view.showPhotosError(error);
    }

    @Override
    public void getPhotosGalleryFailure(String message) {
        view.hideLoading();
        view.showPhotosError(message);
    }

    @Override
    public void getSendPhotosGallerySucces(PhotoUploadedResponse photo) {
        view.reloadGalleryPhotos();
        view.hideLoading();
    }

    @Override
    public void getSendPhotosGalleryError(String error) {
        view.showPhotosError(error);
        view.hideLoading();
    }

    @Override
    public void getSendPhotosGalleryEmpty(String message) {
        view.showPhotosEmpty(message);
        view.hideLoading();
    }

    @Override
    public void getSendPhotosGalleryFailure(String message) {
        view.showPhotosError(message);
        view.hideLoading();
    }
}
