package net.kiranatos.demo12;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/*
В SettingsFragment.java не знаходило класс PreferenceFragmentCompat, довелось додати в build.gradle.kts
в dependencies: implementation("androidx.preference:preference:1.2.1"), а в SettingsFragment.java -
import androidx.preference.PreferenceFragmentCompat;

Не відображався заголовок як у відео, тому не відображались запрограмовані кнопки.
Використав 4th variant описаний в asDemo06MusicPlayer/app/src/main/res/values/themes.xml

 timer_menu.xml - just structure of menu
 MainActivity.java
 SettingsActivity.java
 AboutActivity.java
 SettingsFragment.java
*/
public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SeekBar seekBar;
    private TextView textView;
    private boolean isTimerOn;
    private Button button;
    private CountDownTimer countDownTimer;
    private int defaultInterval;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        seekBar.setMax(600);
        isTimerOn = false;
        setIntervalFromSharedPreferences(sharedPreferences);

        button = findViewById(R.id.button);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                long progressInMillis = progress * 1000;
                updateTimer(progressInMillis);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {           }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {            }
        });

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void start(View view) {
        if (!isTimerOn) {
            button.setText("Stop");
            seekBar.setEnabled(false); // не дає змінювати тумблер таймера
            isTimerOn = true;

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer(millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    /* отримує дані настроєк з SettingsFragment.java > timer_preferences.xml і реалізує логіку їх роботи: звук, мелодія, діапазон */
                    SharedPreferences sharedPreferences =
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    if (sharedPreferences.getBoolean("enable_sound", true)) { // key from timer_preferences.xml

                        String melodyName = sharedPreferences.getString("timer_melody", "bell"); // key from timer_preferences.xml + arrays.xml
                        if (melodyName.equals("bell")) {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),
                                    R.raw.bell_sound); // file in raw folder
                            mediaPlayer.start();
                        } else if (melodyName.equals("alarm_siren")) {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),
                                    R.raw.alarm_siren_sound); // file in raw folder
                            mediaPlayer.start();
                        } else if (melodyName.equals("bip")) {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),
                                    R.raw.bip_sound); // file in raw folder
                            mediaPlayer.start();
                        }
                    }
                    resetTimer();
                }
            };
            countDownTimer.start();
        } else {
            resetTimer();
        }

    }

    private void updateTimer(long millisUntilFinished) {
        int minutes = (int) millisUntilFinished/1000/60;
        int seconds = (int) millisUntilFinished/1000 - (minutes * 60);

        String minutesString = "";
        String secondsString = "";

        if (minutes < 10) {
            minutesString = "0" + minutes;
        } else {
            minutesString = String.valueOf(minutes);
        }

        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = String.valueOf(seconds);
        }

        textView.setText(minutesString + ":" + secondsString);
    }

    private void resetTimer() {
        countDownTimer.cancel();
        //textView.setText("00:30");
        button.setText("Start");
        seekBar.setEnabled(true);
        //seekBar.setProgress(30);
        isTimerOn = false;
        setIntervalFromSharedPreferences(sharedPreferences);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.timer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { /* при натисканні на пунктах(піктограми і текстові) меню заголовка.
    Створюються активності: SettingsActivity, AboutActivity.
    Активність для текстового меню Purchase не реалізована.
    Звернуть увагу також на написане в AndroidManifest.xml, воно має вплив тут */
        int id = item.getItemId();
        if (id == R.id.action_settings) { // id in timer_menu.xml, піктограмма коліщатка
            Intent openSettings =
                    new Intent(this, SettingsActivity.class);
            startActivity(openSettings);
            return true;
        } else if (id == R.id.action_about) { // id in timer_menu.xml, піктограмма діалога
            Intent openAbout =
                    new Intent(this, AboutActivity.class);
            startActivity(openAbout);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setIntervalFromSharedPreferences(SharedPreferences sharedPreferences) {

        defaultInterval = Integer.valueOf(sharedPreferences.getString("default_interval", "30"));
        long defaultIntervalInMillis = defaultInterval * 1000;
        updateTimer(defaultIntervalInMillis);
        seekBar.setProgress(defaultInterval);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("default_interval")) {
            setIntervalFromSharedPreferences(sharedPreferences);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}

/* GPT { Починаючи з **Android 11 (API 30)** і новіших бібліотек AndroidX, метод
                PreferenceManager.getDefaultSharedPreferences(context)
    позначили як deprecated, тому що Google хоче, щоб розробники явно створювали свій SharedPreferences
    файл, а не користувались дефолтним (який неочевидно має спільне ім’я між усіма Preference-екранами).

        Що використовувати замість?
    Тепер рекомендується самостійно створювати/називати файл для SharedPreferences через метод
context.getSharedPreferences():
                seekBar = findViewById(R.id.seekBar);
                textView = findViewById(R.id.textView);
                // Замість PreferenceManager.getDefaultSharedPreferences()
                sharedPreferences = this.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
    * "my_prefs" — будь-яке ім’я твого файлу (можна назвати як хочеш).
    * MODE_PRIVATE — стандартний режим (тільки твоя апка має доступ).

        Чому це краще?
1. Більше контролю над назвами і логікою збереження.
2. Немає ризику конфліктів між Preference-екранами.
3. Рекомендується Google як більш прозорий і безпечний спосіб. } */