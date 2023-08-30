package com.example.musicappserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.example.musicappserver.Model.GetSongs;

import java.util.ArrayList;

public class OneSongActivity extends AppCompatActivity {

    private ImageView imageSong;
    static JcPlayerView jcPlayerView;
    ArrayList<JcAudio> jcAudios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_song);
        imageSong = findViewById(R.id.imageSong);
        jcPlayerView = findViewById(R.id.jcplayer1);
        GetSongs songs = (GetSongs) getIntent().getParcelableExtra("song");
        jcAudios.add(JcAudio.createFromURL(songs.getSongTitle(),songs.getSongLink()));
        jcPlayerView.initPlaylist(jcAudios,null);
        jcPlayerView.playAudio(jcAudios.get(0));
        jcPlayerView.createNotification();

    }
}