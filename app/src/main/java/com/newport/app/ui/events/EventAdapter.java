package com.newport.app.ui.events;

import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newport.app.R;
import com.newport.app.data.models.response.EventsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tohure on 15/11/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventItemViewHolder> {

    private List<EventsResponse> newsResponseList;

    public interface OnClickEventListener { void onEventItemClick(int idEvent);}
    private OnClickEventListener listener;
    void setOnEventClickListener(OnClickEventListener listener) { this.listener = listener;}

    EventAdapter() {
        newsResponseList = new ArrayList<>();
    }

    public void addData(List<EventsResponse> newsResponseList) {
        this.newsResponseList = newsResponseList;
        notifyDataSetChanged();
    }

    @Override
    public EventItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subcategory_event, parent, false);
        return new EventItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventItemViewHolder holder, int position) {
        holder.lblTitleSubCategory.setText(newsResponseList.get(position).getSubcategory());
        Log.d("EventAdapter", newsResponseList.get(position).getSubcategory());

        holder.setPreData(newsResponseList.get(position).getNews().size());
        holder.newsEventAdapter.addData(newsResponseList.get(position).getNews());
    }

    @Override
    public int getItemCount() {
        return newsResponseList.size();
    }

    class EventItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private boolean setting = false;
        private int currentPosition = 0;
        private int adapterSize = 0;

        private TextView lblTitleSubCategory;
        private RecyclerView rcvEventsCategory;
        private NewsEventAdapter newsEventAdapter;
        private SnapHelper snapHelper;
        private ImageView imgLeftArrowGalleryEvent;
        private ImageView imgRightArrowGalleryEvent;

        EventItemViewHolder(View itemView) {
            super(itemView);
            lblTitleSubCategory = itemView.findViewById(R.id.lblTitleSubCategory);
            rcvEventsCategory = itemView.findViewById(R.id.rcvNewsCategory);
            imgLeftArrowGalleryEvent = itemView.findViewById(R.id.imgLeftArrowGalleryEvent);
            imgLeftArrowGalleryEvent.setOnClickListener(this);
            imgRightArrowGalleryEvent = itemView.findViewById(R.id.imgRightArrowGalleryEvent);
            imgRightArrowGalleryEvent.setOnClickListener(this);

            rcvEventsCategory.setHasFixedSize(true);

            snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(rcvEventsCategory);

            newsEventAdapter = new NewsEventAdapter(listener);
            rcvEventsCategory.setAdapter(newsEventAdapter);

            new Timer().scheduleAtFixedRate(new TimerTask(){
                @Override
                public void run(){
                    if (currentPosition < adapterSize - 1) {
                        currentPosition++;
                    } else {
                        currentPosition = 0;
                    }
                    rcvEventsCategory.smoothScrollToPosition(currentPosition);
                }
            },0,7000);
        }

        @Override
        public void onClick(View view) {
            if (setting) {
                if (view.getId() == R.id.imgLeftArrowGalleryEvent) {
                    moveBackwardRecycler();
                } else if (view.getId() == R.id.imgRightArrowGalleryEvent) {
                    moveForwardRecycler();
                }
            }
        }

        private void moveForwardRecycler() {

            if (currentPosition < adapterSize - 1) {
                currentPosition++;
            } else {
                currentPosition = 0;
            }

            rcvEventsCategory.scrollToPosition(currentPosition);
        }

        private void moveBackwardRecycler() {

            if (currentPosition != 0) {
                currentPosition--;
            } else {
                currentPosition = adapterSize - 1;
            }

            rcvEventsCategory.scrollToPosition(currentPosition);
        }

        void setPreData(int size) {
            if (!setting){
                setting = true;
                adapterSize = size;
            }
        }
    }
}