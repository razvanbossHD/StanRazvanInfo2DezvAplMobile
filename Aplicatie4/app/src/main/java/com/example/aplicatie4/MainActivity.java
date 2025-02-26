package com.example.aplicatie4;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnAdunare, btnScadere, btnInmultire, btnImpartire;
    EditText txt1, txt2;
    TextView lblrez;

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
        txt1=findViewById(R.id.txtNr1);
        txt2=findViewById(R.id.txtNr2);
        lblrez=findViewById(R.id.txtRez);
        btnAdunare = (Button) findViewById(R. id.btnAdunare);
        btnAdunare. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblrez.setText(Integer.toString(Integer.parseInt((txt1.getText().toString()))+Integer.parseInt(txt2.getText().toString())));
            }
        });
        btnScadere = (Button) findViewById(R. id.btnScadere);
        btnScadere. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblrez.setText(Integer.toString(Integer.parseInt((txt1.getText().toString()))-Integer.parseInt(txt2.getText().toString())));
            }
        });
        btnInmultire = (Button) findViewById(R. id.btnInmultire);
        btnInmultire. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblrez.setText(Integer.toString(Integer.parseInt((txt1.getText().toString()))*Integer.parseInt(txt2.getText().toString())));
            }
        });
        btnImpartire = (Button) findViewById(R. id.btnImpartire);
        btnImpartire. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblrez.setText(Integer.toString(Integer.parseInt((txt1.getText().toString()))/Integer.parseInt(txt2.getText().toString())));
            }
        });
    }
}