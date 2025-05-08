package net.kiranatos.asdemo01core;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class Demo014FifthActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean isRunning = false;
    private TextView textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo014_fifth);

        textViewTimer = findViewById(R.id.textViewFromFifthActivityDemo014);
        runTimer();
    }

    public void onClickTimerStart(View view) {
        isRunning = true;
    }

    public void onClickTimerPause(View view) {
        isRunning = false;
    }

    public void onClickTimerReset(View view) {
        isRunning = false;
        seconds = 0;
    }

    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                textViewTimer.setText(time);

                if (isRunning) { seconds++; }

                handler.postDelayed(this, 1000);
            }
        });

    }
}