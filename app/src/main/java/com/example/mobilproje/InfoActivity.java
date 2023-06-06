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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;




public class InfoActivity extends AppCompatActivity {


    ///// Değişken tanımlanması
    private SQLiteDatabase database;

    Button geriButon;
    Button ekleButon;
    TextView adText,bilgiText;
    String afet;

    VideoView video;
    Button test;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);




        Intent veriAl = getIntent();


        afet = veriAl.getStringExtra("afet");



        adText = findViewById(R.id.afet_isim_text);
        bilgiText = findViewById(R.id.bilgilendirme_metni_text);
        video = findViewById(R.id.videoView);

        adText.setText(afet);
        test = findViewById(R.id.testButton);
        veriAlma();

        video.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=OJtmO737jFw"));

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


                    database.execSQL("INSERT INTO Afetler (id,afetAd, bilgi,videoUrl)  VALUES(1,'Deprem','Deprem doğal bir afettir ve yer kabuğundaki hareketler sonucunda meydana gelir. Aniden oluşan sarsıntılarla karakterizedir ve büyük yıkımlara neden olabilir. Depremde, evler, binalar ve diğer yapılar hasar görebilir veya yıkılabilir. Bu durum insanlara ve özellikle çocuklara zarar verebilir. Deprem sırasında çocuklar korku ve panik yaşayabilir. Güvende olmak için hızla açık bir alana çıkmalı veya masaların altına saklanmalıdırlar. Depremlere karşı hazırlıklı olmak önemlidir. Deprem çantası ve acil durum planı gibi önlemler almak, çocukları ve aileleri daha güvende tutabilir.','https://www.youtube.com/watch?v=veXjMajERLI')");
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








        Cursor cursor1 = database.rawQuery("SELECT * FROM Afetler;",null);
        int idIndex = cursor1.getColumnIndex("id");
        int adIndex  = cursor1.getColumnIndex("afetAd");
        int bilgiIndex = cursor1.getColumnIndex("bilgi");
        int index = 0;
        switch (afet)
        {
            case "Deprem":
                index = 1;
                break;
            case "Sel":
                index = 2;
                break;
            case "Fırtına":
                break;
            case "Orman":
                break;
            case "Çığ":
                break;
                


        }

        while (cursor1.moveToNext()) {
            if((String)cursor1.getString(adIndex)=="")
            {}

        }
        System.out.println("Buraya gelindi");

        cursor1.close();



    }
    public void getVideoUrlFromDatabase() {
        String query = "SELECT url FROM Afetler  ";
        Cursor cursor = database.rawQuery(query, null);

    if (cursor.moveToFirst()) {
        @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex("url"));
        video.setVideoURI(Uri.parse(url));
        video.start();

        System.out.println(Uri.parse(url));
    }









    }
    public void testler(){








}


}




