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

import java.security.Key;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

import Classes.Connection;
import Classes.Files;

public class MainActivity extends AppCompatActivity {
    Button btnEnter;
    public static String msj="";
    EditText txtEmail, txtPassword, txtUsername;
    TextView lblUsername;
    Switch stcLogin, stcCookies;

    public static int id=1;
    boolean getid(String key)
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        AtomicBoolean ret = new AtomicBoolean(false);
        try {Future<?> future = executorService.submit(new Connection("checkkey " + key));
            future.get();
            runOnUiThread(() -> {
                if(MainActivity.msj!=null){
                    ret.set(true);
                    id=Integer.parseInt(MainActivity.msj);
                    System.out.println(id);
                }
                ret.set(false);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ret.get());
        return ret.get();
    }
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
                if(!stcLogin.isChecked()){ExecutorService executorService = Executors.newSingleThreadExecutor();


                        try {Future<?> future = executorService.submit(new Connection("login " + txtUsername.getText().toString() + " " + txtEmail.getText().toString() + " " + txtPassword.getText().toString()));

                            future.get();
                            runOnUiThread(() -> {
                                String[] rasp =MainActivity.msj.split(" ");
                                Files.setKey( getFilesDir(),rasp[0]);
                                MainActivity.id=Integer.parseInt(rasp[1]);
                                String key=Files.getKey(getFilesDir());
                                if(!key.isEmpty()){
                                    Intent intent = new Intent(MainActivity.this, Find.class);
                                    startActivity(intent);}
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            executorService.shutdown();
                        }
                    }
                    else if(stcLogin.isChecked()){ExecutorService executorService = Executors.newSingleThreadExecutor();

                            try {
                                Future<?> future = executorService.submit(new Connection("register "+txtUsername.getText()+" "+txtEmail.getText()+" "+txtPassword.getText()));
                                future.get();
                                runOnUiThread(() -> {
                                    String[] rasp =MainActivity.msj.split(" ");
                                    Files.setKey( getFilesDir(),rasp[0]);
                                    MainActivity.id=Integer.parseInt(rasp[1]);
                                    String key=Files.getKey(getFilesDir());
                                    if(!key.isEmpty()){
                                        Intent intent = new Intent(MainActivity.this, Find.class);
                                        startActivity(intent);}
                                });

                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                executorService.shutdown();
                            }
                        }
                }

                //executorService.execute(new Connection("login Admin111@gmail.com Admin"));
                //executorService.execute(new Connection("register Rares raresmucea111@gmail.com rarnet"));
        });
    }
}