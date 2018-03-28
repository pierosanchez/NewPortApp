package com.newport.app.ui.newdetail;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.newport.app.NewPortApplication;
import com.newport.app.R;
import com.newport.app.data.models.response.NewResponse;
import com.newport.app.ui.eventsgallery.EventsGalleryFragment;
import com.newport.app.util.Constant;
import com.newport.app.util.PreferencesHeper;
import com.squareup.picasso.Picasso;

public class NewDetailFragment extends Fragment implements NewDetailContract.View {

    private static final String ARG_PARAM1 = "idDetailEvent";

    private NewDetailPresenter newDetailPresenter;

    private static int idDetailEvent;

    private View rootView;
    private RelativeLayout rltProgress;
    private ScrollView scvContainerDetail;
    private ImageView imgDetailNew;
    private TextView lblTitleDetailNew;
    private TextView lblTitleContainer;
    private WebView wbvBodyDetailNew;
    private FloatingActionButton fabDetailNew;
    private CoordinatorLayout crdNewDetail;

    public NewDetailFragment() {
        // Required empty public constructor
    }

    public static NewDetailFragment newInstance(int idEvent) {
        NewDetailFragment fragment = new NewDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idEvent);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idDetailEvent = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_event_detail, container, false);
        init();
        return rootView;
    }

    private void init() {
        crdNewDetail = rootView.findViewById(R.id.crdNewDetail);
        lblTitleContainer = rootView.findViewById(R.id.lblTitleContainer);
        rltProgress = rootView.findViewById(R.id.rltProgress);
        scvContainerDetail = rootView.findViewById(R.id.scvContainerDetail);
        imgDetailNew = rootView.findViewById(R.id.imgDetailNew);
        lblTitleDetailNew = rootView.findViewById(R.id.lblTitleDetailNew);
        wbvBodyDetailNew = rootView.findViewById(R.id.wbvBodyDetailNew);

        fabDetailNew = rootView.findViewById(R.id.fabDetailNew);

        newDetailPresenter = new NewDetailPresenter();
        newDetailPresenter.attachedView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newDetailPresenter.makeNewQuery(idDetailEvent);
    }

    @Override
    public void showLoading() {
        rltProgress.setVisibility(View.VISIBLE);
        scvContainerDetail.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        rltProgress.setVisibility(View.GONE);
        scvContainerDetail.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void showNew(final NewResponse newResponse) {
        lblTitleContainer.setText(newResponse.getCategory_name());

        Picasso.with(imgDetailNew.getContext())
                .load(newResponse.getImage_url())
                .fit()
                .placeholder(R.drawable.newport_gray)
                .error(android.R.drawable.ic_dialog_alert)
                .into(imgDetailNew);

        lblTitleDetailNew.setText(newResponse.getTitle());

        wbvBodyDetailNew.setWebChromeClient(new WebChromeClient());
        wbvBodyDetailNew.setWebViewClient(new WebViewClient());
        wbvBodyDetailNew.getSettings().setJavaScriptEnabled(true);
        wbvBodyDetailNew.loadDataWithBaseURL(null, newResponse.getContent(), "text/html", "UTF-8", null);
        if (newResponse.getHas_gallery() != 0) {
            fabDetailNew.show();
            fabDetailNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callGalleryNew(newResponse.getUpload_photos());
                }
            });
        }

    }

    private void callGalleryNew(int uploadPhotos) {
        PreferencesHeper.setLastFragmentTag(NewPortApplication.getAppContext(), Constant.FRAGMENT_NEWS_DETAIL);
        PreferencesHeper.setCurrentFragmentTag(NewPortApplication.getAppContext(), Constant.FRAGMENT_NEWS_DETAIL_GALLERY);

        EventsGalleryFragment newFragment = EventsGalleryFragment.newInstance(idDetailEvent, uploadPhotos);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.hide(this);
        transaction.add(R.id.content_fragments, newFragment, Constant.FRAGMENT_NEWS_DETAIL_GALLERY);
        transaction.commit();
    }

    @Override
    public void showNewError(String error) {
        rltProgress.setVisibility(View.INVISIBLE);
        Snackbar snackbar = Snackbar
                .make(crdNewDetail, error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry_request, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newDetailPresenter.makeNewQuery(idDetailEvent);
                    }
                });

        snackbar.show();
    }
}
