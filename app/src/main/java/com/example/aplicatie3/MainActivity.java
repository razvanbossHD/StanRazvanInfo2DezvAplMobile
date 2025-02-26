package com.example.aplicatie3;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout background;
    Button btnSwitch, btnMode, btnMood;
    int light=0;
    Boolean auto=false;
    void cb(int r, int g, int b)
    {
        View rootView = findViewById(R.id.Background);
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
            maketoast("Good Morning, do you need more light?");
            cb(255, 255, 255);
        } else if (currentHour >= 18 && currentHour < 22) {
            cb(255, 255, 0);
        } else {
            maketoast("Good Night, the light has turned off automatically.");
            cb(0, 0, 0);
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Background), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.btnSwitch = (Button) findViewById(R. id.btnSwitch);

        btnSwitch. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(light==1)
                {   light=0;
                    cb(155, 155, 155);
                    }
                else
                {   light=1;


                    cb(255, 255, 0);
                }
            }
        });
        btnMode = findViewById(R.id.btnMode);
        btnMode. setOnClickListener(new View.OnClickListener() {
            @Override
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
        btnMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maketoast(Integer.toString(light));
                if(light==2)
                {
                    cb(255, 255, 255);
                    light=3;
                }
                else if(light==3)
                {
                   cb(255, 0, 0);
                    light=4;
                }
                else if(light==4)
                {
                    cb(0, 255, 0);
                    light=1;
                }
                else
                {
                    cb(0, 0, 255);
                    light=2;
                }
            }
        });

    }

}