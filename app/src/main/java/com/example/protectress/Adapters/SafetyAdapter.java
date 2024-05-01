package com.example.protectress.Adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.protectress.R;

import java.util.ArrayList;

public class SafetyAdapter extends RecyclerView.Adapter<SafetyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String>safetyList;

    public SafetyAdapter(ArrayList<String>safetyList,Context context)
    {
        this.safetyList=safetyList;
        this.context=context;
    }






    @NonNull
    @Override
    public SafetyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.safety_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SafetyAdapter.ViewHolder holder, int position)
    {
          holder.tips.setText(safetyList.get(position));
        holder.itemView.setOnLongClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Contact", safetyList.get(position));
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return safetyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tips;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tips=itemView.findViewById(R.id.tips);
        }
    }
}
