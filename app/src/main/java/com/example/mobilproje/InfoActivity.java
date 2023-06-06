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


















}




