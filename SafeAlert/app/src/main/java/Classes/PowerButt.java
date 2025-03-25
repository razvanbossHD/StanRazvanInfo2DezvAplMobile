package Classes;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.safealert.MainActivity;

public class PowerButt extends Service {

    private static final String TAG = "LocationService";

    private static final long limita = 3000;

    private int apasari = 0;
    private long ulttmp = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(powerButtonReceiver, filter);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private BroadcastReceiver powerButtonReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long currentTime = SystemClock.elapsedRealtime();
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                handlePowerButtonPress(currentTime);
            }
        }
    };

    private void handlePowerButtonPress(long currentTime) {
        if (currentTime - ulttmp < limita) {
            apasari++;
        } else {
            apasari = 1;
        }

        ulttmp = currentTime;

        if (apasari == 3) {
            MainActivity.act.SendSMS();
            apasari = 0;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the receiver to prevent memory leaks
        unregisterReceiver(powerButtonReceiver);
    }
}
