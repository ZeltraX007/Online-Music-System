package com.example.musicappserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NavigatorActivity extends AppCompatActivity {

    private Button goToUpload, goToSong, goToAllSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

            goToSong = findViewById(R.id.goToSong);
            goToUpload = findViewById(R.id.goToUpload);
            goToAllSongs = findViewById(R.id.goToAllSongs);

            goToUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(NavigatorActivity.this, UploadActivity.class));
                }
            });

            goToSong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(NavigatorActivity.this, ClientActivity.class));
                }
            });

            goToAllSongs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(NavigatorActivity.this, AllSongsActivity.class));
                }
            });
    }
}