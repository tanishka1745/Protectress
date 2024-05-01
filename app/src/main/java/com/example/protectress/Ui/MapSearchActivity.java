package com.example.protectress.Ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.AsyncTaskLoader;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.protectress.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;
import java.security.spec.ECField;
import java.util.HashMap;
import java.util.List;

public class MapSearchActivity extends AppCompatActivity {


    Spinner spinner;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    Button btn_find;

    double currLat=0,currLong=0;
    //initilze the fused provider here

    FusedLocationProviderClient fusedLocationProviderClient;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);

        spinner = findViewById(R.id.spinner);
        btn_find = findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        String[] placeTypelist = {"atm", "bank", "hospital", "movie_theater", "restaurant", "cafe", "library"};
        String[] placeNameList = {"ATM", "Bank", "Hospital", "Movie Theater", "Restaurant", "Cafe", "Library"};

        spinner.setAdapter(new ArrayAdapter<>(MapSearchActivity.this, android.R.layout.simple_dropdown_item_1line, placeNameList));
        //call fused
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(MapSearchActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }
        else{
            ActivityCompat.requestPermissions(MapSearchActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i= spinner.getSelectedItemPosition();
                String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json"+"?location="+
                        currLat+","+ currLong+ "&radius=5000"+ "&types="+ placeTypelist[i]+ "&sensor=true"
                        +"&key"+ getResources().getString(R.string.key);
                new Placetask().execute(url);
            }
        });
    }
    private class Placetask extends AsyncTask<String,Integer,String>{

        String data=null;

        @Override
        protected String doInBackground(String... strings) {
            try{
                data= downloadUrl(strings[0]);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
    private String downloadUrl(String string) throws IOException
    {
        URL url= new URL(string);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream stream= connection.getInputStream();
        BufferedReader reader= new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder= new StringBuilder();
        String line= "";
        while((line==reader.readLine()))
        {
            builder.append(line);
        }
        String data= builder.toString();
        reader.close();
        return data;
    }
    private class ParserTask extends AsyncTask<String,Integer, List<HashMap<String,String>>>
    {

        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            JsonParser jsonParser= new JsonParser();
            //Initiate hash map
            List<HashMap<String,String>> mapList= null;
            //Inititate json object
            try{
                JSONObject object= new JSONObject(strings[0]);
                mapList= jsonParser.parseResult(object);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return mapList;
        }
    }
    protected void onPostExecute(List<HashMap<String,String>>hashMaps)
    {
        map.clear();
        for(int i=0;i<hashMaps.size();i++)
        {
            HashMap<String,String> hashMapList= hashMaps.get(i);
            //Get latitude
            double lat= Double.parseDouble(hashMapList.get("lat"));
            double lon= Double.parseDouble(hashMapList.get("lng"));
            String name= hashMapList.get("name");
            LatLng latLng= new LatLng(lat,lon);
            MarkerOptions options= new MarkerOptions();
            //set position
            options.position(latLng);
            options.title(name);
            //add marker on map
            map.addMarker(options);
        }
    }



    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null)
                {
                    currLat= location.getLatitude();
                    currLong=location.getLongitude();
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //when map is ready
                            map= googleMap;
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currLat,currLong),10));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==44)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                getCurrentLocation();
            }
        }
    }
}