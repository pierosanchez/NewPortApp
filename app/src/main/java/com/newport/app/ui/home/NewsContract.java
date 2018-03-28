package com.newport.app.ui.home;

import com.newport.app.data.models.response.HomeResponse;
import com.newport.app.util.BasePresenter;

/**
 * Created by tohure on 06/11/17.
 */

interface NewsContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showHomeItems(HomeResponse homeResponse);

        void showHomeItemsError(String error);

    }

    interface Presenter extends BasePresenter<View> {

        void makeHomeQuery();

    }

    interface Callback {

        void getHomeSucces(HomeResponse homeResponse);

        void getHomeError(String s);

        void getHomeFailure(String message);
    }

}
