package com.newport.app.ui.questions;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newport.app.R;

public class QuestionsFragment extends Fragment {

    private View rootView;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    public static QuestionsFragment newInstance() {
        return new QuestionsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_questions, container, false);
        init();
        return rootView;
    }

    private void init() {
        ViewPager vwpQuestions = rootView.findViewById(R.id.vwpQuestions);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getFragmentManager());

        vwpQuestions.setAdapter(adapter);

        TabLayout tblQuestions = rootView.findViewById(R.id.tblQuestions);
        tblQuestions.setupWithViewPager(vwpQuestions);
    }
}
