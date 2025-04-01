package Classes;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.safealert.MainActivity;

public class Counttomsg extends Service {
    private static final int REQUEST_CONTACTS_PERMISSION = 1;
    private static final int REQUEST_SMS_PERMISSION = 2;
    CountDownTimer countdown;
    @Override
    public void onCreate() {
        countdown=new CountDownTimer(MainActivity.milisec, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                MainActivity.act.SendSMS();

            }

        }.start();
    }

    @Override
    public void onDestroy() {
        countdown.cancel();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
