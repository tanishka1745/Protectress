package com.example.protectress.Ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.protectress.R;

public class MagnoInst extends AppCompatActivity {

    private RadioButton bedroom, bathroom, changingroom, outside;
    private Button next;
    RadioGroup radioGroup;
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magno_inst);
        bedroom = findViewById( R.id.bedroom );
        bathroom = findViewById( R.id.baathroom );
        changingroom = findViewById( R.id.changingroom );
        outside = findViewById( R.id.outside );

        next = findViewById( R.id.next );
        radioGroup = findViewById( R.id.radiogroup );





    }

}