package com.newport.app.ui.internalpolicies;


import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.newport.app.BuildConfig;
import com.newport.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InternalPoliciesFragment extends Fragment implements InternalPoliceAdapter.OnClickInternalPoliceListener {

    private View rootView;

    private InternalPoliceAdapter internalPoliceAdapter;

    private FirebaseAnalytics mFirebaseAnalytics;

    public InternalPoliciesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_internal_policies, container, false);
        init();
        return rootView;
    }

    private void init() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());

        RecyclerView rcvInternalPolicies = rootView.findViewById(R.id.rcvInternalPolicies);
        rcvInternalPolicies.setHasFixedSize(true);

        internalPoliceAdapter = new InternalPoliceAdapter();
        internalPoliceAdapter.setOnInternalPoliceClickListener(this);
        rcvInternalPolicies.setAdapter(internalPoliceAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initDummyData();
    }

    private void initDummyData() {
        List<InternalPolice> internalPoliceList = new ArrayList<>();
        internalPoliceList.add(new InternalPolice("¿Qué debo saber sobre el programa de voluntariado?", BuildConfig.URL_STORAGE + "politicas/politica_voluntariado.pdf"));
        internalPoliceList.add(new InternalPolice("¿Cómo puedo solicitar un préstamo?", BuildConfig.URL_STORAGE + "politicas/politica_prestamo.pdf"));
        internalPoliceList.add(new InternalPolice("¿Cómo funciona el programa de línea de carrera?", BuildConfig.URL_STORAGE + "politicas/linea_de_carrera.pdf"));
        internalPoliceList.add(new InternalPolice("¿Cómo accedo a los convenios educativos?", BuildConfig.URL_STORAGE + "politicas/convenios_educativos.pdf"));

        internalPoliceAdapter.addData(internalPoliceList);
    }

    @Override
    public void onPoliceInternalItemClick(InternalPolice internalPolice) {
        //Track Event
        Bundle bundle = new Bundle();
        bundle.putString("question", internalPolice.getQuestion());
        mFirebaseAnalytics.logEvent("see_internal_police", bundle);

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        builder.setToolbarColor(this.getResources().getColor(R.color.colorPrimary));
        builder.setShowTitle(true);
        builder.setStartAnimations(getActivity(), android.R.anim.fade_in, android.R.anim.fade_out);
        builder.setExitAnimations(getActivity(), android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        customTabsIntent.launchUrl(getActivity(), Uri.parse(internalPolice.getLink()));
    }
}
