package com.newport.app.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.newport.app.R;

/**
 * Created by tohure on 02/11/17.
 */

public class VerifyAccountFragment extends DialogFragment {

    private View rooView;

    private RadioButton rdbPhone;
    private RadioButton rdbEmail;
    private Button btnVerifyDialog;

    //Listener for onClick in items
    public interface OnClickSelectorListener { void onDialogItemClick(String optionVerify); }
    private OnClickSelectorListener listener;
    public void setOnItemClickListener(OnClickSelectorListener listener) { this.listener = listener; }

    public static VerifyAccountFragment newInstance() {

        return new VerifyAccountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @SuppressLint("InflateParams")
    private void init() {

        //InflateView
        LayoutInflater inflater = getActivity().getLayoutInflater();
        rooView = inflater.inflate(R.layout.dialog_verify_account, null, false);

        rdbPhone = rooView.findViewById(R.id.rdbPhone);
        rdbEmail = rooView.findViewById(R.id.rdbEmail);

        btnVerifyDialog = rooView.findViewById(R.id.btnVerifyDialog);

        btnVerifyDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyRadioButtons();
            }
        });
    }

    private void verifyRadioButtons() {
        if (rdbEmail.isChecked() || rdbPhone.isChecked()) {
            sendOptionToVerify();
            dismiss();
        } else {
            Toast.makeText(rooView.getContext(), "Seleccione al menos una opción", Toast.LENGTH_SHORT).show();
        }

    }

    private void sendOptionToVerify() {
        if (rdbEmail.isChecked()) {
            listener.onDialogItemClick("Email");
        } else {
            listener.onDialogItemClick("Phone");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(rooView);
        return builder.create();
    }
}
