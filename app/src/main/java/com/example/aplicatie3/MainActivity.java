package com.example.aplicatie3;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Console;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnSwitch, btnMode, btnMood, button;
    int light=0;
    Boolean auto=false;
    void cb(int r, int g, int b)
    {
        View rootView = findViewById(android.R.id.background);
        rootView.setBackgroundColor(Color.rgb(r, g, b));
    }
    void maketoast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
    void update()
    {
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

        if (currentHour >= 7 && currentHour < 18) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Notificare");
            builder.setContentTitle("Morning");
            builder.setContentText("Good Morning, do you need more light?");
            builder.setAutoCancel(true);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            managerCompat.notify(1, builder.build());
            View rootView = findViewById(android.R.id.content);
            rootView.setBackgroundColor(Color.WHITE);
            findViewById(android.R.id.content).setBackgroundColor(getResources().getColor(android.R.color.white));
        } else if (currentHour >= 18 && currentHour < 22) {
            View rootView = findViewById(android.R.id.content);
            rootView.setBackgroundColor(Color.rgb(255, 255, 0));
            findViewById(android.R.id.content).setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Notificare");
            builder.setContentTitle("Good night");
            builder.setContentText("Good Night, the light has turned off automatically.");
            builder.setAutoCancel(true);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            managerCompat.notify(1, builder.build());
            View rootView = findViewById(android.R.id.content);
            rootView.setBackgroundColor(Color.BLACK);
        }
    }
    void eventcheck()
    {
        Handler handler = new Handler();
        Runnable updateTimeTask = new Runnable() {
            @Override
            public void run() {
                if (auto) {

                    update();
                    System.out.println("da");
                    handler.postDelayed(this, 60); // Run this task every 1 minute
                }
            }
        };

        handler.post(updateTimeTask);
    }

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

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Notificare");
        builder.setContentTitle("Good night");
        builder.setContentText("Good Night, the light has turned off automatically.");
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        managerCompat.notify(1, builder.build());
        this.btnSwitch = (Button) findViewById(R. id.btnSwitch);

        btnSwitch. setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            public void onClick(View v) {
                if(light==1)
                {   cb(255, 255, 255);
                    light=0;
                    maketoast("da");
                    }
                else
                {   View rootView = findViewById(android.R.id.background);
                    rootView.setBackgroundColor(Color.rgb(255, 255, 0));
                    light=1;}
            }
        });
        btnMode = findViewById(R.id.btnMode);
        btnMode. setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            public void onClick(View v) {
                if(auto)
                {
                    auto=false;
                }
                else
                {
                    auto=true;
                    eventcheck();
                }
            }
        });

        btnMood = findViewById(R.id.btnMood);
        btnMode.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            public void onClick(View v) {
                if(light==2)
                {
                    View rootView = findViewById(android.R.id.content);
                    rootView.setBackgroundColor(Color.rgb(255, 255, 255));
                    light=3;
                }
                else if(light==3)
                {
                    View rootView = findViewById(android.R.id.content);
                    rootView.setBackgroundColor(Color.rgb(0, 0, 255));
                    light=4;
                }
                if(light==5)
                {
                    View rootView = findViewById(android.R.id.content);
                    rootView.setBackgroundColor(Color.rgb(255, 0, 0));
                    light=1;
                }
                else
                {
                    View rootView = findViewById(android.R.id.content);
                    rootView.setBackgroundColor(Color.rgb(0, 255, 0));
                    light=2;
                }
            }
        });

    }

}