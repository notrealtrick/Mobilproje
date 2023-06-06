package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


            /////////// Veri tabanı işlemleri
        try {
            database = this.openOrCreateDatabase("AfetDB", MODE_PRIVATE,null);

            database.execSQL("CREATE TABLE IF NOT EXISTS testler (id INT, afetAd VARCHAR, Soru VARCHAR, cevapA VARCHAR, cevapB VARCHAR, cevapC VARCHAR, cevapD VARCHAR, cevap VARCHAR)");

        }
        catch (Exception e){
            e.printStackTrace();
        }

        try {

            database.execSQL("INSERT INTO testler (id,afetAd, Soru,cevapA,cevapB,cevapC,cevapD,cevap)  VALUES(0,'Deprem','Aşağıdakilerden hangisi bir deprem şiddeti olamaz? ','20.5','6.5','4.5','3.6','8.1')");
            Toast.makeText(getApplicationContext(), "Soru eklendi", Toast.LENGTH_LONG).show();


        }
        catch (Exception e){
            e.printStackTrace();

        }


    }
    public void veriAlma2(){
        Cursor cursor1 = database.rawQuery("SELECT * FROM testler",null);

        int idIndex = cursor1.getColumnIndex("id");
        int AFETadIndex = cursor1.getColumnIndex("afetAd");
        int soruIndex = cursor1.getColumnIndex("Soru");

        int cevapAIndex = cursor1.getColumnIndex("cevapA");
        int cevapBIndex = cursor1.getColumnIndex("cevapB");
        int cevapCIndex = cursor1.getColumnIndex("cevapC");
        int cevapDIndex = cursor1.getColumnIndex("cevapD");
        int cevapIndex = cursor1.getColumnIndex("cevap");


        while (cursor1.moveToNext()) {
            System.out.println("Cevap D: "+cursor1.getString(AFETadIndex));
        }


        cursor1.close();



    }

}