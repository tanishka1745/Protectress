package com.example.protectress.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import com.example.protectress.MapSet.MapGoogle;
import com.example.protectress.R;

public class HomeActivityClass extends AppCompatActivity {


    LinearLayout helpline,sms,find_place,contact;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_class);

        helpline=findViewById(R.id.helpline);
        sms=findViewById(R.id.emergency);
        find_place=findViewById(R.id.find_place1);
        contact=findViewById(R.id.contact_class);

        helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HelpLineActivity.class));
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SmsActivity.class));
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ContactUsActivity.class));
            }
        });
        find_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapGoogle.class));
            }
        });





    }
}