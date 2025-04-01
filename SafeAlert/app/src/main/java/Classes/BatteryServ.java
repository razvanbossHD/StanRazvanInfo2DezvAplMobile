package Classes;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.BatteryManager;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.safealert.MainActivity;

import java.util.ArrayList;

public class BatteryServ extends Service {

    private static final int REQUEST_CONTACTS_PERMISSION = 1;
    private static final int REQUEST_SMS_PERMISSION = 2;
    @Override
    public void onCreate() {
        checkBatteryLevel();
        IntentFilter batteryStatusFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, batteryStatusFilter);
    }

    private void checkBatteryLevel() {
        BatteryManager batteryManager = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
        int batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        if (batteryLevel < 10) {
            Toast.makeText(this, "Battery is below 10%!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Battery level: " + batteryLevel + "%", Toast.LENGTH_SHORT).show();
        }
    }

    private final BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            if (level != -1 && scale != -1) {
                int batteryPercentage = (int) ((level / (float) scale) * 100);

                if (batteryPercentage == 9) {
                    Toast.makeText(MainActivity.act, "Bateria e sub 10% activati modul de economisire al ecranului!", Toast.LENGTH_LONG).show();
                    MainActivity.act.SendSMS();
                }
                else if (batteryPercentage == 1) {
                    Toast.makeText(MainActivity.act, "Bateria e 1%!", Toast.LENGTH_LONG).show();
                    MainActivity.act.SendSMS();
                }
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

