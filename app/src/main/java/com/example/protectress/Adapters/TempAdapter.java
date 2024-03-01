package com.example.protectress.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.protectress.Modals.ContactModal;
import com.example.protectress.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class TempAdapter extends RecyclerView.Adapter<TempAdapter.ViewHolder> {

    List<ContactModal> list;
    Context context;

    public TempAdapter(List<ContactModal>list,Context context)
    {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public TempAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TempAdapter.ViewHolder holder, int position) {

        ContactModal contactModal= list.get(position);
        holder.name.setText(contactModal.getName());
        holder.number.setText(contactModal.getNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView name,number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
        }
    }
}
