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
        if (currentTime - MainActivity.ulttmp < 3000) {
            MainActivity.apasari++;
        } else {
            MainActivity.apasari = 1;
        }

        MainActivity.ulttmp = currentTime;

        if (MainActivity.apasari == 3) {
            MainActivity.act.SendSMS();
            MainActivity.apasari = 0;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(powerButtonReceiver);
    }
}
