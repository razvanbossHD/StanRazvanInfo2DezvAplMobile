package com.example.competition;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;

public class Add extends AppCompatActivity {

    EditText txtNume, txtPrenume, txtScor;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtNume =(EditText)findViewById(R.id.txtNume);
        txtPrenume=(EditText)findViewById(R.id.txtSurname);
        txtScor=(EditText)findViewById(R.id.txtScor);
        btnAdd = findViewById(R.id.BtnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nume = "competitor.txt";

                    FileWriter fileWriter = new FileWriter(getFilesDir() + "/" + nume, true);
                    BufferedWriter writer = new BufferedWriter(fileWriter);

                    writer.write(txtNume.getText().toString()+"\n");
                    writer.write(txtPrenume.getText().toString()+"\n");
                    writer.write(txtScor.getText().toString()+"\n");
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

    }
}