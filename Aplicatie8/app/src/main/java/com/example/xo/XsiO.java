package com.example.xo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class XsiO extends AppCompatActivity {
    char[][] tabel = new char[3][3];
    char curr='x';

    void checkfull()
    {

        boolean chec= false;
        for(int i=0;i<9;++i)
        {
            if (tabel[i / 3][i % 3] != 'a') {
                chec = true;
                break;
            }
        }
        if(!chec)
            finish();
    }
    boolean checkwin()
    {
        for(int i=0;i<3;++i)
        {
            if(tabel[i][0]!='a'&&(tabel[i][0]==tabel[i][1]&&tabel[i][0]==tabel[i][2]))
                finish();
            if(tabel[0][i]!='a'&&(tabel[0][i]==tabel[1][i]&&tabel[0][i]==tabel[2][i]))
                finish();
        }

        if(tabel[0][0]!='a'&&(tabel[0][0]==tabel[1][1]&&tabel[0][0]==tabel[2][2]))
            finish();
        if(tabel[2][0]!='a'&&(tabel[2][0]==tabel[1][1]&&tabel[2][0]==tabel[0][2]))
            finish();
        return false;
    }
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xsi_o);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tabel[0][0]='a';
        btn1 =(Button) findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfull();
                tabel[0][0]=curr;
                btn1.setText(curr);
                if(curr=='X')
                    curr='O';
                else
                    curr='x';
                checkwin();


            }
        });
        tabel[0][1]='a';
        btn2 =(Button) findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfull();
                tabel[0][1]=curr;
                btn2.setText(curr);
                if(curr=='X')
                    curr='O';
                else
                    curr='x';
                checkwin();
            }
        });
        tabel[0][2]='a';
        btn3 =(Button) findViewById(R.id.button3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfull();
                tabel[0][2]=curr;
                btn3.setText(curr);
                if(curr=='X')
                    curr='O';
                else
                    curr='x';
                checkwin();
            }
        });
        tabel[1][0]='a';
        btn4 =(Button) findViewById(R.id.button4);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfull();
                tabel[1][0]=curr;
                btn4.setText(curr);
                if(curr=='X')
                    curr='O';
                else
                    curr='x';
                checkwin();
            }
        });
        tabel[1][1]='a';
        btn5 =(Button) findViewById(R.id.button5);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfull();
                System.out.println(curr);
                tabel[1][1]=curr;
                System.out.println(curr);
                btn5.setText(curr);
                if(curr=='X')
                    curr='O';
                else
                    curr='x';
            }
        });
        tabel[1][2]='a';
        btn6 =(Button) findViewById(R.id.button6);

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfull();
                tabel[1][2]=curr;
                btn6.setText(curr);
                if(curr=='X')
                    curr='O';
                else
                    curr='x';
                checkwin();
            }
        });
        tabel[2][0]='a';
        btn7 =(Button) findViewById(R.id.button7);

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfull();
                tabel[2][0]=curr;
                btn7.setText(curr);
                if(curr=='X')
                    curr='O';
                else
                    curr='x';
                checkwin();
            }
        });
        tabel[2][1]='a';
        btn8 =(Button) findViewById(R.id.button8);

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfull();
                tabel[2][1]=curr;
                btn8.setText(curr);
                if(curr=='X')
                    curr='O';
                else
                    curr='x';
                checkwin();
            }
        });
        tabel[2][2]='a';
        btn9 =(Button) findViewById(R.id.button9);

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfull();
                tabel[2][2]=curr;
                btn9.setText(curr);
                if(curr=='X')
                    curr='O';
                else
                    curr='x';
                checkwin();
            }
        });
    }
}