package com.hansung.teamproject.homework1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class RestaurantMap extends AppCompatActivity implements OnMapReadyCallback{

    final int REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA = 1;

    private FusedLocationProviderClient fusedLocationProviderClient;
    GoogleMap mGoogleMap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_map);

        checkDangerousPermissions(); // 위치 접근권한 확인

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }

    private void checkDangerousPermissions(){
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION    // 위치 접근권한 확인
        };
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for(int i =0; i<permissions.length; i++){
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if(permissionCheck == PackageManager.PERMISSION_DENIED)
                break;
        }
        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, permissions, REQUEST_EXTERNAL_STORAGE_FOR_MULTIMEDIA);
    }
}