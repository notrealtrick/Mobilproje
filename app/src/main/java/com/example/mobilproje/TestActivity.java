package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity  {
    private SQLiteDatabase database;


    TextView textSoru;
    RadioButton r1,r2,r3,r4;

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
        int cevap1 = cursor.getColumnIndex("cevap1");
        int cevap2 = cursor.getColumnIndex("cevap2");
        int cevap3 = cursor.getColumnIndex("cevap3");
        int cevap4 = cursor.getColumnIndex("cevap4");
        int cevap = cursor.getColumnIndex("cevap");








        while (cursor.moveToNext()) {

            textSoru.setText(cursor.getString(soruIndex));
            r1.setText(cursor.getString(cevap1));
            r2.setText(cursor.getString(cevap2));
            r3.setText(cursor.getString(cevap3));
            r4.setText(cursor.getString(cevap4));

        }


        cursor.close();







    }

}