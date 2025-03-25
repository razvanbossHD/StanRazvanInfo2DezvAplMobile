package com.example.rate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Classes.Connection;
import Classes.Files;

public class NewGame extends AppCompatActivity {
    EditText txtName, txtGenre, txtDescription;
    Button btnEnterGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtName= findViewById(R.id.txtName);
        txtGenre= findViewById(R.id.txtGenre);
        txtDescription= findViewById(R.id.txtDescription);
        btnEnterGame = findViewById(R.id.btnEnterGame);
        btnEnterGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                if(txtDescription.getText().toString()!=""&&txtGenre.getText().toString()!=""&&txtDescription.getText().toString()!=""){
                    try {Future<?> future = executorService.submit(new Connection("newgame " + txtName.getText().toString() + " " + txtGenre.getText().toString() + " " + txtDescription.getText().toString(), getFilesDir()));
                        String key=Files.getKey(getFilesDir());
                        future.get();
                        finish();
                        runOnUiThread(() -> {
                            if(key.length()>10){
                                int x=0;
                            }});
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        executorService.shutdown();
                    }}

            }
        });
    }
}