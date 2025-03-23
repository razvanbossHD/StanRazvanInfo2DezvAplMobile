package com.example.apl6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    Button btnNext, btnPrev;
    int pag=0;
    void ind()
    {
        Fragment fragment;
        pag=pag+1;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment currentFragment;
        switch (pag){
            case 1:
                currentFragment = getSupportFragmentManager().findFragmentByTag(Pag1.class.getSimpleName());
                if (currentFragment != null) {
                    transaction.remove(currentFragment);
                }
                fragment= new Pag1();
                break;
            case 2:
                currentFragment = getSupportFragmentManager().findFragmentByTag(Pag2.class.getSimpleName());
                if (currentFragment != null) {
                    transaction.remove(currentFragment);
                }
                break;
            case 3:
                currentFragment = getSupportFragmentManager().findFragmentByTag(Pag3.class.getSimpleName());
                if (currentFragment != null) {
                    transaction.remove(currentFragment);
                }
                break;
            case 4:
                currentFragment = getSupportFragmentManager().findFragmentByTag(Pag4.class.getSimpleName());
                if (currentFragment != null) {
                    transaction.remove(currentFragment);
                }
                break;}
        fragment= new Index();
        transaction.replace(R.id.main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

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
        btnNext = (Button) findViewById(R. id.btnNext);
        btnNext. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                pag=pag+1;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                Fragment currentFragment;
                switch (pag){
                    case 1:
                        fragment= new Pag1();
                        break;
                    case 2:
                        currentFragment = getSupportFragmentManager().findFragmentByTag(Pag1.class.getSimpleName());
                        if (currentFragment != null) {
                            transaction.remove(currentFragment);
                        }
                        fragment= new Pag2();
                        break;
                    case 3:
                        currentFragment = getSupportFragmentManager().findFragmentByTag(Pag2.class.getSimpleName());
                        if (currentFragment != null) {
                            transaction.remove(currentFragment);
                        }
                        fragment= new Pag3();
                        break;
                    case 4:
                        currentFragment = getSupportFragmentManager().findFragmentByTag(Pag3.class.getSimpleName());
                        if (currentFragment != null) {
                            transaction.remove(currentFragment);
                        }
                        fragment= new Pag4();
                        break;
                    default:
                        fragment= new Index();
                        ind();
                        break;

                }transaction.replace(R.id.main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnPrev = (Button) findViewById(R. id.btnPrev);
        btnPrev. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                pag=pag-1;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                Fragment currentFragment;
                switch (pag){
                    case 1:
                        currentFragment = getSupportFragmentManager().findFragmentByTag(Pag2.class.getSimpleName());
                        if (currentFragment != null) {
                            transaction.remove(currentFragment);
                        }
                        fragment= new Pag1();
                        break;
                    case 2:
                        currentFragment = getSupportFragmentManager().findFragmentByTag(Pag3.class.getSimpleName());
                        if (currentFragment != null) {
                            transaction.remove(currentFragment);
                        }
                        fragment= new Pag2();
                        break;
                    case 3:
                        currentFragment = getSupportFragmentManager().findFragmentByTag(Pag4.class.getSimpleName());
                        if (currentFragment != null) {
                            transaction.remove(currentFragment);
                        }
                        fragment= new Pag3();
                        break;
                    case 4:
                        fragment= new Pag4();
                        break;
                    default:
                        fragment= new Index();
                        break;

                }
                transaction.replace(R.id.main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnPrev = (Button) findViewById(R. id.btnPrev);
        btnPrev. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                fragment= new Index();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}