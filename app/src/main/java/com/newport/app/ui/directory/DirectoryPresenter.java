package com.newport.app.ui.directory;

import com.newport.app.data.models.response.DirectoryResponse;

import java.util.List;

/**
 * Created by tohure on 12/11/17.
 */

public class DirectoryPresenter implements DirectoryContract.Presenter, DirectoryContract.Callback {

    private DirectoryContract.View directoryView;

    @Override
    public void makeDirectoryQuery() {
        directoryView.showLoading();
        DirectoryInteractor.getDirectory(this);
    }

    @Override
    public void attachedView(DirectoryContract.View directoryView) {
        this.directoryView = directoryView;
    }

    @Override
    public void detachView() {
        directoryView = null;
    }

    @Override
    public void getDirectorySucces(List<DirectoryResponse> directoryList) {
        directoryView.showDirectory(directoryList);
        directoryView.hideLoading();
    }

    @Override
    public void getDirectoryError(String error) {
        directoryView.hideLoading();
        directoryView.showDirectoryError(error);
    }

    @Override
    public void getDirectoryFailure(String message) {
        directoryView.hideLoading();
        directoryView.showDirectoryError(message);
    }
}