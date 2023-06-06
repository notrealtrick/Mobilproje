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
import android.widget.ImageView;
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

    ImageView image;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);




        Intent veriAl = getIntent();


        afet = veriAl.getStringExtra("afet");

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
        try {


            database.execSQL("INSERT INTO Afetler (id,afetAd, bilgi,videoUrl)  VALUES(2,'Deprem','Deprem doğal bir felakettir ve yer kabuğundaki hareketler sonucunda oluşur. Yer kabuğu parçalarının ani kaymasıyla ortaya çıkar. Bu hareketler sırasında yer sarsılır ve büyük enerji açığa çıkar. Depremler tehlikeli olabilir ve can ve mal kaybına neden olabilir. Çocuklar depremlere karşı hazırlıklı olmalıdır. Deprem anında güvende olmak için önceden belirlenmiş bir güvenli alan belirlemek önemlidir. Deprem sırasında panik yapmadan sakin kalmak ve ebeveynlerin talimatlarını dinlemek gereklidir. Deprem öncesi ve sonrası aileyle birlikte plan yapmak, acil durum çantası hazırlamak ve acil numaraları öğrenmek de önemlidir. Depremlerden korunmak için doğru bilgilere sahip olmak ve deprem güvenliği hakkında eğitim almak gereklidir.','https://www.youtube.com/watch?v=veXjMajERLI')");
            Toast.makeText(getApplicationContext(), "Kayıt Başarıyla Eklendi", Toast.LENGTH_LONG).show();

            veriAlma();


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



        geriButon = findViewById(R.id.geriButton);
        geriButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }



        });



    }









    public void veriAlma(){



        int index=0;
        Cursor cursor = database.rawQuery("SELECT * FROM Afetler;",null);
        int idIndex = cursor.getColumnIndex("id");
        int adIndex  = cursor.getColumnIndex("afetAd");
        int bilgiIndex = cursor.getColumnIndex("bilgi");










        while (cursor.moveToNext()) {


            adText.setText(cursor.getString(adIndex));
            bilgiText.setText(cursor.getString(bilgiIndex));

            }


        cursor.close();



    }










}




