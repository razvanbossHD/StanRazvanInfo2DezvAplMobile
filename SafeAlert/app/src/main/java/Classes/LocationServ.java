package Classes;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.safealert.MainActivity;

public class LocationServ extends Service {

    private LocationManager locationManager;
    private LocationListener locationListener;

    public double latitude;
    public double longitude;
    private static final String TAG = "LocationService";

    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    MainActivity.latitudine=latitude;
                    MainActivity.longitudine=longitude;
                    Log.d(TAG, "Latitude: " + latitude + ", Longitude: " + longitude);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, android.os.Bundle extras) {
                // Optional: handle provider status changes here
            }

            @Override
            public void onProviderEnabled(String provider) {
                // Optional: handle provider enabled events
            }

            @Override
            public void onProviderDisabled(String provider) {
                // Optional: handle provider disabled events
            }
        };

        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    10000, // 10 seconds
                    10, // 10 meters
                    locationListener
            );
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // You don't need to bind this service, so return null
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

}
