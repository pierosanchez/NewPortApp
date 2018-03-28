package com.newport.app.ui.directory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newport.app.R;
import com.newport.app.data.models.response.DirectoryResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tohure on 12/11/17.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactItemViewHolder> {

    private final List<DirectoryResponse.ContactsBean> personDirectoryList;

    ContactsAdapter() {
        this.personDirectoryList = new ArrayList<>();
    }

    public void addData(List<DirectoryResponse.ContactsBean> personDirectoryList) {
        this.personDirectoryList.clear();
        this.personDirectoryList.addAll(personDirectoryList);
        notifyDataSetChanged();
    }

    @Override
    public ContactItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_directory, parent, false);
        return new ContactItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactItemViewHolder holder, int position) {
        if (!personDirectoryList.get(position).getPhoto_url().isEmpty()) {
            Picasso.with(holder.imgPerfil.getContext())
                    .load(personDirectoryList.get(position).getPhoto_url())
                    .fit().centerCrop()
                    .placeholder(R.drawable.user_icon)
                    .error(R.drawable.user_icon)
                    .into(holder.imgPerfil);
        }
        holder.lblNameContact.setText(personDirectoryList.get(position).getName());
        holder.lblNameArea.setText(personDirectoryList.get(position).getPosition());
        holder.lblEmailContact.setText(personDirectoryList.get(position).getEmail());
        holder.lblPhoneContact.setText("Anexo : " + personDirectoryList.get(position).getTelephone1());
        if(personDirectoryList.get(position).getTelephone2().trim().length()>0){
            holder.lblCellPhoneContact.setText("Teléfono : " + personDirectoryList.get(position).getTelephone2());
        }
        else{
            holder.lblCellPhoneContact.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return personDirectoryList.size();
    }

    class ContactItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgPerfil;
        private final TextView lblNameContact;
        private final TextView lblNameArea;
        private final TextView lblEmailContact;
        private final TextView lblPhoneContact;
        private final TextView lblCellPhoneContact;

        ContactItemViewHolder(View itemView) {
            super(itemView);
            imgPerfil = itemView.findViewById(R.id.imgPerfil);
            lblNameContact = itemView.findViewById(R.id.lblNameContact);
            lblNameArea = itemView.findViewById(R.id.lblNameArea);
            lblEmailContact = itemView.findViewById(R.id.lblEmailContact);
            lblPhoneContact = itemView.findViewById(R.id.lblPhoneContact);
            lblCellPhoneContact = itemView.findViewById(R.id.lblCellPhoneContact);
        }
    }
}
