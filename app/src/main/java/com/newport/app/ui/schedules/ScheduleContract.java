package com.newport.app.ui.schedules;

import com.newport.app.data.models.response.ScheduleResponse;
import com.newport.app.util.BasePresenter;

import java.util.List;

/**
 * Created by tohure on 15/11/17.
 */

public interface ScheduleContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showSchedules(List<ScheduleResponse> scheduleResponseList);

        void showSchedulesError(String error);

    }

    interface Presenter extends BasePresenter<View> {

        void getSchedules();

    }

    interface Callback {

        void getSchedulesSucces(List<ScheduleResponse> scheduleResponseList);

        void getSchedulesError(String error);

        void getSchedulesFailure(String message);
    }

}
