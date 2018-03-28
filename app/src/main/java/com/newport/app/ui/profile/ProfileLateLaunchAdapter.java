package com.newport.app.ui.profile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newport.app.R;
import com.newport.app.data.models.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tohure on 06/12/17.
 */

public class ProfileLateLaunchAdapter extends RecyclerView.Adapter<ProfileLateLaunchAdapter.LateItemViewHolder> {

    private final List<UserResponse.TardinessBean.LunchBean> tardinessBeanList;

    public ProfileLateLaunchAdapter() {
        this.tardinessBeanList = new ArrayList<>();
    }

    public void addData(List<UserResponse.TardinessBean.LunchBean> beanList) {
        this.tardinessBeanList.clear();
        this.tardinessBeanList.addAll(beanList);
        notifyDataSetChanged();
    }

    @Override
    public LateItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lates, parent, false);
        return new LateItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LateItemViewHolder holder, int position) {
        holder.lblDaysLate.setText(tardinessBeanList.get(position).getDay());
        holder.lblTimeLate.setText(tardinessBeanList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return tardinessBeanList.size();
    }

    class LateItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView lblDaysLate;
        private final TextView lblTimeLate;

        LateItemViewHolder(View itemView) {
            super(itemView);
            lblDaysLate = itemView.findViewById(R.id.lblDaysLate);
            lblTimeLate = itemView.findViewById(R.id.lblTimeLate);
        }
    }
}
