package com.example.safealert;


import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import Classes.LocationServ;
import Classes.PowerButt;
import Classes.SMS;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_CONTACTS_PERMISSION = 1;
    private static final int REQUEST_SMS_PERMISSION = 1;
    private static final int REQUEST_PHONE_CALL = 1;
    public static double longitudine=0, latitudine=0;
    public static double longitudinesigura=0, latitudinesigura=0;
    public static int apasari=0;
    public static long ulttmp = 0;
    public static long milisec=10000;

    public static String mesaj="Sunt in pericol!";
    public static MainActivity act;
    AlertDialog alertDialog;
    public CountDownTimer countdown;
    Button btnSOS, btnCall, btnMarkSafe, btnAleg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        permisiuni();
        StartPow();
        btnSOS = findViewById(R.id.btnSOS);
        btnSOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendSMS();
            }
        });
        btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                else
                {
                    Alert("Apel de urgenta in 10 secunde");
                }
            }
        });
        btnMarkSafe = findViewById(R.id.btnMarkSafe);
        btnMarkSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.longitudinesigura=MainActivity.longitudine;
                MainActivity.latitudinesigura=MainActivity.latitudine;
            }
        });
        btnAleg = findViewById(R.id.btnAleg);
        btnAleg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starttimerauto();
            }
        });
        act=this;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationServ();
            }
        }
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(getApplicationContext().ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    private void permisiuni() {
        // Check if the permissions are already granted
        boolean readContactsPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
        boolean sendSmsPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;

        // If permissions are not granted, request them
        if (!readContactsPermissionGranted) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_CONTACTS_PERMISSION);
        }

        if (!sendSmsPermissionGranted) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    REQUEST_SMS_PERMISSION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            startLocationServ();
        }
    }
    public void starttimerauto()
    {
        Intent intent = new Intent(this, TimerAutomat.class);
        startActivity(intent);
    }
    public void SendSMS()
    {

        Intent serviceIntent = new Intent(this, SMS.class);
        startService(serviceIntent);
    }
    public void StartPow()
    {

        Intent serviceIntent = new Intent(this, PowerButt.class);
        startService(serviceIntent);
    }
    private void startLocationServ() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        Intent serviceIntent = new Intent(this, LocationServ.class);
        startService(serviceIntent);
        System.out.println(isMyServiceRunning(LocationServ.class));
    }
    public void Alert(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);

        builder.setMessage(msg);

        builder.setTitle("Alert !");

        builder.setCancelable(false);
        builder.setNegativeButton("Anuleaza", (DialogInterface.OnClickListener) (dialog, which) -> {

            dialog.cancel();
            countdown.cancel();
        });
        alertDialog = builder.create();
        alertDialog.show();
        countdown=new CountDownTimer(10000, 1000) {
            String message=msg;
            public void onTick(long millisUntilFinished) {
                alertDialog.setMessage(message+" "+ millisUntilFinished / 1000);
            }

            public void onFinish() {
                SendSMS();
                alertDialog.cancel();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"));
                startActivity(intent);


            }

        }.start();
    }
}