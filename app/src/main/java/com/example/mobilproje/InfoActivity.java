package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class InfoActivity extends AppCompatActivity {


    ///// Değişken tanımlanması
    private SQLiteDatabase database;

    Button geriButon;
    Button ekleButon;
    TextView adText,bilgiText;
    String afet;


    Button test;
    ImageView ImageView = findViewById(R.id.ImageView);




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);




        Intent veriAl = getIntent();


        afet = veriAl.getStringExtra("afet");
      /*  switch (afet) {
            case "deprem":
                ImageView.setImageResource(R.drawable.deprem_icon);
                break;
            case "cig":
                ImageView.setImageResource(R.drawable.cig_icon);
                break;
            case "firtina":
                ImageView.setImageResource(R.drawable.firtina_icon);
                break;
            case "heyelan":
                ImageView.setImageResource(R.drawable.heyelan_icon);
                break;
            case "orman;yanginlari":
                ImageView.setImageResource(R.drawable.orman_yanginlari_icon);
                break;
            case "sel":
                ImageView.setImageResource(R.drawable.sel_icon);
                break;

            default:
                ImageView.setImageResource(R.drawable.afetler);
                break;
        }
*/


            adText = findViewById(R.id.afet_isim_text);
        bilgiText = findViewById(R.id.bilgilendirme_metni_text);

        adText.setText(afet);
        test = findViewById(R.id.testButton);



        //// Veri tabanı tablo oluşturma işlemi
        try {
            database = this.openOrCreateDatabase("AfetDB", MODE_PRIVATE,null);

            database.execSQL("CREATE TABLE IF NOT EXISTS Afetler (id INT, afetAd VARCHAR, bilgi VARCHAR,videoUrl VARCHAR)");

        }
        catch (Exception e){
            e.printStackTrace();
        }




        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, TestActivity.class);
                intent.putExtra("afet",afet);

                startActivity(intent);
            }
        });

        //// Geçici verilerin eklenmesi butonu
        ekleButon = findViewById(R.id.ekleButon);
        ekleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    database.execSQL("INSERT INTO Afetler (id,afetAd, bilgi,videoUrl)  VALUES(0,'deneme3','deneme','https://www.youtube.com/watch?v=8kooIgKESYE&ab_channel=JustMontage')");
                    Toast.makeText(getApplicationContext(), "Kayıt Başarıyla Eklendi", Toast.LENGTH_LONG).show();
                    veriAlma();




                }
                catch (Exception e){
                    e.printStackTrace();

                }

            }
        });


        geriButon = findViewById(R.id.geriButton);
        geriButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }



        });



    }



    ///// Bilgi ekranında görünecek bilgiler için veri tabanı bağlantı işlemleri
    public void veriAlma(){

        Cursor cursor = database.rawQuery("SELECT * FROM Afetler;",null);
        int idIndex = cursor.getColumnIndex("id");
        int adIndex = cursor.getColumnIndex("afetAd");
        int bilgiIndex = cursor.getColumnIndex("bilgi");
        int videoIndex = cursor.getColumnIndex("videoUrl");



        while (cursor.moveToNext()) {
            System.out.println(" id= " + cursor.getInt(idIndex) + " ad = " + cursor.getString(adIndex) + " bilgi = " + cursor.getString(bilgiIndex) + " videoUrl=" + cursor.getString(videoIndex));
            adText.setText(cursor.getString(adIndex));
            bilgiText.setText(cursor.getString(bilgiIndex));

        }


        cursor.close();



        // getVideoUrlFromDatabase();


    }



}




