package com.newport.app.ui.events;

import com.newport.app.data.models.response.EventsResponse;
import com.newport.app.util.BasePresenter;

import java.util.List;

/**
 * Created by tohure on 15/11/17.
 */

public interface EventsContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showEvents(List<EventsResponse> newsResponseList);

        void showEventsError(String error);

    }

    interface Presenter extends BasePresenter<View> {

        void getEvents();

    }

    interface Callback {

        void getEventsSucces(List<EventsResponse> newsResponseList);

        void getEventsError(String error);

        void getEventsFailure(String message);
    }

}
