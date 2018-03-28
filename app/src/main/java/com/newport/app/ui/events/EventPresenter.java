package com.newport.app.ui.events;

import com.newport.app.data.models.response.EventsResponse;

import java.util.List;

/**
 * Created by tohure on 15/11/17.
 */

public class EventPresenter implements EventsContract.Presenter, EventsContract.Callback {

    private EventsContract.View eventView;

    @Override
    public void getEvents() {
        eventView.showLoading();
        EventInteractor.getEvents(this);
    }

    @Override
    public void attachedView(EventsContract.View eventView) {
        this.eventView = eventView;
    }

    @Override
    public void detachView() {
        eventView = null;
    }

    @Override
    public void getEventsSucces(List<EventsResponse> newsResponseList) {
        eventView.showEvents(newsResponseList);
        eventView.hideLoading();
    }

    @Override
    public void getEventsError(String error) {
        eventView.hideLoading();
        eventView.showEventsError(error);
    }

    @Override
    public void getEventsFailure(String message) {
        eventView.hideLoading();
        eventView.showEventsError(message);
    }
}