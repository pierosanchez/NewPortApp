package com.newport.app.ui.schedules;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.newport.app.R;
import com.newport.app.data.models.response.ScheduleResponse;
import com.newport.app.util.PreferencesHeper;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchedulesFragment extends Fragment implements ScheduleContract.View, ScheduleAdapter.OnClickSchedulerListener {

    private SchedulePresenter schedulePresenter;
    private ScheduleAdapter scheduleAdapter;
    private RelativeLayout rltProgress;
    private RecyclerView rcvScheduels;

    private FirebaseAnalytics mFirebaseAnalytics;

    private View rootView;

    public SchedulesFragment() {
        // Required empty public constructor
    }

    public static SchedulesFragment newInstance() {
        return new SchedulesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_schedules, container, false);
        init();
        return rootView;
    }

    private void init() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());

        rltProgress = rootView.findViewById(R.id.rltProgress);

        rcvScheduels = rootView.findViewById(R.id.rcvScheduels);
        rcvScheduels.setHasFixedSize(true);

        //Get width system
        int width = PreferencesHeper.getWidthSystem(getActivity());

        scheduleAdapter = new ScheduleAdapter(width);
        scheduleAdapter.setOnScheduleClickListener(this);
        rcvScheduels.setAdapter(scheduleAdapter);

        schedulePresenter = new SchedulePresenter();
        schedulePresenter.attachedView(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        schedulePresenter.getSchedules();
    }

    @Override
    public void showLoading() {
        rltProgress.setVisibility(View.VISIBLE);
        rcvScheduels.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        rltProgress.setVisibility(View.GONE);
        rcvScheduels.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSchedules(List<ScheduleResponse> scheduleResponseList) {
        scheduleAdapter.addData(scheduleResponseList);
    }

    @Override
    public void showSchedulesError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScheduleItemClick(ScheduleResponse scheduleResponse) {

        //Track Event
        Bundle bundle = new Bundle();
        bundle.putString("casino_id", scheduleResponse.getId() + "");

        if (scheduleResponse.getFile_url().equals("")) {
            Toast.makeText(getActivity(), "Horario aún no disponible", Toast.LENGTH_SHORT).show();
            bundle.putString("schedule", "not available");
        } else {
            bundle.putString("schedule", "available");

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            builder.setToolbarColor(this.getResources().getColor(R.color.colorPrimary));
            builder.setShowTitle(true);
            builder.setStartAnimations(getActivity(), android.R.anim.fade_in, android.R.anim.fade_out);
            builder.setExitAnimations(getActivity(), android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            customTabsIntent.launchUrl(getActivity(), Uri.parse(scheduleResponse.getFile_url()));
        }

        mFirebaseAnalytics.logEvent("see_schedule", bundle);
    }
}