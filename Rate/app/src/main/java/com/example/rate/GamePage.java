package com.example.rate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Comment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Classes.Comments;
import Classes.Connection;
import Classes.Files;

public class GamePage extends AppCompatActivity {
    String Name;
    String Description;
    int id=0;
    Float Rating;
    TextView txtGameName, txtGameRating, txtGameDescription, txtComment, txtRate;
    Button btnPost;
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
            if(d>10||d<0)
                return false;
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    void newcomment(Comments comments)
    {
        LinearLayout linearLayout = findViewById(R.id.body);
        TextView name = new TextView(this);
        ExecutorService executorService = Executors.newSingleThreadExecutor();


        try {Future<?> future = executorService.submit(new Connection("getaccount "+comments.name));
            future.get();
            runOnUiThread(() -> {
                name.setText(MainActivity.msj+"("+comments.rating+")");
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        name.setLayoutParams(layoutParams);
        linearLayout.addView(name);
        TextView lblcomment = new TextView(this);
        lblcomment.setText(comments.text);
        lblcomment.setLayoutParams(layoutParams);
        linearLayout.addView(lblcomment);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle b = getIntent().getExtras();
        id = b.getInt("ID");
        System.out.println("id");
        btnPost= findViewById(R.id.btnPost);
        txtComment= findViewById(R.id.txtComment);
        txtRate= findViewById(R.id.txtRate);
        txtGameDescription=findViewById(R.id.txtGameDescription);
        txtGameRating=findViewById(R.id.txtGameRating);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                try {
                    System.out.println("alala");
                    if(isNumeric(txtRate.getText().toString())){
                        System.out.println("aha");
                        Future<?> future = executorService.submit(new Connection("post "+id+" "+MainActivity.id+" "+txtComment.getText().toString()+" "+txtRate.getText().toString()));
                        future.get();
                        runOnUiThread(() -> {

                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    executorService.shutdown();
                }
            }
        });
        txtGameName = findViewById(R.id.txtGameName);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {

            Future<?> future = executorService.submit(new Connection("getgame "+id));
            future.get();
            runOnUiThread(() -> {
                String key= MainActivity.msj;
                if(key!=null&& !key.isEmpty()){
                    String[] strin = key.split("non404");
                    this.Name=strin[0];
                    txtGameName.setText(strin[0]);
                    this.Description=strin[1];
                    this.Rating=Float.parseFloat(strin[2]);
                    txtGameDescription.setText(Description);
                    String t = "Rating:"+Rating+"/10";
                    txtGameRating.setText(t);
                }});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        executorService = Executors.newSingleThreadExecutor();
        try {
            Future<?> future = executorService.submit(new Connection("getcomments "+id));
            future.get();
            runOnUiThread(() -> {

                Comments[] key= Files.getcomments(getFilesDir());
                for(int i=0;i<key.length&&key[i]!=null;++i)
                    newcomment(key[i]);

            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}