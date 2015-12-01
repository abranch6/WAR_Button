package com.example.benjamin.alert;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.app.Service;
import android.os.IBinder;
import android.os.Bundle;

/**
 * Created by Benjamin on 10/26/2015.
 * GPS class, used to get current location
 */
public class GPS extends Service implements LocationListener {

    private final Context mContext;
    boolean GPSEnabled = false;
    boolean networkEnabled = false;
    boolean canGetLoc = false;

    Location location;
    double lat;
    double longg;
    protected LocationManager locManager;

    /**
     * Gets current location and sets longitude and latitude
     * @param context
     */
    public GPS(Context context) {
        this.mContext = context;

        Location loc = getLocation();
        lat = loc.getLatitude();
        longg = loc.getLongitude();

    }

    /**
     * Finds current location (coordinates of the phone)
     * @return location
     */
    public Location getLocation() {
        try {
            locManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            // getting GPS status
            GPSEnabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            networkEnabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!GPSEnabled && !networkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLoc = true;
                // First get location from Network Provider
                if (networkEnabled) {
                    locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                    if (locManager != null) {
                        location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            lat = location.getLatitude();
                            longg = location.getLongitude();
                        }
                    }
                } else if (GPSEnabled) {
                    locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    if (locManager != null) {
                        location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            lat = location.getLatitude();
                            longg = location.getLongitude();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    /**
     * Gets latitude
     * @return
     */
    public double getLatitude() {
        if (location != null) {
            lat = location.getLatitude();
        }
        return lat;
    }

    /**
     * Gets longitude
     * @return
     */
    public double getLongitude() {
        if (location != null) {
            longg = location.getLongitude();
        }
        return longg;
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}