package com.newport.app.ui.eventsgallery;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.newport.app.R;
import com.newport.app.data.models.response.PhotoGalleryEventResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tohure on 08/11/17.
 */

public class EventsGalleryAdapter extends RecyclerView.Adapter<EventsGalleryAdapter.EventGalleryItemViewHolder> {

    private final List<PhotoGalleryEventResponse> photoGalleryEventResponseList;
    private int widthSystem;

    //Listener for onClick in Gallery
    public interface OnClickPhotoListener {  void onGalleryItemClick(int position, PhotoGalleryEventResponse photoGalleryEventResponse, ImageView imgItemGalleryPhoto); }
    private OnClickPhotoListener listener;
    public void setOnPhotoClickListener(OnClickPhotoListener listener) {
        this.listener = listener;
    }

    public EventsGalleryAdapter(int widthSystem) {
        this.photoGalleryEventResponseList = new ArrayList<>();
        this.widthSystem = widthSystem;
    }

    public void addData(List<PhotoGalleryEventResponse> photoGalleryEventResponseList) {
        this.photoGalleryEventResponseList.clear();
        this.photoGalleryEventResponseList.addAll(photoGalleryEventResponseList);
        notifyDataSetChanged();
    }

    @Override
    public EventGalleryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_gallery, parent, false);
        return new EventGalleryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventGalleryItemViewHolder holder, int position) {

        Picasso.with(holder.imgItemGalleryPhoto.getContext())
                .load(photoGalleryEventResponseList.get(position).getImage_url())
                .fit().centerCrop()
                .placeholder(R.drawable.newport_gray)
                .error(android.R.drawable.ic_dialog_alert)
                .into(holder.imgItemGalleryPhoto);

        ViewCompat.setTransitionName(holder.imgItemGalleryPhoto, photoGalleryEventResponseList.get(position).getCreated_at());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onGalleryItemClick(holder.getAdapterPosition(), photoGalleryEventResponseList.get(holder.getAdapterPosition()), holder.imgItemGalleryPhoto);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return photoGalleryEventResponseList.size();
    }

    class EventGalleryItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgItemGalleryPhoto;

        EventGalleryItemViewHolder(View itemView) {
            super(itemView);
            imgItemGalleryPhoto = itemView.findViewById(R.id.imgItemGalleryPhoto);
            android.view.ViewGroup.LayoutParams layoutParams = imgItemGalleryPhoto.getLayoutParams();

            layoutParams.height = (widthSystem / 4) - 20;
            imgItemGalleryPhoto.setLayoutParams(layoutParams);
        }

    }
}
