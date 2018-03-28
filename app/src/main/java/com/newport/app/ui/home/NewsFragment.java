package com.newport.app.ui.home;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newport.app.NewPortApplication;
import com.newport.app.R;
import com.newport.app.data.models.response.HomeResponse;
import com.newport.app.ui.eventsgallery.EventsGalleryFragment;
import com.newport.app.ui.newdetail.NewDetailFragment;
import com.newport.app.util.Constant;
import com.newport.app.util.PreferencesHeper;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tohure on 05/11/17.
 */

public class NewsFragment extends Fragment implements NewsContract.View, NewsAdapter.OnClickNewListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private NewsPresenter newsPresenter;

    private RelativeLayout rltProgress;
    private CoordinatorLayout crdHome;
    private SwipeRefreshLayout srlNews;

    private View rootView;

    //First Element
    private RecyclerView rcvNewsCategory;
    private int currentPosition = -1;
    private int adapterSize = 0;

    //Second Element
    private LinearLayout lnlAtTime;
    private TextView lblDaysLate;
    private TextView lblTiempo;

    //Listener for Click and change tab
    public interface OnClickLateDate {
        void onLateDateClick();
    }

    private OnClickLateDate listener;

    //Third Element - Type Cash
    private RelativeLayout rltThirdElementCash;
    private TextView lblThirdElementCashDate;

    //Third Element - Type Upload Photo
    private RelativeLayout rltThirdElementUploadPhoto;

    //Third Element - Type Gallery Photo
    private RelativeLayout rltThirdElementGalleryPhoto;
    private TextView lblThirdElementGalleryPhoto;
    private ImageView imgThirdElementGalleryPhoto;
    private ImageView imgPhoneGoldFour;

    //Fourth Element
    private RelativeLayout rltFourthElement;
    private TextView lblFourthElementTitleNew;

    private NewsAdapter adapter;

    private int idThirdNew = 0;
    private int idThirdUpload = 0;
    private int idFourthNew = 0;
    private String textThirdGallery = "";
    private String urlimageThirdGallery = "";
    private String urlImageFour = "";

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {

        return new NewsFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnClickLateDate) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_news, container, false);
        init();
        return rootView;
    }

    private void init() {
        ImageView imgLeftArrowGalleryEvent = rootView.findViewById(R.id.imgLeftArrowGalleryEvent);
        imgLeftArrowGalleryEvent.setOnClickListener(this);
        ImageView imgRightArrowGalleryEvent = rootView.findViewById(R.id.imgRightArrowGalleryEvent);
        imgRightArrowGalleryEvent.setOnClickListener(this);

        srlNews = rootView.findViewById(R.id.srlNews);
        srlNews.setColorSchemeResources(R.color.colorPrimaryDark,
                R.color.colorPrimary,
                R.color.colorAccent);

        crdHome = rootView.findViewById(R.id.crdHome);
        rltProgress = rootView.findViewById(R.id.rltProgress);

        newsPresenter = new NewsPresenter();
        newsPresenter.attachedView(this);

        lnlAtTime = rootView.findViewById(R.id.lnlAtTime);
        lblDaysLate = rootView.findViewById(R.id.lblDaysLate);
        lblTiempo = rootView.findViewById(R.id.lblTiempo);

        rltThirdElementCash = rootView.findViewById(R.id.rltThirdElementCash);
        lblThirdElementCashDate = rootView.findViewById(R.id.lblThirdElementCashDate);

        rltThirdElementUploadPhoto = rootView.findViewById(R.id.rltThirdElementUploadPhoto);
        rltThirdElementGalleryPhoto = rootView.findViewById(R.id.rltThirdElementGalleryPhoto);
        lblThirdElementGalleryPhoto = rootView.findViewById(R.id.lblThirdElementGalleryPhoto);
        imgThirdElementGalleryPhoto = rootView.findViewById(R.id.imgGalleryPhoto);
        imgPhoneGoldFour = rootView.findViewById(R.id.imgPhoneGold);

        rltFourthElement = rootView.findViewById(R.id.rltFourthElement);
        lblFourthElementTitleNew = rootView.findViewById(R.id.lblFourthElementTitleNew);

        adapter = new NewsAdapter();

        final SnapHelper snapHelper = new PagerSnapHelper();

        rcvNewsCategory = rootView.findViewById(R.id.rcvNewsCategory);
        rcvNewsCategory.setHasFixedSize(true);
        rcvNewsCategory.setAdapter(adapter);
        snapHelper.attachToRecyclerView(rcvNewsCategory);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsPresenter.makeHomeQuery();
    }

    @Override
    public void showLoading() {
        srlNews.setOnRefreshListener(null);
        rltProgress.setVisibility(View.VISIBLE);
        srlNews.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        rltProgress.setVisibility(View.GONE);
        srlNews.setVisibility(View.VISIBLE);
        if (srlNews.isRefreshing()) {
            srlNews.setRefreshing(false);
        }
    }

    @Override
    public void showHomeItems(final HomeResponse homeResponse) {
        //Setup Refresh
        srlNews.setOnRefreshListener(this);

        //First Section
        adapterSize = homeResponse.getSection1().size();
        adapter.addData(homeResponse.getSection1());
        adapter.setOnNewClickListener(this);

        // Init
        //First Section
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if (currentPosition < adapterSize - 1) {
                    currentPosition++;
                } else {
                    currentPosition = 0;
                }
                rcvNewsCategory.smoothScrollToPosition(currentPosition);
            }
        },1000,5000);

        //Second Section
        lnlAtTime.setOnClickListener(this);

        String sDay = (homeResponse.getSection2().getDays() > 1) ? "s" : "";
        String daysLate = String.format(NewPortApplication.getAppContext().getResources().getString(R.string.late_days), homeResponse.getSection2().getDays(), sDay);
        lblDaysLate.setText(daysLate);
        lblTiempo.setText(homeResponse.getSection2().getTime());

        //Third Section
        idThirdNew = homeResponse.getSection3().getNews_id();
        idThirdUpload = homeResponse.getSection3().getUpload_photos();
        textThirdGallery = homeResponse.getSection3().getText();
        urlimageThirdGallery = homeResponse.getSection3().getImage_url();

        if (idThirdNew > 0) {
            /*if (idThirdUpload > 0) {
                rltThirdElementUploadPhoto.setVisibility(View.VISIBLE);
                rltThirdElementUploadPhoto.setOnClickListener(this);
            } else {

            }*/
            rltThirdElementGalleryPhoto.setVisibility(View.VISIBLE);
            rltThirdElementGalleryPhoto.setOnClickListener(this);
            lblThirdElementGalleryPhoto.setText(textThirdGallery);
            Picasso.with(imgThirdElementGalleryPhoto.getContext())
                    .load(urlimageThirdGallery)
                    .placeholder(R.drawable.newport_gray)
                    //.error(android.R.drawable.ic_dialog_alert)
                    .into(imgThirdElementGalleryPhoto);
        } else {
            Calendar c = Calendar.getInstance();

            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

            String[] months = getResources().getStringArray(R.array.months_name);

            rltThirdElementCash.setVisibility(View.VISIBLE);

            lblThirdElementCashDate.setText(String.format(getString(R.string.update_to_month), dayOfMonth, months[c.get(Calendar.MONTH)]));
        }

        //Fourth Section
        rltFourthElement.setOnClickListener(this);
        lblFourthElementTitleNew.setText(homeResponse.getSection4().getTitle());
        idFourthNew = homeResponse.getSection4().getId();
        urlImageFour = homeResponse.getSection4().getImage_url_icon();
        Picasso.with(imgPhoneGoldFour.getContext())
                .load(urlImageFour)
                .placeholder(R.drawable.newport_gray)
                //.error(android.R.drawable.ic_dialog_alert)
                .into(imgPhoneGoldFour);
    }

    @Override
    public void showHomeItemsError(String error) {
        rltProgress.setVisibility(View.INVISIBLE);
        Snackbar snackbar = Snackbar
                .make(crdHome, error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry_request, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newsPresenter.makeHomeQuery();
                    }
                });

        snackbar.show();
    }

    private void moveForwardRecycler() {

        if (currentPosition < adapterSize - 1) {
            currentPosition++;
        } else {
            currentPosition = 0;
        }

        rcvNewsCategory.scrollToPosition(currentPosition);
    }

    private void moveBackwardRecycler() {

        if (currentPosition != 0) {
            currentPosition--;
        } else {
            currentPosition = adapterSize - 1;
        }

        rcvNewsCategory.scrollToPosition(currentPosition);
    }

    @Override
    public void onNewItemClick(HomeResponse.Section1Bean lastNew) {
        callNewDetail(lastNew.getId());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgLeftArrowGalleryEvent:
                moveBackwardRecycler();
                break;
            case R.id.imgRightArrowGalleryEvent:
                moveForwardRecycler();
                break;
            case R.id.lnlAtTime:
                if (listener != null) {
                    listener.onLateDateClick();
                }
                break;
            case R.id.rltThirdElementUploadPhoto:
            case R.id.rltThirdElementGalleryPhoto:
                callGalleryNew(idThirdNew, idThirdUpload);
                break;
            case R.id.rltFourthElement:
                callNewDetail(idFourthNew);
                break;
        }
    }

    private void callNewDetail(int id) {
        PreferencesHeper.setTypeTab(NewPortApplication.getAppContext(), 0);
        PreferencesHeper.setLastFragmentTag(NewPortApplication.getAppContext(), Constant.MENU_NAME_ITEM_NEWS);
        PreferencesHeper.setCurrentFragmentTag(NewPortApplication.getAppContext(), Constant.FRAGMENT_NEWS_DETAIL);

        NewDetailFragment newFragment = NewDetailFragment.newInstance(id);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.hide(this);
        transaction.add(R.id.content_fragments, newFragment, Constant.FRAGMENT_NEWS_DETAIL);
        transaction.commit();
    }

    private void callGalleryNew(int idDetailEvent, int uploadPhotos) {
        PreferencesHeper.setTypeTab(NewPortApplication.getAppContext(), 0);
        PreferencesHeper.setLastFragmentTag(NewPortApplication.getAppContext(), Constant.MENU_NAME_ITEM_NEWS);
        PreferencesHeper.setCurrentFragmentTag(NewPortApplication.getAppContext(), Constant.FRAGMENT_NEWS_DETAIL_GALLERY);

        EventsGalleryFragment newFragment = EventsGalleryFragment.newInstance(idDetailEvent, uploadPhotos);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.hide(this);
        transaction.add(R.id.content_fragments, newFragment, Constant.FRAGMENT_NEWS_DETAIL_GALLERY);
        transaction.commit();
    }

    @Override
    public void onRefresh() {
        srlNews.setRefreshing(true);
        newsPresenter.makeHomeQuery();
    }
}