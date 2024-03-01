package com.example.protectress.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.protectress.Modals.ContactAdd;
import com.example.protectress.R;

import java.util.ArrayList;

public class ContactAddAdapter extends RecyclerView.Adapter<ContactAddAdapter.ViewHolder> {


    private Context context;
    private ArrayList<ContactAdd> contactArrayList;

    public ContactAddAdapter(Context context, ArrayList<ContactAdd> contactArrayList) {
        this.context = context;
        this.contactArrayList = contactArrayList;
    }

    @NonNull
    @Override
    public ContactAddAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAddAdapter.ViewHolder holder, int position) {

        final ContactAdd contact = contactArrayList.get(position);
        holder.contactName.setText(contact.getName());
        holder.contactPhone.setText(contact.getPhone());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView contactName, contactPhone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName = (TextView) itemView.findViewById(R.id.contactName);
            contactPhone = (TextView) itemView.findViewById(R.id.contactPhone);

        }
    }
}
