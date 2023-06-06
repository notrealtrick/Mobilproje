package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


            /////////// Veri tabanı işlemleri
        try {
            database = this.openOrCreateDatabase("AfetDB", MODE_PRIVATE,null);

            database.execSQL("CREATE TABLE IF NOT EXISTS testler (id INT, afetAd VARCHAR, soru VARCHAR, A VARCHAR, B VARCHAR, C VARCHAR, D VARCHAR, cevap VARCHAR)");

        }
        catch (Exception e){
            e.printStackTrace();
        }

        try {

            database.execSQL("INSERT INTO testler (id,afetAd, soru, A, B, C, D, cevap)  VALUES(0,'Deprem','Aşağıdakilerden hangisi bir deprem şiddeti olamaz? ','20.5','6.5','4.5','3.6','20.5')");
            Toast.makeText(getApplicationContext(), "Soru eklendi", Toast.LENGTH_LONG).show();


        }
        catch (Exception e){
            e.printStackTrace();

        }



    }


    public void veriAlma(){

        Cursor cursor = database.rawQuery("SELECT * FROM testler;",null);
        int idIndex = cursor.getColumnIndex("id");




        while (cursor.moveToNext()) {
            System.out.println(" id= " + cursor.getInt(idIndex) );

        }


        cursor.close();






    }

}