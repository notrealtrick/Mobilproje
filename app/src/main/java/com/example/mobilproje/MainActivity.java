package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button depremButon,selButon,heyelanButon,cigButon,firtinaButon,orman_yanginButon;
    Context context = this;
    public String afet = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        depremButon = findViewById(R.id.deprem_button);
        selButon = findViewById(R.id.sel_button);
        cigButon = findViewById(R.id.cig_button);
        heyelanButon = findViewById(R.id.heyelan_button);
        orman_yanginButon = findViewById(R.id.orman_yanginlari_button);
        firtinaButon = findViewById(R.id.firtina_button);


        depremButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                afet = "Deprem";


                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("afet",afet);

                startActivity(intent);

            }




        });
        selButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                afet = "Sel";


                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("afet",afet);

                startActivity(intent);
            }




        });
        cigButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                afet = "Çığ";


                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("afet",afet);

                startActivity(intent);

            }




        });

        heyelanButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                afet = "Heyelan";


                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("afet",afet);

                startActivity(intent);

            }




        });

        orman_yanginButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                afet = "Orman";


                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("afet",afet);

                startActivity(intent);

            }




        });

        firtinaButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                afet = "Fırtına";


                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("afet",afet);

                startActivity(intent);

            }




        });







    }





    }
