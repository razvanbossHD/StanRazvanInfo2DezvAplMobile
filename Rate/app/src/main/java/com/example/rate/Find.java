package com.example.rate;

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

public class Find extends AppCompatActivity {
    private static final String SERVER_IP = "10.0.2.2";  // Your Python server IP
    private static final int SERVER_PORT = 65432;        // Your Python server port

    private class ConnectTask implements Runnable {

        @Override
        public void run() {
            String response = "";
            try {
                // Create a socket to connect to the server
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);

                // Send a message to the server
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("Hello from Android client!".getBytes());
                outputStream.flush();

                // Receive the response from the server
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(inputStream.readLine());  // Read the server's response

                socket.close();  // Close the socket
            } catch (UnknownHostException e) {
                e.printStackTrace();
                response = "Unknown host exception.";
            } catch (IOException e) {
                e.printStackTrace();
                response = "IOException occurred.";
            }
        }
    }

    void newButton()
    {
        LinearLayout linearLayout = findViewById(R.id.scrLayout);
        Button button = new Button(this);
        button.setText("adsx");
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
        executorService.execute(new ConnectTask());
        newButton();
        newButton();
        newButton();
        newButton();
        newButton();
        newButton();
    }
}