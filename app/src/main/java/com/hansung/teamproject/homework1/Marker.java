package com.hansung.teamproject.homework1;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Junho on 2017-12-16.
 */

public class Marker {

    double lat;
    double lon;

    String title;

    public Marker(double lat, double lon, String title){
        this.lat = lat;
        this.lon = lon;
        this.title = title;
    }
    public Marker(LatLng latLng, String string){
        lat = latLng.latitude;
        lon = latLng.longitude;
        title = string;
    }
}
