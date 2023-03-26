package com.example.numad23sp_abhiachalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


        import android.os.Bundle;

import org.xml.sax.Locator;

public class LocatorActivity extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    TextView latitudeValue;
    TextView longitudeValue;
    TextView distanceValue;


    float totalDistance;

    LocationListener locationListener;

    Location lastKnowLocation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locator);
        totalDistance = 0;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        latitudeValue = findViewById(R.id.latitude);
        longitudeValue = findViewById(R.id.longitude);
        distanceValue = findViewById(R.id.distance);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return;
        }


        Button resetDistance = findViewById(R.id.reset);
        resetDistance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                totalDistance = 0;
                distanceValue.setText("Distance is: " + 0.0 + " meters");
            }
        });

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // set lat and long
                    latitudeValue.setText("Latitude: " + location.getLatitude());
                    longitudeValue.setText("Longitude: " + location.getLongitude());


                // calculate distance by adding the laslocation- curr loc
                if(lastKnowLocation != null) {
                    totalDistance += lastKnowLocation.distanceTo(location);
                    distanceValue.setText("Distance is: " + totalDistance + " meters");
                }
                lastKnowLocation = location;

            }
        };




    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Access Granted", Toast.LENGTH_LONG).show();
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1, this);
            } else {
                Toast.makeText(this, "Access Denied!", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);

    }

    @Override
    public void onLocationChanged(Location location) {

        String hej1 = Double.toString(location.getLatitude());
        String hej2 = Double.toString(location.getLongitude());
        Toast.makeText(this, "Location: "+"Lat: " + hej1 + "Long: " + hej2,
                Toast.LENGTH_LONG).show();
        latitudeValue.setText("Latitude:  " + location.getLatitude());
        longitudeValue.setText("Longitude:  " + location.getLongitude());

        if(lastKnowLocation != null) {
            totalDistance += lastKnowLocation.distanceTo(location);
            distanceValue.setText("Distance is: " + totalDistance + " meters");
        }
        lastKnowLocation = location;
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("GPS","Disabled");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("GPS","Enabled");
    }
}