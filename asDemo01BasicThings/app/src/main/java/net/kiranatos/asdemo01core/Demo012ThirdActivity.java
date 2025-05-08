package net.kiranatos.asdemo01core;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Demo012ThirdActivity extends AppCompatActivity {

    private EditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo012_third);

        editTextComment = findViewById(R.id.editTextFromThirdActivityDemo012);
    }

    public void onClickAddCommentButtonDemo012ThirdActivity(View view) {
        String comment = editTextComment.getText().toString();

        // Send to 4nd activity:
        Intent intent = new Intent(this, Demo012FourthActivity.class);
        intent.putExtra("comment", comment);
        startActivity(intent);
    }
}