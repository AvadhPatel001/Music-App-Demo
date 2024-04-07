package com.example.musicappdemo;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button play = findViewById(R.id.play);
//        Button pause = findViewById(R.id.pause);
        seekBar = findViewById(R.id.seekBar);

//        <------------------------------- Media Player Using Local Source------------------------------->

//        mediaPlayer = MediaPlayer.create(this, R.raw.music_2);
//        mediaPlayer.start();

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://socialdance.stanford.edu/music/Old_Dan_Tucker.m4a");
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        mediaPlayer.setOnPreparedListener(mp -> {
            Toast.makeText(MainActivity.this, "Ready to Play", Toast.LENGTH_SHORT).show();
            mediaPlayer.start();

            seekBar.setMax(mediaPlayer.getDuration());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    mediaPlayer.seekTo(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        });

        mediaPlayer.prepareAsync();
    }
    public void play(View view)
    {
        mediaPlayer.start();
    }

    public void pause(View view)
    {
        mediaPlayer.pause();
    }

}