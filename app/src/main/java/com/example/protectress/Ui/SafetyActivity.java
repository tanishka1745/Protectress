package com.example.protectress.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.protectress.Adapters.SafetyAdapter;
import com.example.protectress.R;

import java.util.ArrayList;

public class SafetyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SafetyAdapter safetyAdapter;

    private ArrayList<String> safetyTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);

        recyclerView=findViewById(R.id.recyclerView_safety);
        safetyTips= new ArrayList<>();
        safetyTips.add(getString(R.string.safety_tips_tip_1));
        safetyTips.add(getString(R.string.safety_tips_tip_2));
        safetyTips.add(getString(R.string.safety_tips_tip_3));
        safetyTips.add(getString(R.string.safety_tips_tip_4));
        safetyTips.add(getString(R.string.safety_tips_tip_5));
        safetyTips.add(getString(R.string.safety_tips_tip_6));
        safetyTips.add(getString(R.string.safety_tips_tip_7));
        safetyTips.add(getString(R.string.safety_tips_tip_8));
        safetyTips.add(getString(R.string.safety_tips_tip_9));
        safetyTips.add(getString(R.string.safety_tips_tip_10));
        safetyTips.add(getString(R.string.safety_tips_tip_11));
        safetyTips.add(getString(R.string.safety_tips_tip_12));
        safetyTips.add(getString(R.string.safety_tips_tip_13));
        safetyTips.add(getString(R.string.safety_tips_tip_14));
        safetyTips.add(getString(R.string.safety_tips_tip_15));
        safetyTips.add(getString(R.string.safety_tips_tip_16));
        safetyTips.add(getString(R.string.safety_tips_tip_17));
        safetyTips.add(getString(R.string.safety_tips_tip_18));
        safetyTips.add(getString(R.string.safety_tips_tip_19));
        safetyTips.add(getString(R.string.safety_tips_tip_20));
        safetyTips.add(getString(R.string.safety_tips_tip_21));
        safetyAdapter= new SafetyAdapter(safetyTips,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(safetyAdapter);


    }
}