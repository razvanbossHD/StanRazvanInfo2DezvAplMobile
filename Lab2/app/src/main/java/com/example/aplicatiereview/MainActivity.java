package com.example.aplicatiereview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        Button btn1 = findViewById(R. id.btn1);
        btn1. setOnClickListener(new View. OnClickListener() {
            public void onClick(View v) {
                TextView tv1 = (TextView)findViewById(R.id.txtView);
                tv1.setText(String.valueOf(Integer.parseInt(tv1.getText().toString())+1));

            }
    });
        Button btn2 = findViewById(R. id.btn2);
        btn2. setOnClickListener(new View. OnClickListener() {
            public void onClick(View v) {
                TextView tv1 = (TextView)findViewById(R.id.txtView);
                tv1.setText(String.valueOf(Integer.parseInt(tv1.getText().toString())-1));

            }
        });
}}