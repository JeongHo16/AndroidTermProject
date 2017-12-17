package com.hansung.teamproject.homework1;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RestaurantMap extends AppCompatActivity implements OnMapReadyCallback{
    final private int REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION = 1;

    private FusedLocationProviderClient mFusedLocationClient;
    private Location mCurrentLocation ;

    GoogleMap mGoogleMap = null;

    static ArrayList<Marker> myMarker = new ArrayList<Marker>();
    EditText findText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (!checkLocationPermissions()) { //위치접근 권한 확인 11주차 강의자료 참고
            requestLocationPermissions(REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION);
        } else{
            getLastLocation();
        }

        Button findButton = (Button) findViewById(R.id.findButton);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findText = (EditText) findViewById(R.id.findText);
                findLocation(findText.getText().toString());
            }
        });

    }

    void findLocation(String input) {
        TextView result = (TextView)findViewById(R.id.result);
        try {
            Geocoder geocoder = new Geocoder(this, Locale.KOREA);
            List<Address> addresses = geocoder.getFromLocationName(input,1);
            if (addresses.size() >0) {
                Address bestResult = (Address) addresses.get(0);

                result.setText(String.format("[ %s , %s ]",
                        bestResult.getLatitude(),
                        bestResult.getLongitude()));

                LatLng findLocation = new LatLng(bestResult.getLatitude(),
                        bestResult.getLongitude());
                mGoogleMap.addMarker(new MarkerOptions().position(findLocation).title(input));
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(findLocation,15));
                mGoogleMap.setOnMarkerClickListener(new MyMarkerClickListener());
            }
        } catch (IOException e) {
            Log.e(getClass().toString(),"Failed in using Geocoder.", e);
            return;
        }
    }

    class MyMarkerClickListener implements GoogleMap.OnMarkerClickListener {
        @Override
        public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
            click();
            return false;
        }
    }

    void click() { //http://webnautes.tistory.com/1094 참고
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("맛집 등록");
        builder.setMessage("새로운 맛집으로 등록하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), Restaurant_pluses.class);
                        intent.putExtra("address", findText.getText().toString());
                        startActivity(intent);
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"등록하지 않습니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.resmap_menu, menu);
        menu.findItem(R.id.one).setChecked(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.currentLocation:
                getLastLocation();
                return true;

            case R.id.one:
                LatLng newLocation = new LatLng(mCurrentLocation.getLatitude(),
                        mCurrentLocation.getLongitude());
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 15));
                return true;

            case R.id.two:
                LatLng newLocation2 = new LatLng(mCurrentLocation.getLatitude(),
                        mCurrentLocation.getLongitude());
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation2, 14));
                return true;

            case R.id.three:
                LatLng newLocation3 = new LatLng(mCurrentLocation.getLatitude(),
                        mCurrentLocation.getLongitude());
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation3, 13));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean checkLocationPermissions() { //11주차 강의자료 참고
        int permissionState = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermissions(int requestCode) { //11주차 강의자료 참고
        ActivityCompat.requestPermissions(
                RestaurantMap.this,            // RestaurantMap 액티비티의 객체 인스턴스를 나타냄
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},        // 요청할 권한 목록을 설정한 String 배열
                requestCode    // 사용자 정의 int 상수. 권한 요청 결과를 받을 때
        );
    }

    @Override //11주차 강의자료 참고
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation();
                } else {
                    Toast.makeText(this, "Permission required", Toast.LENGTH_SHORT);
                }
                break;
            }
        }
    }

    @SuppressWarnings("MissingPermission") //11주차 강의자료 참고
    private void getLastLocation() {
        Task task = mFusedLocationClient.getLastLocation();       // Task<Location> 객체 반환
        task.addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    mCurrentLocation = location;
                    LatLng newLocation = new LatLng(mCurrentLocation.getLatitude(),
                            mCurrentLocation.getLongitude());
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 15));
                } else
                    Toast.makeText(getApplicationContext(),
                            "No Location Detected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        getMarker();
    }

    String putTitle;
    public void getMarker(){
        try{
            if(myMarker.size() >0){
                for(int i = 0; i < myMarker.size(); i++){
                    mGoogleMap.addMarker(new MarkerOptions().
                            position(new LatLng(myMarker.get(i).lat, myMarker.get(i).lon)).
                            title(myMarker.get(i).title).
                            icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                    );
                    Log.i("MyMarker Plus", i+ "번쨰");
                    putTitle = myMarker.get(i).title;
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myMarker.get(i).lat, myMarker.get(i).lon),15));
                    mGoogleMap.setOnMarkerClickListener(new MyMarkerClickListener(){
                        @Override
                        public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
                            Intent intent = new Intent(getApplicationContext(), RestaurantDetailActivity.class);
                            intent.putExtra("plusesName",putTitle);
                            startActivity(intent);
                            return false;
                        }
                    });
                }
            }
        }catch (NullPointerException e){
            return;
        }
    }

    /*class rMarkerClickListener implements GoogleMap.OnMarkerClickListener {
        @Override
        public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
            rclick();
            return false;
        }
    }

    void rclick() {
        Intent intent = new Intent(getApplicationContext(), RestaurantDetailActivity.class);
        intent
    }*/
}
