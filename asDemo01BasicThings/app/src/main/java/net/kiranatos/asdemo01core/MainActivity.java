package net.kiranatos.asdemo01core;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // R is link to res folder and file activity_main.xml
    }

    /* Demonstration #01.1. Second Activity. Explicit intent.

    Create 2nd activity:
    in menu File>New>"Empty Views Activity"
    "Empty Views Activity" - Find exactly this menu option, bcs only there I can choose java code, otherwise it will create on kotlin code.

    In file AndroidManifest.xml also will be added 2nd activity Demo011SecondActivity. Check there.    */
    public void onClickButtonDemo011SecondActivity(View view) {
        Intent intent = new Intent(this, Demo011SecondActivity.class);
        startActivity(intent);
    }

    /* Demonstration #01.2. Add comment. Third Activity. Explicit intent.  */
    public void onClickButtonDemo012ThirdActivity(View view) {
        Intent intent = new Intent(this, Demo012ThirdActivity.class);
        startActivity(intent);
    }

    /* Demonstration #01.3. Send comment via messenger. Fourth Activity. Implicit intent.  */
    public void onClickButtonDemo013FourthActivity(View view) {
        Intent intent = new Intent(this, Demo013FourthActivity.class);
        startActivity(intent);
    }

    /* Demonstration #01.4. Timer.  */
    public void onClickButtonDemo014FifthActivity(View view) {
        Intent intent = new Intent(this, Demo014FifthActivity.class);
        startActivity(intent);
    }
}