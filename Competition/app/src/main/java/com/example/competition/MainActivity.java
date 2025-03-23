package com.example.competition;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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

import Classes.Competitor;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnFilter, btnFilterScore, btnUpdate, btnSort;
    EditText txtFilter;

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
        Competitor comp=new Competitor("Nume");
        comp.Surname="Tress";
        comp.Score=10;
        btnAdd =(Button) findViewById(R.id.btnAddComp);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });
        btnFilter =(Button) findViewById(R.id.btnFilter);
        txtFilter=findViewById(R.id.txtFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = findViewById(R.id.layoutButoane);
                linearLayout.removeAllViews();
                Competitor[] Competitors= getcomp();
                for(int i=0;i<Competitors.length;++i)
                {
                    if(Competitors[i]!=null&&Competitors[i].Name.equals(txtFilter.getText().toString()))
                        newButton(Competitors[i]);
                    else
                    {
                        if(Competitors[i]!=null)
                            System.out.println(Competitors[i].Name+" "+txtFilter.getText().toString());
                    }
                }

            }
        });

        btnFilterScore =(Button) findViewById(R.id.btnFilterScore);
        btnFilterScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout linearLayout = findViewById(R.id.layoutButoane);
                linearLayout.removeAllViews();
                Competitor[] Competitors= getcomp();
                for(int i=0;i<Competitors.length;++i)
                {
                    if(Competitors[i]!=null&&Competitors[i].Score>=(Integer.parseInt(txtFilter.getText().toString())))
                        newButton(Competitors[i]);
                }

            }
        });
        btnUpdate =(Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtons();

            }
        });
        btnSort =(Button) findViewById(R.id.btnSort);
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = findViewById(R.id.layoutButoane);
                linearLayout.removeAllViews();
                Competitor[] Competitors= getcomp();
                for(int i=0;i<Competitors.length;++i)
                {
                    for(int j=0;j<Competitors.length;++j)
                    {
                        if(Competitors[i]!=null&&Competitors[j]!=null&&Competitors[i].Name.compareTo(Competitors[j].Name)>0)
                        {
                            Competitor comp=Competitors[i];
                            Competitors[i]=Competitors[j];
                            Competitors[j]=comp;
                        }
                    }
                }
                for(int i=0;i<Competitors.length;++i)
                {
                    if(Competitors[i]!=null)
                        newButton(Competitors[i]);
                }

            }
        });
        updateButtons();
    }
    private void savecomp(Competitor contact) {
        try {
            String nume = "competitor.txt";

            FileWriter fileWriter = new FileWriter(getFilesDir() + "/" + nume, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(contact.Name+"\n");
            writer.write(contact.Surname+"\n");
            writer.write(contact.Score+"\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Competitor[] getcomp() {
        File file=new File(getFilesDir(), "competitor.txt");
        Competitor[] competitors = new Competitor[100];
        int i=0;
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if(i%3==0)
                        competitors[i/3]=new Competitor(line);
                    else if(i%3==1)
                        competitors[i/3].Surname=line;
                    else if(i%3==2)
                        competitors[i/3].Score=Integer.parseInt(line);
                    ++i;
                }
            } catch (IOException e) {
                Log.e("File Error", "Error reading file", e);
            }
        } else {
            Log.d("File Content", "File does not exist.");
        }
        return competitors;
    }
    private void deletecomp(Competitor comp, Competitor[] Competitors) {
        File file=new File(getFilesDir(), "competitor.txt");
        file.delete();
        try {
            String nume = "contacts.txt";

            FileWriter fileWriter = new FileWriter(getFilesDir() + "/" + nume, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(int i=0;i<Competitors.length-1;++i)
            {
                if(Competitors[i]!=null&&Competitors[i].Name!=null&&Competitors[i].Surname!=null&&!Competitors[i].Name.equals(comp.Name))
                    savecomp(Competitors[i]);
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void updateButtons()
    {
        LinearLayout linearLayout = findViewById(R.id.layoutButoane);
        linearLayout.removeAllViews();
        Competitor[] competitors= getcomp();
        for(int i=0;i<competitors.length;++i)
        {
            if(competitors[i]!=null&&competitors[i].Name!=null&&competitors[i].Surname!=null)
                newButton(competitors[i]);
        }
    }
    void newButton(Competitor comp)
    {
        LinearLayout linearLayout = findViewById(R.id.layoutButoane);
        Button button = new Button(this);
        System.out.println("icid");
        button.setText(comp.Name);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,  // Width
                500
        );

        button.setLayoutParams(layoutParams);
        linearLayout.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}