package com.newport.app.ui.events;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.newport.app.NewPortApplication;
import com.newport.app.R;
import com.newport.app.data.models.response.EventsResponse;
import com.newport.app.ui.newdetail.NewDetailFragment;
import com.newport.app.util.Constant;
import com.newport.app.util.PreferencesHeper;

import java.util.List;

public class EventsFragment extends Fragment implements EventsContract.View, EventAdapter.OnClickEventListener, SwipeRefreshLayout.OnRefreshListener {

    private EventPresenter eventPresenter;
    private EventAdapter eventAdapter;

    private SwipeRefreshLayout srlEvents;
    private CoordinatorLayout crdEvents;

    private View rootView;
    private RelativeLayout rltProgress;
    private RecyclerView rcvScheduels;

    public EventsFragment() {
        // Required empty public constructor
    }

    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_events, container, false);
        init();
        return rootView;
    }

    private void init() {
        srlEvents = rootView.findViewById(R.id.srlEvents);
        srlEvents.setColorSchemeResources(R.color.colorPrimaryDark,
                R.color.colorPrimary,
                R.color.colorAccent);
        crdEvents = rootView.findViewById(R.id.crdEvents);

        eventPresenter = new EventPresenter();
        eventPresenter.attachedView(this);

        rltProgress = rootView.findViewById(R.id.rltProgress);

        rcvScheduels = rootView.findViewById(R.id.rcvScheduels);
        rcvScheduels.setHasFixedSize(true);

        eventAdapter = new EventAdapter();
        eventAdapter.setOnEventClickListener(this);
        rcvScheduels.setAdapter(eventAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventPresenter.getEvents();
    }

    @Override
    public void showLoading() {
        srlEvents.setOnRefreshListener(null);
        rltProgress.setVisibility(View.VISIBLE);
        rcvScheduels.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        rltProgress.setVisibility(View.GONE);
        rcvScheduels.setVisibility(View.VISIBLE);
        if (srlEvents.isRefreshing()) {
            srlEvents.setRefreshing(false);
        }
    }

    @Override
    public void showEvents(List<EventsResponse> newsResponseList) {
        srlEvents.setOnRefreshListener(this);
        eventAdapter.addData(newsResponseList);
    }

    @Override
    public void showEventsError(String error) {
        rltProgress.setVisibility(View.INVISIBLE);
        Snackbar snackbar = Snackbar
                .make(crdEvents, error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry_request, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        eventPresenter.getEvents();
                    }
                });

        snackbar.show();
    }


    @Override
    public void onEventItemClick(int idEvent) {

        PreferencesHeper.setTypeTab(NewPortApplication.getAppContext(), 1);
        PreferencesHeper.setLastFragmentTag(NewPortApplication.getAppContext(), Constant.MENU_NAME_ITEM_EVENTS);
        PreferencesHeper.setCurrentFragmentTag(NewPortApplication.getAppContext(), Constant.FRAGMENT_NEWS_DETAIL);

        NewDetailFragment newFragment = NewDetailFragment.newInstance(idEvent);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.hide(this);
        transaction.add(R.id.content_fragments, newFragment, Constant.FRAGMENT_NEWS_DETAIL);
        transaction.commit();
    }

    @Override
    public void onRefresh() {
        srlEvents.setRefreshing(true);
        eventPresenter.getEvents();
    }
}
