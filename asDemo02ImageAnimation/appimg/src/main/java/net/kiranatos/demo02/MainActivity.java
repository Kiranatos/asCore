package net.kiranatos.demo02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchingImages(View view) {
        ImageView brownImageView = findViewById(R.id.brownImageView);
        ImageView blueImageView = findViewById(R.id.blueImageView);

        brownImageView.animate()
                .alpha(blueImageView.getAlpha())
                .rotation(brownImageView.getRotation() + 3600)
                .scaleX(blueImageView.getScaleX())
                .scaleY(blueImageView.getScaleY())
                .setDuration(2000);

        blueImageView.animate()
                .alpha(brownImageView.getAlpha())
                .rotation(blueImageView.getRotation() + 3600)
                .scaleX(brownImageView.getScaleX())
                .scaleY(brownImageView.getScaleY())
                .setDuration(2000);
    }
}