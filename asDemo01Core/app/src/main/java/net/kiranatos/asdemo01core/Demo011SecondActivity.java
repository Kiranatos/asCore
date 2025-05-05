package net.kiranatos.asdemo01core;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class Demo011SecondActivity extends AppCompatActivity {

    private Spinner spinnerAnime;
    private TextView textViewAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo011_second); // activity_demo011_second.xml

        spinnerAnime = findViewById(R.id.spinnerListOfAnimeFrom2ndActivityDemo011); // R is link to res folder and activity_demo011_second.xml
        textViewAnime = findViewById(R.id.textViewAnimeDescriptionFrom2ndActivityDemo011); // TextView id in activity_demo011_second.xml
    }

    public void showDescriptionFrom2ndActivityDemo011(View view) {
        int position = spinnerAnime.getSelectedItemPosition();
        String description = getDescriptionByPosition(position);
        textViewAnime.setText(description);
    }

    private String getDescriptionByPosition(int position) {
        String[] description = getResources().getStringArray(R.array.descriptions_of_anime);
        return description[position];
    }
}