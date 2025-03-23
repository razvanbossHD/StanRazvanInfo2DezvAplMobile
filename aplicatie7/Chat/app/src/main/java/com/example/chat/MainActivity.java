package com.example.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView lblText;
    EditText txtMessage;
    Button btnSend, btnToUser2;
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
        lblText= findViewById(R.id.lblText2);
        getMessages();
        txtMessage =findViewById(R.id.txtMessage);

        btnSend =(Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtMessage.getText().toString()!="")
                {
                    sendMessage(txtMessage.getText().toString());
                    getMessages();
                }
            }
        });
        btnToUser2 =(Button) findViewById(R.id.btnToUser2);

        btnToUser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, User2.class);
                startActivity(intent);
            }
        });
    }
    private void sendMessage(String Message) {
        try {
            String nume = "conv.txt";

            // Open the file in append mode, or create it if it doesn't exist
            FileWriter fileWriter = new FileWriter(getFilesDir() + "/" + nume, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            // Write the text to the file
            writer.write("Name1:"+Message+"\n");
            writer.close();

            // You can also use Log or Toast to display a success message
            System.out.println("Text appended to file successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMessages() {
        File file=new File(getFilesDir(), "conv.txt");
        String conc="";
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    conc=conc+"\n"+line;
                }
            } catch (IOException e) {
                Log.e("File Error", "Error reading file", e);
            }
        } else {
            Log.d("File Content", "File does not exist.");
        }

        lblText.setText(conc);
    }
}