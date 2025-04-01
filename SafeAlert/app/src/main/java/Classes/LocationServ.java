package Classes;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.safealert.MainActivity;

public class LocationServ extends Service {

    private LocationManager locationManager;
    private LocationListener locationListener;
    ToneGenerator toneGenerator;

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
                    double dist=sqrt(pow(MainActivity.latitudinesigura-latitude, 2)+pow(MainActivity.longitudinesigura-longitude, 2));
                    System.out.println(dist);
                    System.out.println(MainActivity.latitudine);
                    if(MainActivity.latitudinesigura!=0&&MainActivity.longitudinesigura!=0&&dist>0.1)
                    {
                        MainActivity.act.SendSMS();
                        toneGenerator = new ToneGenerator(AudioManager.STREAM_ALARM, 1000);
                        toneGenerator.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 5000);
                    }
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, android.os.Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    1,
                    locationListener
            );
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

}
