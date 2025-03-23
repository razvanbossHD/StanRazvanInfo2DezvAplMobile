package com.example.phonebook;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Clase.Contacts;

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
        Contacts cont1= new Contacts("Stan", "098642833", "2345414@gmail.com");
        getcontacts();
    }
    private void savecontact(Contacts contact) {
        try {
            String nume = "contacts.txt";

            FileWriter fileWriter = new FileWriter(getFilesDir() + "/" + nume, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write("Name:"+contact.getName()+"\n");
            writer.write("Phone:"+contact.getPhone()+"\n");
            writer.write("Email:"+contact.getEmail()+"\n");
            writer.close();

            // You can also use Log or Toast to display a success message
            System.out.println("Text appended to file successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Contacts[] getcontacts() {
        File file=new File(getFilesDir(), "contacts.txt");
        Contacts[] contacts= new Contacts[100];
        int i=0;
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if(i%3==0)
                        contacts[i/3]=new Contacts(line);
                    else if(i%3==1)
                        contacts[i/3].setPhone(line);
                    else if(i%3==2)
                        contacts[i/3].setEmail(line);
                    System.out.println(line);
                    ++i;
                }
            } catch (IOException e) {
                Log.e("File Error", "Error reading file", e);
            }
        } else {
            Log.d("File Content", "File does not exist.");
        }
        return contacts;
    }
    void newButton(Contacts contact)
    {
        LinearLayout linearLayout = findViewById(R.id.layoutButoane);
        Button button = new Button(this);
        button.setText(contact.getName());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,  // Width
                500
        );
        button.setLayoutParams(layoutParams);
        //button.setBackgroundColor(Color.TRANSPARENT);
        //button.setAlpha(0);
        linearLayout.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Open the file for writing
                    FileOutputStream fos = context.openFileOutput("myfile.txt", Context.MODE_PRIVATE);
                    // Write the data to the file
                    fos.write(data.getBytes());
                    // Close the output stream
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}