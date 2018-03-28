package com.newport.app.ui.directory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newport.app.R;
import com.newport.app.data.models.response.DirectoryResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tohure on 12/11/17.
 */

public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.DirectoryItemViewHolder> {
    private final List<DirectoryResponse> sectionDirectoryList;
    private final Context context;
    private int mExpandedPosition = -1;
    private RecyclerView r1;

    public DirectoryAdapter(Context context, RecyclerView r1) {
        this.sectionDirectoryList = new ArrayList<>();
        this.context = context;
        this.r1 = r1;
    }

    public void addData(List<DirectoryResponse> newsList) {
        this.sectionDirectoryList.clear();
        this.sectionDirectoryList.addAll(newsList);
        notifyDataSetChanged();
    }

    @Override
    public DirectoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_directory, parent, false);
        return new DirectoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DirectoryItemViewHolder holder, int position) {
        holder.lblSectionName.setText(String.format(context.getString(R.string.title_section_directory), sectionDirectoryList.get(position).getSection()));
        holder.contactsAdapter.addData(sectionDirectoryList.get(position).getContacts());

        final boolean isExpanded = position == mExpandedPosition;
        holder.rcvSectionDirectory.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : holder.getAdapterPosition();
                TransitionManager.beginDelayedTransition(r1);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sectionDirectoryList.size();
    }

    class DirectoryItemViewHolder extends RecyclerView.ViewHolder {
        private TextView lblSectionName;
        private ImageView imgArrow;
        private RecyclerView rcvSectionDirectory;
        private ContactsAdapter contactsAdapter;

        DirectoryItemViewHolder(View itemView) {
            super(itemView);

            lblSectionName = itemView.findViewById(R.id.lblSectionName);
            imgArrow = itemView.findViewById(R.id.imgArrow);
            rcvSectionDirectory = itemView.findViewById(R.id.rcvSectionDirectory);
            contactsAdapter = new ContactsAdapter();
            rcvSectionDirectory.setAdapter(contactsAdapter);
        }

    }
}
