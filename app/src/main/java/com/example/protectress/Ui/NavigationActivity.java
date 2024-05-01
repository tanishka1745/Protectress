package com.example.protectress.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.protectress.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View mHeaderView;
    int select=200;
    private LinearLayout profile,contact,Siren,safety_tips,mapActivity;

    TextView profile_name,profile_email;
    ImageView set_image;

    FirebaseUser firebaseUser;
    FirebaseDatabase database;
    DatabaseReference databaseReference;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView=findViewById(R.id.nav_view);

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
        mHeaderView=navigationView.getHeaderView(0);
        profile_name= (TextView)mHeaderView.findViewById(R.id.profile_name);
        profile_name.setText("Tanishka");
        profile_email=(TextView)mHeaderView.findViewById(R.id.profile_email);
        profile_email.setText("tani1734@gmail.com");
        set_image=(ImageView)mHeaderView.findViewById(R.id.profile_set);
        set_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFetch();
            }
        });



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
                startActivity(new Intent(getApplicationContext(), MapSearchActivity.class));
            }
        });
        safety_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewsActivity.class));
            }
        });


    }
    void imageFetch() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), select);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == select) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    set_image.setImageURI(selectedImageUri);
                }
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }







    @SuppressLint("NonConstantResourceId")
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