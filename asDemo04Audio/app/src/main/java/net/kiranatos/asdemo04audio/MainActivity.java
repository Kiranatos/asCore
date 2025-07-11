package net.kiranatos.asdemo04audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button button;
    SeekBar volumeSeekBar;
    AudioManager audioManager; // for connection between SeekBar and sound

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar); /* GPT написав, що це потрібно ставити після сетерів, але в мене навпаки - працює тільки до, а після падає з помилкою. Він зазначив, можливо це залежить від версії */
//        getSupportActionBar().setTitle("My App [MainActivity.java]");
//        toolbar.setTitleTextColor(Color.GREEN);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFEB3B"))); // yellow

        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE); // getSystemService - виклик різних служб, в даному випадку аудіо сервісу (AUDIO_SERVICE)
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // Отримуємо Max значення звука STREAM_MUSIC і встановлюємо його SeekBar-у

        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        volumeSeekBar.setMax(maxVolume);
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("Progress changed: ", "" + progress); // Log.d can watched down in IDE Logcat terminal
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        button = findViewById(R.id.playButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    pause();
                } else {
                    play();
                }
            }
        });

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff); // file from res/raw/stuff.mp3
    }

    public void play() {
        mediaPlayer.start();
        button.setText("Pause");
    }

    public void pause() {
        mediaPlayer.pause();
        button.setText("Play");
    }
}