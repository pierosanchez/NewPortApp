package com.newport.app.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newport.app.R;
import com.newport.app.data.models.response.HomeResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tohure on 06/11/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    private final List<HomeResponse.Section1Bean> newsList;

    //Listener for onClick in news
    public interface OnClickNewListener { void onNewItemClick(HomeResponse.Section1Bean lastNew); }
    private OnClickNewListener listener;
    public void setOnNewClickListener(OnClickNewListener listener) { this.listener = listener; }

    NewsAdapter() {
        this.newsList = new ArrayList<>();
    }

    public void addData(List<HomeResponse.Section1Bean> newsList){
        this.newsList.addAll(newsList);
        notifyDataSetChanged();
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_new, parent, false);
        return new NewsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        Picasso.with(holder.imgNew.getContext())
                .load(newsList.get(position).getImage_url())
                .placeholder(R.drawable.newport_gray)
                .error(android.R.drawable.ic_dialog_alert)
                .fit()
                .into(holder.imgNew);
        holder.lblTitleNew.setText(newsList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imgNew;
        private final TextView lblTitleNew;

        NewsItemViewHolder(View itemView) {
            super(itemView);
            imgNew = itemView.findViewById(R.id.imgNew);
            lblTitleNew = itemView.findViewById(R.id.lblTitleNew);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null){
                listener.onNewItemClick(newsList.get(getAdapterPosition()));
            }
        }
    }
}


