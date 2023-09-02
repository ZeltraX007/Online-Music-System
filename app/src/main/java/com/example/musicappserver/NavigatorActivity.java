package com.example.musicappserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NavigatorActivity extends AppCompatActivity {
    private static final int RECORD_AUDIO_PERMISSION_CODE = 1;
    private static final int MODIFY_AUDIO_SETTINGS_PERMISSION_CODE = 2;

    private Button goToUpload, goToSong, goToAllSongs, btnPlaylist,goToRecording,goToLiked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

            goToSong = findViewById(R.id.goToSong);
            goToUpload = findViewById(R.id.goToUpload);
            goToAllSongs = findViewById(R.id.goToAllSongs);
            btnPlaylist = findViewById(R.id.btnPlaylist);
            goToRecording = findViewById(R.id.goToRecording);
            goToLiked = findViewById(R.id.goToLiked);

        // Check if the app has permission to record audio
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO_PERMISSION_CODE);
        }

        // Check if the app has permission to modify audio settings
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.MODIFY_AUDIO_SETTINGS},
                    MODIFY_AUDIO_SETTINGS_PERMISSION_CODE);
        }

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

            btnPlaylist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(NavigatorActivity.this, UploadAlbumActivity.class));
                }
            });

            goToRecording.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(NavigatorActivity.this, RecordActivity.class));
                }
            });

            goToLiked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(NavigatorActivity.this, LikedActivity.class));
                }
            });
    }
}