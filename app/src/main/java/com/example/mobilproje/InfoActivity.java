package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class InfoActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private VideoView videoView;

    Button geriButon;
    Button ekleButon;
    TextView adText,bilgiText;
    String afet;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent veriAl = getIntent();
        afet = veriAl.getStringExtra("afet");

        // Video ayarları
        String videoURL = "https://www.youtube.com/watch?v=VIDEO_ID";
        Uri videoUri = Uri.parse(videoURL);
        videoView.setVideoURI(videoUri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);


        adText = findViewById(R.id.afet_isim_text);
        bilgiText = findViewById(R.id.bilgilendirme_metni_text);

        adText.setText(afet);

        try {
            database = this.openOrCreateDatabase("AfetDB", MODE_PRIVATE,null);

            database.execSQL("CREATE TABLE IF NOT EXISTS Afetler (id INT, afetAd VARCHAR, bilgi VARCHAR,videoUrl VARCHAR)");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            database = this.openOrCreateDatabase("AfetDB", MODE_PRIVATE,null);

            database.execSQL("CREATE TABLE IF NOT EXISTS Testler (id INT, afetAd VARCHAR, Soru VARCHAR, cevapA VARCHAR, cevapB VARCHAR, cevapC VARCHAR, cevapD VARCHAR, cevap VARCHAR)");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        ekleButon = findViewById(R.id.ekleButon);
        ekleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    database.execSQL("INSERT INTO Afetler (id,afetAd, bilgi,videoUrl)  VALUES(0,'deneme','deneme','deneme')");
                    Toast.makeText(getApplicationContext(), "Kayıt Başarıyla Eklendi", Toast.LENGTH_LONG).show();
                    veriAlma();
                }
                catch (Exception e){
                    e.printStackTrace();

                }
                try {


                    database.execSQL("INSERT INTO Testler (id,afetAd, Soru,cevapA,cevapB,cevapC,cevapD,cevap)  VALUES(0,'deneme','deneme Sorusu','Ankara','İzmir','İstanbul','Antalya','Antalya')");
                    Toast.makeText(getApplicationContext(), "Soru eklendi", Toast.LENGTH_LONG).show();
                    veriAlma2();
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
    public void veriAlma(){
        Cursor cursor = database.rawQuery("SELECT * FROM Afetler",null);

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



    }
    public void veriAlma2(){
        Cursor cursor = database.rawQuery("SELECT * FROM Testler",null);

        int idIndex = cursor.getColumnIndex("id");
        int adIndex = cursor.getColumnIndex("afetAd");
        int soruIndex = cursor.getColumnIndex("Soru");

        int cevapAIndex = cursor.getColumnIndex("cevapA");
        int cevapBIndex = cursor.getColumnIndex("cevapB");
        int cevapCIndex = cursor.getColumnIndex("cevapC");
        int cevapDIndex = cursor.getColumnIndex("cevapD");
        int cevapIndex = cursor.getColumnIndex("cevap");


        while (cursor.moveToNext()) {
            System.out.println(cursor.getString(adIndex));
            System.out.println(cursor.getString(soruIndex));
            System.out.println(cursor.getString(cevapAIndex));
            System.out.println(cursor.getString(cevapBIndex));
            System.out.println(cursor.getString(cevapCIndex));
            System.out.println(cursor.getString(cevapDIndex));
            System.out.println(cursor.getString(cevapIndex));
        }


        cursor.close();



    }




}

