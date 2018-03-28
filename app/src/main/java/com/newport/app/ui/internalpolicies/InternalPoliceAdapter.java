package com.newport.app.ui.internalpolicies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.newport.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tohure on 11/11/17.
 */

public class InternalPoliceAdapter extends RecyclerView.Adapter<InternalPoliceAdapter.InternalPoliceItemViewHolder> {

    private final List<InternalPolice> internalPoliceList;

    //Listener for onClick in List
    public interface OnClickInternalPoliceListener { void onPoliceInternalItemClick(InternalPolice internalPolice); }
    private OnClickInternalPoliceListener listener;
    public void setOnInternalPoliceClickListener(OnClickInternalPoliceListener listener) { this.listener = listener; }

    public InternalPoliceAdapter() {
        this.internalPoliceList = new ArrayList<>();
    }

    public void addData(List<InternalPolice> internalPoliceList) {
        this.internalPoliceList.clear();
        this.internalPoliceList.addAll(internalPoliceList);
        notifyDataSetChanged();
    }

    @Override
    public InternalPoliceItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_internal_policies, parent, false);
        return new InternalPoliceItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InternalPoliceItemViewHolder holder, int position) {
        holder.lblQuestionInternalPolicies.setText(internalPoliceList.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return internalPoliceList.size();
    }

    public class InternalPoliceItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView lblQuestionInternalPolicies;
        private Button btnSeeInformaation;

        InternalPoliceItemViewHolder(View itemView) {
            super(itemView);

            lblQuestionInternalPolicies = itemView.findViewById(R.id.lblQuestionInternalPolicies);
            btnSeeInformaation = itemView.findViewById(R.id.btnSeeInformaation);
            btnSeeInformaation.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onPoliceInternalItemClick(internalPoliceList.get(getAdapterPosition()));
            }
        }
    }
}
