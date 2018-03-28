package com.newport.app.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.perf.metrics.AddTrace;
import com.newport.app.R;
import com.newport.app.ui.events.EventsFragment;
import com.newport.app.ui.home.NewsFragment;
import com.newport.app.ui.profile.ProfileFragment;
import com.newport.app.ui.questions.QuestionsFragment;
import com.newport.app.ui.schedules.SchedulesFragment;

/**
 * Created by tohure on 05/11/17.
 */

public class RecoverFragmentUtil {

    private RecoverFragmentUtil() {
        throw new IllegalAccessError("Utility class");
    }

    private static NewsFragment newsFragment;
    private static EventsFragment eventsFragment;
    private static QuestionsFragment questionsFragment;
    private static SchedulesFragment schedulesFragment;
    private static ProfileFragment profileFragment;

    private static NewsFragment getNewsFragment() {
        if (newsFragment == null) {
            newsFragment = NewsFragment.newInstance();
        }
        return newsFragment;
    }

    private static EventsFragment getEventsFragment() {
        if (eventsFragment == null) {
            eventsFragment = EventsFragment.newInstance();
        }
        return eventsFragment;
    }

    private static QuestionsFragment getQuestionsFragment() {
        if (questionsFragment == null) {
            questionsFragment = QuestionsFragment.newInstance();
        }
        return questionsFragment;
    }

    private static SchedulesFragment getSchedulesFragment() {
        if (schedulesFragment == null) {
            schedulesFragment = SchedulesFragment.newInstance();
        }
        return schedulesFragment;
    }

    private static ProfileFragment getProfileFragment() {
        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance();
        }
        return profileFragment;
    }

    public static void displayFragmentNews(FragmentTransaction ft, FirebaseAnalytics mFirebaseAnalytics, Activity activity) {
        replaceFragments(ft, getNewsFragment(), Constant.MENU_NAME_ITEM_NEWS);
        mFirebaseAnalytics.setCurrentScreen(activity, getNewsFragment().getClass().getSimpleName(), getQuestionsFragment().getClass().getSimpleName());
    }

    public static void displayFragmentEvents(FragmentTransaction ft, FirebaseAnalytics mFirebaseAnalytics, Activity activity) {
        replaceFragments(ft, getEventsFragment(), Constant.MENU_NAME_ITEM_EVENTS);
        mFirebaseAnalytics.setCurrentScreen(activity, getEventsFragment().getClass().getSimpleName(), getQuestionsFragment().getClass().getSimpleName());
    }

    public static void displayFragmentQuestions(FragmentTransaction ft, FirebaseAnalytics mFirebaseAnalytics, Activity activity) {
        replaceFragments(ft, getQuestionsFragment(), Constant.MENU_NAME_ITEM_QUESTIONS);
        mFirebaseAnalytics.setCurrentScreen(activity, getQuestionsFragment().getClass().getSimpleName(), getQuestionsFragment().getClass().getSimpleName());
    }

    public static void displayFragmentSchedules(FragmentTransaction ft, FirebaseAnalytics mFirebaseAnalytics, Activity activity) {
        replaceFragments(ft, getSchedulesFragment(), Constant.MENU_NAME_ITEM_SCHEDULES);
        mFirebaseAnalytics.setCurrentScreen(activity, getSchedulesFragment().getClass().getSimpleName(), getQuestionsFragment().getClass().getSimpleName());
    }

    public static void displayFragmentPerfil(FragmentTransaction ft, FirebaseAnalytics mFirebaseAnalytics, Activity activity) {
        replaceFragments(ft, getProfileFragment(), Constant.MENU_NAME_ITEM_PROFILE);
        mFirebaseAnalytics.setCurrentScreen(activity, getProfileFragment().getClass().getSimpleName(), getQuestionsFragment().getClass().getSimpleName());
    }

    @AddTrace(name = "replaceFragments")
    private static void replaceFragments(FragmentTransaction ft, Fragment fragment, String tag) {

        //AddNewFragment
        if (fragment.isAdded()) {
            ft.show(fragment);
        } else {
            ft.add(R.id.content_fragments, fragment, tag);
        }

        // Hide fragment News
        if (getNewsFragment().isAdded() && !getNewsFragment().getTag().equals(tag)) {
            ft.hide(getNewsFragment());
        }

        // Hide fragment Events
        if (getEventsFragment().isAdded() && !getEventsFragment().getTag().equals(tag)) {
            ft.hide(getEventsFragment());
        }

        // Hide fragment Questions
        if (getQuestionsFragment().isAdded() && !getQuestionsFragment().getTag().equals(tag)) {
            ft.hide(getQuestionsFragment());
        }

        // Hide fragment Schedules
        if (getSchedulesFragment().isAdded() && !getSchedulesFragment().getTag().equals(tag)) {
            ft.hide(getSchedulesFragment());
        }

        // Hide fragment Perfil
        if (getProfileFragment().isAdded() && !getProfileFragment().getTag().equals(tag)) {
            ft.hide(getProfileFragment());
        }

        // Commit changes
        ft.commit();
    }


}
