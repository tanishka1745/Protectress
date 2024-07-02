package com.example.protectress.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.protectress.R;


import com.example.protectress.Vibrate.StoreContact;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SmsActivity extends AppCompatActivity {


    EditText txt_pnumber1, txt_msg, txt_pnumber2;
    Button Save,add_to_contact;

    /**
     * LocationManager :- This class provides access to the system location services.
     * These services allow applications to obtain periodic updates of the device's geographical location,
     * or to be notified when the device enters the proximity of a given geographical location.
     */

    /**
     * FusedLocationProvider :- The fused location provider is one of the location APIs in Google Play services.
     * It manages the underlying location technology and provides a simple API so that you can specify
     * requirements at a high level, like high accuracy or low power.
     * It also optimizes the device's use of battery power.
     * The fused location provider  is used specifically to retrieve the device's last known location
     */

    FusedLocationProviderClient fusedLocationProviderClient;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        txt_msg = findViewById(R.id.textMessage);
        add_to_contact=findViewById(R.id.add_to_contact);

        add_to_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StoreContact.class));
            }
        });

        txt_pnumber1 = findViewById(R.id.textPhoneNumber1);
        txt_pnumber2 = findViewById(R.id.textPhoneNumber2);






        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);



    }

    public void send_btn(View view) {
        //Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        tryIt ();
    }
    public  void tryIt(){
        if(!txt_pnumber1.getText ().toString ().trim ().equals ("")) {
            /**
             * ContextCompat :- It is a class for replacing some work with base context.
             * For example if you used before something like getContext().getColor(R.color.black);
             * Now its deprecated since android 6.0 (API 22+) so you should use: getContext().getColor(R.color.black,theme);
             */
            if (ContextCompat.checkSelfPermission (SmsActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                makeCall ();
            } else {
                ActivityCompat.requestPermissions (this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        }else{
            Toast.makeText (this,"Please enter first number...",Toast.LENGTH_LONG);
        }
        int permissionCheck = ContextCompat.checkSelfPermission (this, Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            if ( ContextCompat.checkSelfPermission (SmsActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
                SendLocationMessage ();

            } else {

                /**
                 * ActivityCompat :- public interface ActivityCompat. A helper for accessing features in Activity in a backwards
                 * compatible fashion. Construct this by using getActivityCompat(Activity) .
                 * This helper augments the included methods with data on instant apps.
                 */
                ActivityCompat.requestPermissions (this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            }

        } else {
            ActivityCompat.requestPermissions (this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }


    }
    //Calling function
    private void makeCall() {
        Intent intent = new Intent (Intent.ACTION_CALL);
        String phoneNumber = txt_pnumber1.getText ().toString ();
        if (phoneNumber.trim ().isEmpty ()) {
            Toast.makeText (SmsActivity.this, "Please enter first number to make a call..", Toast.LENGTH_SHORT).show ();

        }
        else if(phoneNumber.length()<11 || phoneNumber.length()>11)
        {
            Toast.makeText(this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
        }
        else {

            intent.setData (Uri.parse ("tel:" + phoneNumber));
        }
        startActivity (intent);
    }

    private void SendLocationMessage() {


        if (ActivityCompat.checkSelfPermission (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation ().addOnCompleteListener (new OnCompleteListener<Location> () {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //Initialize Location
                Location location = task.getResult ();
                String Message = txt_msg.getText ().toString ().trim ();
                if (location != null) {

                    try {
                        // Read about Geocoder by just hover on where it is written
                        //Initialize Geocoder
                        Geocoder geocoder = new Geocoder (SmsActivity.this, Locale.getDefault ());
                        //Initialize address list
                        List<Address> addresses = geocoder.getFromLocation (
                                location.getLatitude (), location.getLongitude (), 1
                        );
                        Message += "I am at " + addresses.get (0).getCountryName () +
                                "," + addresses.get (0).getLocality () + ", " + addresses.get (0).getAddressLine (0)+ "\n"+
                                "http://maps.google.com/?q="+addresses.get(0).getLatitude()+","+addresses.get(0).getLongitude();
                        //Log.d("myapp",Message);

                    } catch (IOException e) {
                        e.printStackTrace ();
                    }


                }else{
                    String str="Software was not able to retrieve live location due to some internal errors..";
                    Message += str;
                }
                String phoneNumber1 = txt_pnumber1.getText ().toString ().trim ();
                String phoneNumber2 = txt_pnumber2.getText ().toString ().trim ();

                if (!txt_pnumber1.getText ().toString ().equals ("") || !txt_pnumber2.getText ().toString ().equals (""))
                    if (!txt_pnumber1.getText ().toString ().equals ("")) {

                        // SmsManager :- Manages SMS operations such as sending data, text, and pdu SMS messages.
                        // Get this object by calling the static method getDefault().
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNumber1, null, Message, null, null);
                        smsManager.sendTextMessage(phoneNumber2, null, Message, null, null);
                        Toast.makeText(SmsActivity.this, "Message sent...", Toast.LENGTH_SHORT).show();
                    }
                if(!txt_pnumber2.getText().toString().equals(""))
                {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber2, null, Message, null, null);
                }



                 else {
                    Toast.makeText (SmsActivity.this, "Please give the phone number first...", Toast.LENGTH_SHORT).show ();
                }

            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult (requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:

                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }
                else {
                    Toast.makeText (this, "You don't have required permissions", Toast.LENGTH_SHORT).show ();
                }

                break;
        }
    }


}