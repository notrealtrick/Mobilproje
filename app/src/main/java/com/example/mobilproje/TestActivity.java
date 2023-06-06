package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity  {
    private SQLiteDatabase database;


    TextView textSoru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textSoru = findViewById(R.id.soru_metin_text);



        //// Veri tabanı tablo oluşturma işlemi
        try {
            database = this.openOrCreateDatabase("AfetDB", MODE_PRIVATE,null);

            database.execSQL("CREATE TABLE IF NOT EXISTS Sorular (id INT, afetAd VARCHAR, soru VARCHAR,cevap1 VARCHAR,cevap2 VARCHAR,cevap3 VARCHAR,cevap4 VARCHAR,cevap VARCHAR)");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {


            database.execSQL("INSERT INTO Afetler (id,afetAd, soru,cevap1,cevap2,cevap3,cevap4,cevap)  VALUES(0,'Aşağıdakilerden hangisi bir deprem yüksekliği olamaz?','5.6','6.5','3.6','36.1','36.1')");
            Toast.makeText(getApplicationContext(), "Kayıt Başarıyla Eklendi", Toast.LENGTH_LONG).show();

            veriAlma();


        }
        catch (Exception e){
            e.printStackTrace();

        }


    }


    public void veriAlma(){


        int index=0;
        Cursor cursor = database.rawQuery("SELECT * FROM Sorular;",null);
        int idIndex = cursor.getColumnIndex("id");
        int adIndex  = cursor.getColumnIndex("afetAd");
        int soruIndex = cursor.getColumnIndex("soru");









        while (cursor.moveToNext()) {

            textSoru.setText(cursor.getString(soruIndex));


        }


        cursor.close();







    }

}