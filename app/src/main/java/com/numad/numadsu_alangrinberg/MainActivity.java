package com.numad.numadsu_alangrinberg;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends FragmentActivity implements LocationListener {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Button contactBtn = findViewById(R.id.contact);

        contactBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView contactText = findViewById(R.id.showContact);
                contactText.setText(R.string.contactInfo);
            }
        });
    }

    public void openClicky(View view) {
        startActivity(new Intent(MainActivity.this, ClickyActivity.class));
    }

    public void openLinkCollector(View view) {
        startActivity(new Intent(MainActivity.this, LinkActivity.class));
    }

    public void openLocator(View view) {
        if (checkLocationPermissions()) {
            startActivity(new Intent(MainActivity.this, LocatorActivity.class));
        }
    }

    public void openWebService(View view) {
        startActivity(new Intent(MainActivity.this, WebService.class));
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
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
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

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    Toast.makeText(this, " testing ", Toast.LENGTH_LONG).show();
                }
            } else {
                System.out.println("access denied");
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

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
