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

/* I didnt check: from stackoverflow:
    When you are animating a view with a rotation (or anithing else) viewElement.animate.rotate(180),
    you will change the viewElement attribute in this case rotation!
    So if you call again viewElement.animate.rotate(180). Your element will not rotate, cause the
    rotation is already occured.
        Solution 1
            myView.animate().rotation(myView.getRotation()+180);
        Solution 2 (seems equivalent but is different)
            myView.animate().setRotation(0);
            myView.animate().rotation(180);

    GPT:
    .translationX(value)` / `.translationY(value)` / `.translationZ(value)`
    Ці методи задають **абсолютне положення** елемента на відповідній осі **відносно його початкового положення**.
        Приклад: brownImageView.animate().translationX(100f);
        Перемістить елемент на **100 пікселів праворуч** **відносно його початкової позиції** (навіть якщо це викликається вдруге — буде знову 100).

    .translationXBy(value)` / `.translationYBy(value)` / `.translationZBy(value)`
    Ці методи задають **відносне переміщення** — тобто додають до **попередньої позиції**.
        Приклад: brownImageView.animate().translationXBy(100f);
        Перемістить елемент на **100 пікселів далі**, ніж він уже є.

    Якщо викликати **двічі підряд**, то:
        .translationX(100f);` — нічого не зміниться, бо обидва рази встановлює **точно 100**
        .translationXBy(100f);` — сумарно буде +200 пікселів, бо додає

    Порівняймо ще раз коротко:
| Метод                 | Що робить                                            |
| --------------------- | ---------------------------------------------------- |
| `translationX(100)`   | Ставить **чітку позицію**: 100 пікселів по X         |
| `translationXBy(100)` | **Додає** 100 пікселів до вже встановленого X        |
| `translationY(...)`   | Ставить точну позицію по вертикалі                   |
| `translationYBy(...)` | Додає до вертикальної позиції                        |
| `translationZ(...)`   | Це глибина (ефект “над” або “під” іншими елементами) |
| `translationZBy(...)` | Змінює глибину анімації                              |

    **Зазвичай ти використовуєш `By`, коли хочеш анімувати крок за кроком.**
    **А просто `translationX()` — коли хочеш задати нову позицію напряму.**
    */