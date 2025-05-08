package net.kiranatos.asdemo01core;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Demo013FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo013_fourth);
    }

    public void onClickSendCommentButtonDemo013FourthActivity(View view) {
        EditText editTextComment = findViewById(R.id.editTextFromFourthActivityDemo013);
        String comment = editTextComment.getText().toString();

        // Send to other application
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        // тут ключем використовуються встроєні в Андроїд константи Intent.EXTRA_TEXT
        intent2.putExtra(Intent.EXTRA_TEXT, comment);
        // Без цієї строки, в андроїді питає як використовувати зовнішній мессенджер: "завжди чи тільки цього разу?".
        // З цією строкою воно завжди буде питати, який мессенджер використати.
        // На відміну від відео, в мене це речення R.string.choose_title_fourth_activity_demo_013 не відобразилось а смартфоні
        Intent selectedIntent = Intent.createChooser(intent2, getString(R.string.choose_title_fourth_activity_demo_013));
        startActivity(selectedIntent);
    }
}