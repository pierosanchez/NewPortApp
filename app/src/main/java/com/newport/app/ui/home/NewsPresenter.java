package com.newport.app.ui.home;

import com.newport.app.data.models.response.HomeResponse;

/**
 * Created by tohure on 06/11/17.
 */

public class NewsPresenter implements NewsContract.Presenter, NewsContract.Callback {

    private NewsContract.View newsView;

    @Override
    public void attachedView(NewsContract.View view) {
        newsView = view;
    }

    @Override
    public void detachView() {
        newsView = null;
    }

    @Override
    public void makeHomeQuery() {
        newsView.showLoading();
        NewsInteractor.getHomeItems(this);
    }

    @Override
    public void getHomeSucces(HomeResponse homeResponse) {
        newsView.showHomeItems(homeResponse);
        newsView.hideLoading();
    }

    @Override
    public void getHomeError(String s) {
        newsView.showHomeItemsError(s);
    }

    @Override
    public void getHomeFailure(String message) {
        newsView.showHomeItemsError(message);
    }
}
