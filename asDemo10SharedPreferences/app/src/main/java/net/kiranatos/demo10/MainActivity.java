package net.kiranatos.demo10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/* SharedPreferences
in xml file - key-value
Пари ключ/значення (примітивні дані)              | Поки програма не видалена  or clean cookie
Приклади використання: збереження очків в грі, користувальницькі налаштування типу фона, залогінення, ...

SharedPreferences sharedPreferences = this.getSharedPreferences - xml-file на уровне всего приложения
SharedPreferences sharedPreferences = this.getPreferences - xml-file на уровне данной активити
Context.MODE_PRIVATE - доступен только в данном приложении
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences(
                "myFileName", Context.MODE_PRIVATE
        ); // xml-файл створений
        SharedPreferences.Editor editor = sharedPreferences.edit(); // дає доступ до редагування файла
        editor.putString("newHelloText", "Hello Shared Preferences");
        //editor.putString("key2", "value2");
        //editor.commit(); записує в сховище миттєво, краще використовувати .apply() який не блокується UI і робе це в фоновому режимі
        // editor.remove("newHelloText");
        //editor.clear(); // як я зрозумів, це вичищає всі значення з файлу.
        editor.apply();

        TextView textView = findViewById(R.id.textView);
        textView.setText(sharedPreferences.getString("key2",
                "Default text if no values in sharedPreferences"));
    }
}

/* GPT { 1. editor.commit();
    Зберігає всі зміни, які ти зробив у SharedPreferences.Editor, синхронно (тобто метод блокує потік, поки дані реально не збережуться на диск).
    Повертає boolean, який вказує, чи збереження відбулось успішно.
    Використовують, коли треба негайно і гарантовано зберегти (наприклад, перед закриттям програми).

2. editor.remove("newHelloText");
    Видаляє збережене значення за ключем "newHelloText" з SharedPreferences.
    Зміни ще не збережені, поки ти не викличеш commit() або apply().

3. editor.clear();
    Видаляє всі ключі і значення з цього SharedPreferences файлу.
    Після цього файл стає пустим.
    Знову ж таки, без commit() або apply() дані не збережуться.

4. editor.apply();
    Зберігає всі зміни, але асинхронно (не блокує потік, результат не повертає).
    Найчастіше використовують саме apply(), бо він швидший і не блокує UI-потік. } */