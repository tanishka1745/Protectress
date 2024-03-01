package com.example.protectress.Adapters;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.protectress.Modals.HelpLineModal;
import com.example.protectress.R;

import java.util.List;

public class HelpLineAdapter extends RecyclerView.Adapter<HelpLineAdapter.ViewHolder>
{

       List<HelpLineModal> helpLineModalList;
       Context context;

       public HelpLineAdapter(List<HelpLineModal>helpLineModalList,Context context)
       {
           this.context=context;
           this.helpLineModalList=helpLineModalList;
       }

    @NonNull
    @Override
    public HelpLineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.helpline_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpLineAdapter.ViewHolder holder, int position)
    {
        holder.name.setText(helpLineModalList.get(position).getName());
        holder.number.setText(helpLineModalList.get(position).getNumber());
        Linkify.addLinks(holder.number, Patterns.PHONE, "tel:", Linkify.sPhoneNumberMatchFilter, Linkify.sPhoneNumberTransformFilter);
        holder.number.setMovementMethod(LinkMovementMethod.getInstance());
        holder.details.setText(helpLineModalList.get(position).getDetails());
    }

    @Override
    public int getItemCount() {
        return helpLineModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name,number,details;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
            details=itemView.findViewById(R.id.details);
        }
    }
}
