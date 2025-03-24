package com.example.rate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Classes.Connection;

public class MainActivity extends AppCompatActivity {
    Button btnEnter;
    EditText txtEmail, txtPassword, txtUsername;
    TextView lblUsername;
    Switch stcLogin, stcCookies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Find), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtUsername = findViewById(R.id.txtUsername);
        txtEmail =findViewById(R.id.txtEmail);
        txtPassword =findViewById(R.id.txtPassword);
        stcCookies=findViewById(R.id.stcCookies);

        stcLogin =findViewById(R.id.stcLogin);
        lblUsername=findViewById(R.id.lblUsername);
        stcLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stcLogin.isChecked()){
                    stcLogin.setText("Register");
                    txtUsername.setVisibility(View.VISIBLE);
                    lblUsername.setVisibility(View.VISIBLE);}
                else{
                    stcLogin.setText("Login");
                    txtUsername.setVisibility(View.INVISIBLE);
                    lblUsername.setVisibility(View.INVISIBLE);}

            }
        });
        btnEnter =(Button) findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stcCookies.isChecked()){
                    if(!stcLogin.isChecked()){ExecutorService executorService = Executors.newSingleThreadExecutor();

                        Future<?> future = executorService.submit(new Connection("login " + txtUsername.getText().toString() + " " + txtEmail.getText().toString() + " " + txtPassword.getText().toString()));

                        try {
                            future.get();
                            runOnUiThread(() -> {

                                Intent intent = new Intent(MainActivity.this, Find.class);
                                startActivity(intent);
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            executorService.shutdown();
                        }
                    }
                    else
                    {

                        ExecutorService executorService = Executors.newSingleThreadExecutor();
                        executorService.execute(new Connection("register "+txtUsername.getText()+" "+txtEmail.getText()+" "+txtPassword.getText()));
                    }
                }

                //executorService.execute(new Connection("login Admin111@gmail.com Admin"));
                //executorService.execute(new Connection("register Rares raresmucea111@gmail.com rarnet"));

            }
        });
    }
}