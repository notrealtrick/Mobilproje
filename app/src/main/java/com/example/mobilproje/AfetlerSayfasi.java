package com.example.mobilproje;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class AfetlerSayfasi extends AppCompatActivity {

    private VideoView videoView;
    private TextView infoTextView;
    private Button quizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afetler_sayfasi);

        videoView = findViewById(R.id.videoView);
        infoTextView = findViewById(R.id.infoTextView);
        quizButton = findViewById(R.id.quizButton);

        // Video ayarları
        String videoURL = "https://www.youtube.com/watch?v=VIDEO_ID";
        Uri videoUri = Uri.parse(videoURL);
        videoView.setVideoURI(videoUri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        // Quiz butonu tıklama olayı
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quiz sayfasını açmak için gerekli kodlar buraya gelecek
                // Örneğin:
                Intent intent = new Intent(AfetlerSayfasi.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }
}
