package com.newport.app.ui.main;

import com.newport.app.NewPortApplication;
import com.newport.app.util.PreferencesHeper;

import java.util.Calendar;

/**
 * Created by tohure on 07/12/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Override
    public void calculateDiferencesDay() {

        //Verify Die Date Carnet
        int dayCarnet = PreferencesHeper.getDayExpiration(NewPortApplication.getAppContext());
        int monthCarnet = PreferencesHeper.getMonthExpiration(NewPortApplication.getAppContext());
        int yearCarnet = PreferencesHeper.getYearExpiration(NewPortApplication.getAppContext());

        if (dayCarnet != 0 && (monthCarnet+1) != 0 && yearCarnet != 0) {
            Calendar thatDay = Calendar.getInstance();
            thatDay.set(Calendar.DAY_OF_MONTH, dayCarnet);
            thatDay.set(Calendar.MONTH, monthCarnet);
            thatDay.set(Calendar.YEAR, yearCarnet);

            Calendar today = Calendar.getInstance();

            long diff = thatDay.getTimeInMillis() - today.getTimeInMillis();

            if (diff <= 864000000 && diff > 0) {
                view.expireCarnet();
            }
        }

    }

    @Override
    public void attachedView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

}
