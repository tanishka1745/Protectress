package com.example.protectress.onBoarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.protectress.R;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>{

    private List<OnBoardingItem> onBoardingItems;

    public OnBoardingAdapter(List<OnBoardingItem>onBoardingItems)
    {
        this.onBoardingItems=onBoardingItems;
    }

    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboarding,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {

       holder.setOnboardingData(onBoardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    class OnBoardingViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitle;
        private TextView textDescription;
        private ImageView imageOnBoarding;

        public OnBoardingViewHolder(@NonNull View itemView) {
            super(itemView);

            textDescription=itemView.findViewById(R.id.textDescription);
            textTitle=itemView.findViewById(R.id.textTitle);
            imageOnBoarding=itemView.findViewById(R.id.onboardingimage);
        }
        void setOnboardingData(OnBoardingItem onBoardingItem)
        {
            textTitle.setText(onBoardingItem.getTitle());
            textDescription.setText(onBoardingItem.getDescription());
            imageOnBoarding.setImageResource(onBoardingItem.getImage());
        }
    }
}
