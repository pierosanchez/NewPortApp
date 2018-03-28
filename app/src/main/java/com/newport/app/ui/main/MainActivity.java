package com.newport.app.ui.main;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.perf.metrics.AddTrace;
import com.newport.app.NewPortApplication;
import com.newport.app.R;
import com.newport.app.ui.BaseActivity;
import com.newport.app.ui.home.NewsFragment;
import com.newport.app.util.BottomNavigationViewHelper;
import com.newport.app.util.Constant;
import com.newport.app.util.PreferencesHeper;
import com.newport.app.util.RecoverFragmentUtil;

public class MainActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        MainContract.View,
        NewsFragment.OnClickLateDate {

    private Menu menu;
    private MenuItem logoPrincipal;
    private boolean sensibleFragment = false;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(savedInstanceState);
    }

    @SuppressLint("CommitTransaction")
    @AddTrace(name = "initMainScreen")
    private void init(Bundle savedInstanceState) {
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        BottomNavigationView bnvMenu = findViewById(R.id.bnvMenu);
        BottomNavigationViewHelper.disableShiftMode(bnvMenu);
        bnvMenu.setOnNavigationItemSelectedListener(this);

        menu = bnvMenu.getMenu();
        logoPrincipal = menu.findItem(R.id.navigation_questions);
        logoPrincipal.setIcon(R.drawable.logo_deactivated);

        if (savedInstanceState == null) {
            //Init first fragment
            RecoverFragmentUtil.displayFragmentNews(getFragmentManager().beginTransaction(), mFirebaseAnalytics, this);
            sensibleFragment = true;
        } else {
            Fragment fragment = getFragmentManager().findFragmentById(R.id.content_fragments);
            if (fragment != null) {
                Intent myIntent = new Intent(this, MainActivity.class);
                finish();

                this.startActivity(myIntent);
            }
        }

        //Attach Presenter
        MainPresenter presenter = new MainPresenter();
        presenter.attachedView(this);
        presenter.calculateDiferencesDay();
    }

    public void displayView(int id) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        removeDetailFragments();

        logoPrincipal.setIcon(R.drawable.logo_deactivated);
        switch (id) {
            case R.id.navigation_questions:
                logoPrincipal.setIcon(R.drawable.logo_activated);
                RecoverFragmentUtil.displayFragmentQuestions(ft, mFirebaseAnalytics, this);
                sensibleFragment = false;
                break;
            case R.id.navigation_events:
                RecoverFragmentUtil.displayFragmentEvents(ft, mFirebaseAnalytics, this);
                PreferencesHeper.setLastFragmentTag(NewPortApplication.getAppContext(), "");
                PreferencesHeper.setCurrentFragmentTag(NewPortApplication.getAppContext(), Constant.MENU_NAME_ITEM_EVENTS);
                sensibleFragment = true;
                break;
            case R.id.navigation_schedule:
                RecoverFragmentUtil.displayFragmentSchedules(ft, mFirebaseAnalytics, this);
                sensibleFragment = false;
                break;
            case R.id.navigation_profile:
                RecoverFragmentUtil.displayFragmentPerfil(ft, mFirebaseAnalytics, this);
                sensibleFragment = false;
                break;
            case R.id.navigation_news:
            default:
                RecoverFragmentUtil.displayFragmentNews(ft, mFirebaseAnalytics, this);
                PreferencesHeper.setLastFragmentTag(NewPortApplication.getAppContext(), "");
                PreferencesHeper.setCurrentFragmentTag(NewPortApplication.getAppContext(), Constant.MENU_NAME_ITEM_EVENTS);
                sensibleFragment = true;
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displayView(item.getItemId());
        return true;
    }

    private void removeDetailFragments() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        Fragment fragment = getFragmentManager().findFragmentByTag(Constant.FRAGMENT_NEWS_DETAIL);
        if (fragment != null) {
            ft.remove(fragment);
        }

        Fragment fragment2 = getFragmentManager().findFragmentByTag(Constant.FRAGMENT_NEWS_DETAIL_GALLERY);
        if (fragment2 != null) {
            ft.remove(fragment2);
        }

        ft.commit();
    }

    private void callRemoveFragment() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        Fragment fragment = getFragmentManager().findFragmentByTag(PreferencesHeper.getCurrentFragmentTag(NewPortApplication.getAppContext()));
        if (fragment != null) {
            ft.remove(fragment);
        }

        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (sensibleFragment) {
            callRemoveFragment();
            if (PreferencesHeper.getLastFragmentTag(NewPortApplication.getAppContext()).equals("")) {
                super.onBackPressed();
            } else {
                showLastFragment();
            }
        } else {
            super.onBackPressed();
        }
    }

    private void showLastFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment fragment = getFragmentManager().findFragmentByTag(PreferencesHeper.getLastFragmentTag(NewPortApplication.getAppContext()));
        if (fragment != null) {
            ft.show(fragment);
        }
        ft.commit();

        replaceTags();
    }

    private void replaceTags() {
        if (PreferencesHeper.getCurrentFragmentTag(NewPortApplication.getAppContext()).equals(Constant.FRAGMENT_NEWS_DETAIL_GALLERY) &&
                PreferencesHeper.getLastFragmentTag(NewPortApplication.getAppContext()).equals(Constant.FRAGMENT_NEWS_DETAIL)) {

            if (PreferencesHeper.getTypeTab(NewPortApplication.getAppContext()) == 0) {
                PreferencesHeper.setLastFragmentTag(NewPortApplication.getAppContext(), Constant.MENU_NAME_ITEM_NEWS);
            } else {
                PreferencesHeper.setLastFragmentTag(NewPortApplication.getAppContext(), Constant.MENU_NAME_ITEM_EVENTS);
            }
            PreferencesHeper.setCurrentFragmentTag(NewPortApplication.getAppContext(), Constant.FRAGMENT_NEWS_DETAIL);

        } else if (PreferencesHeper.getCurrentFragmentTag(NewPortApplication.getAppContext()).equals(Constant.FRAGMENT_NEWS_DETAIL_GALLERY) &&
                PreferencesHeper.getLastFragmentTag(NewPortApplication.getAppContext()).equals(Constant.MENU_NAME_ITEM_NEWS)) {

            PreferencesHeper.setLastFragmentTag(NewPortApplication.getAppContext(), "");
            PreferencesHeper.setCurrentFragmentTag(NewPortApplication.getAppContext(), Constant.MENU_NAME_ITEM_NEWS);
        }

        if (PreferencesHeper.getCurrentFragmentTag(NewPortApplication.getAppContext()).equals(Constant.FRAGMENT_NEWS_DETAIL) &&
                PreferencesHeper.getLastFragmentTag(NewPortApplication.getAppContext()).equals(Constant.MENU_NAME_ITEM_NEWS)) {

            sensibleFragment = false;
        }

        /*if (PreferencesHeper.getCurrentFragmentTag(NewPortApplication.getAppContext()).equals(Constant.FRAGMENT_NEWS_DETAIL) &&
                PreferencesHeper.getLastFragmentTag(NewPortApplication.getAppContext()).equals(Constant.MENU_NAME_ITEM_EVENTS)) {

            sensibleFragment = false;
        }*/

        if (PreferencesHeper.getCurrentFragmentTag(NewPortApplication.getAppContext()).equals(Constant.FRAGMENT_NEWS_DETAIL_GALLERY) &&
                PreferencesHeper.getLastFragmentTag(NewPortApplication.getAppContext()).equals(Constant.MENU_NAME_ITEM_NEWS)) {

            sensibleFragment = false;
        }
    }

    @Override
    public void expireCarnet() {
        Toast.makeText(this, R.string.carnet_is_expiring, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLateDateClick() {
        PreferencesHeper.setScrollProfileStatus(NewPortApplication.getAppContext(), true);
        MenuItem menuItem = menu.findItem(R.id.navigation_profile);
        onNavigationItemSelected(menuItem);
        menuItem.setChecked(true);
    }

}