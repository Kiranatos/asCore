package net.kiranatos.asdemo01core;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Demo012FourthActivity extends AppCompatActivity {

    private TextView textViewReceivedComment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo012_fourth);

        textViewReceivedComment = findViewById(R.id.textViewFromFourthActivityDemo012);
        Intent intent = getIntent();
        String comment = intent.getStringExtra("comment");
        textViewReceivedComment.setText(comment);
    }
}