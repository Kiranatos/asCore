package net.kiranatos.demo09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/*
https://developer.android.com/guide/components/activities/activity-lifecycle
ifiles/Demo09.png
onCreate > onStart > onResume - запускаються відразу при старті додатка
onPause > onStop - при звертуванні
onDestroy - при закритті та зміни орієнтації екрану

Для того, щоб дані не втрачались:

    I варіант) Треба помістити інформацію в Bundle savedInstanceState. А для цього переоприділити
метод onSaveInstanceState(Bundle outState) в якому сохранити інформацію в outState змінній. Це
працює тільки протягом однієї сесії, при закритті додатка - дані втрачаються.
https://developer.android.com/reference/android/os/Bundle

Parcelable - інтерфейс, який допомагає сберігати мої створені класи в Bundle
https://developer.android.com/reference/android/os/Parcelable#java
*/

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // в Bundle - пари ключ-значення
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString("textToBundle"));
        }
        Log.d("Lifecycle method: ", "onCreate()");
        textView.append("onCreate()" + "\n");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("Lifecycle method: ", "onStart()");
        textView.append("onStart()" + "\n");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Lifecycle method: ", "onResume()");
        textView.append("onResume()" + "\n");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("Lifecycle method: ", "onPause()");
        textView.append("onPause()" + "\n");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("Lifecycle method: ", "onStop()");
        textView.append("onStop()" + "\n");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Lifecycle method: ", "onDestroy()");
        textView.append("onDestroy()" + "\n");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d("onSaveInstanceState: ", "onSaveInstanceState()");
        textView.append("onSaveInstanceState()" + "\n");
        outState.putString("textToBundle", textView.getText().toString());
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("Lifecycle method: ", "onRestart()");
        textView.append("onRestart()" + "\n");
    }
}