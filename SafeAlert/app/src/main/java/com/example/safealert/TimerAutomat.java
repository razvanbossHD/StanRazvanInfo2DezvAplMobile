package com.example.safealert;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Classes.Counttomsg;
import Classes.LocationServ;

public class TimerAutomat extends AppCompatActivity {

    EditText txtMesaj;
    Intent serviceIntent;
    Button btn30min, btn1ora, btn3ore, btn12ore, btn24ore, btnAnuleaza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_timer_automat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtMesaj=findViewById(R.id.txtMesaj);
        txtMesaj.setText(MainActivity.mesaj);

        btn30min=findViewById(R.id.btn30min);

        btn30min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimer();
                MainActivity.milisec=(long)6*1000;
                starttimer();
            }
        });

        btn1ora=findViewById(R.id.btn1ora);
        btn1ora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimer();
                MainActivity.milisec=(long)60*60*1000;
                starttimer();
            }
        });

        btn3ore=findViewById(R.id.btn3ore);
        btn1ora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimer();
                MainActivity.milisec=(long)3*60*60*1000;
                starttimer();
            }
        });

        btn12ore=findViewById(R.id.btn12ore);
        btn1ora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimer();
                MainActivity.milisec=(long)12*60*60*1000;
                starttimer();
            }
        });

        btn24ore=findViewById(R.id.btn24ore);
        btn1ora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimer();
                MainActivity.milisec=(long)24*60*60*1000;
                starttimer();
            }
        });

        btnAnuleaza=findViewById(R.id.btnAnuleaza);
        btn1ora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimer();
            }
        });
    }

    public void starttimer()
    {
        serviceIntent = new Intent(this, Counttomsg.class);
        startService(serviceIntent);
    }
    public void stoptimer()
    {
        if(serviceIntent!=null)
            stopService(serviceIntent);
    }

}