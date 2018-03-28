package com.newport.app.ui.events;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newport.app.R;
import com.newport.app.data.models.response.EventsResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tohure on 21/11/17.
 */

public class NewsEventAdapter extends RecyclerView.Adapter<NewsEventAdapter.NewItemViewHolder> {

    private List<EventsResponse.NewsBean> newsResponseList;
    private EventAdapter.OnClickEventListener listener;

    NewsEventAdapter(EventAdapter.OnClickEventListener listener) {
        this.newsResponseList = new ArrayList<>();
        this.listener = listener;
    }

    public void addData(List<EventsResponse.NewsBean> newsResponseList) {
        this.newsResponseList = newsResponseList;
        notifyDataSetChanged();
    }

    @Override
    public NewItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_new, parent, false);
        return new NewItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewItemViewHolder holder, int position) {
        Picasso.with(holder.imgNew.getContext())
                .load(newsResponseList.get(position).getImage_url())
                .fit().centerCrop()
                .placeholder(R.drawable.newport_gray)
                .error(android.R.drawable.ic_dialog_alert)
                .into(holder.imgNew);
        holder.lblTitleNew.setText(newsResponseList.get(position).getTitle());
        holder.lblDateNew.setText(newsResponseList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return newsResponseList.size();
    }

    class NewItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgNew;
        private TextView lblTitleNew;
        private TextView lblDateNew;

        NewItemViewHolder(View itemView) {
            super(itemView);
            imgNew = itemView.findViewById(R.id.imgNew);
            lblTitleNew = itemView.findViewById(R.id.lblTitleNew);
            lblDateNew = itemView.findViewById(R.id.lblDateNew);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onEventItemClick(newsResponseList.get(getAdapterPosition()).getId());
            }
        }
    }
}
