package com.newport.app.ui.directory;

import com.newport.app.data.models.response.DirectoryResponse;
import com.newport.app.util.BasePresenter;

import java.util.List;

/**
 * Created by tohure on 12/11/17.
 */

interface DirectoryContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showDirectory(List<DirectoryResponse> sectionDirectoryList);

        void showDirectoryError(String message);
    }

    interface Presenter extends BasePresenter<View> {

        void makeDirectoryQuery();

    }

    interface Callback {

        void getDirectorySucces(List<DirectoryResponse> directoryList);

        void getDirectoryError(String error);

        void getDirectoryFailure(String message);
    }

}