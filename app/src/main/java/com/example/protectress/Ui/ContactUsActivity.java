package com.example.protectress.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.protectress.R;

public class ContactUsActivity extends AppCompatActivity {

    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        button=findViewById(R.id.contact_us);
        button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                composeEmail();
            }
        });
    }
    public void composeEmail() {
        String subject="Contacting for Protectress";
        String mailto = "mailto:tanishkasharma1745@gmail.com" +
                "&subject=" + Uri.encode(subject);


        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }


    }

}