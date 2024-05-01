package com.example.protectress.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.protectress.R;
import com.example.protectress.Retrofit.NewsAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;

    private LinearLayout profile,contact,Siren,safety_tips;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout= findViewById(R.id.drawer_layout);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) NavigationView navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        profile=findViewById(R.id.profile);
        contact=findViewById(R.id.contact);
        Siren=findViewById(R.id.siren);
        safety_tips=findViewById(R.id.news);




        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChoosenActivity.class));
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SmsActivity.class));
            }
        });
        Siren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AlertSirenActivity.class));
            }
        });
        safety_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewsActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.help1)
        {
            startActivity(new Intent(getApplicationContext(),HelpLineActivity.class));
        }
        else if(id==R.id.tips)
        {
            startActivity(new Intent(getApplicationContext(), SafetyActivity.class));
        }
        else if(id==R.id.contact_us)
        {
            startActivity(new Intent(getApplicationContext(), ContactUsActivity.class));
        }
        return true;
    }
}