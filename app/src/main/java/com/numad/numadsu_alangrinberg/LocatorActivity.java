package com.numad.numadsu_alangrinberg;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LocatorActivity extends AppCompatActivity implements LocationListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    LocationManager locationManager;
    String provider;
    TextView latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(LocatorActivity.this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
//                    1);
//        }

//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
////
////        if (checkLocationPermissions()) {
////            provider = locationManager.getBestProvider(new Criteria(), false);
////        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (checkLocationPermissions()) {
            provider = locationManager.getBestProvider(new Criteria(), false);
        }
    }

    public boolean checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new MaterialAlertDialogBuilder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(LocatorActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        }).create().show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
//
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//                    Toast.makeText(this, latitude + " WORKS " + longitude + "", Toast.LENGTH_LONG).show();
//
//                }
//            } else {
//                System.out.println("access denied");
//            }
//        }
//    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.removeUpdates(this);
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = (TextView) findViewById(R.id.latitude);
        latitude.setText(String.format("Latitude:%s", location.getLatitude()));
        longitude = (TextView) findViewById(R.id.longitude);
        longitude.setText(String.format("Longitude: %s", location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
