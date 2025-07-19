package net.kiranatos.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* I спосіб:
        final Handler handler = new Handler();

        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                Log.d("Runnable","Two seconds are passed");
                handler.postDelayed(this, 2000);
            }
        };

        handler.post(runnable); */

        // II спосіб:
        CountDownTimer timer = new CountDownTimer(10000,1000) { // (проміжок часу 10 сек., інтервал 1 сек.)
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("My Timer:", String.valueOf(millisUntilFinished/1000) + " second left.");
            }

            @Override
            public void onFinish() {
                Log.d("My Timer:", " Finish! ");
            }
        };

        timer.start();
    }
}