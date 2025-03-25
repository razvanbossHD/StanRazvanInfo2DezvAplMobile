package com.example.rate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Classes.Connection;
import Classes.Files;
import Classes.Game;

public class Find extends AppCompatActivity {


    void newButton( String Name, int ID)
    {

        LinearLayout linearLayout = findViewById(R.id.scrLayout);
        Button button = new Button(this);
        button.setText(Name);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                500
        );
        button.setLayoutParams(layoutParams);
        //button.setBackgroundColor(Color.TRANSPARENT);
        //button.setAlpha(0);
        linearLayout.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find.this, GamePage.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Find), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ExecutorService executorService = Executors.newSingleThreadExecutor();


        try {
            Future<?> future = executorService.submit(new Connection("getgames ", getFilesDir()));

            future.get();
            runOnUiThread(() -> {
                String[] games= Files.getKey(getFilesDir()).split(" ");
                for(int i=0;2*i+1<games.length&&games[i]!=null;++i){
                    System.out.println(games[2*i+1]+"  adsa");
                    newButton(games[2*i], Integer.parseInt(games[2*i+1]) );
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}