package Classes;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class Loc extends Service {

    boolean serviceStopped;

    private Handler mHandler;
    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            if (serviceStopped == false)
            {
                createNotificationIcon();
            }
            queueRunnable();
        }
    };

    private void queueRunnable() {

        mHandler.postDelayed(updateRunnable, 5000);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        serviceStopped = false;

        mHandler = new Handler();
        queueRunnable();
    }

    @Override
    public void onDestroy() {
        serviceStopped = true;
    }

    @Override
    public void onStart(Intent intent, int startid) {

    }

    public void createNotificationIcon()
    {
        Log.d("MyServiceNotifications", "Hello");
    }
}
