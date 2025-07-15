package net.kiranatos.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView colorsListView = findViewById(R.id.colorsListView);

        final ArrayList<String> colorsArrayList = new ArrayList<>();
        colorsArrayList.add("Красный");
        colorsArrayList.add("Оранжевый");
        colorsArrayList.add("Желтый");
        colorsArrayList.add("Зеленый");
        colorsArrayList.add("Голубой");
        colorsArrayList.add("Синий");
        colorsArrayList.add("Фиолетовый");
        colorsArrayList.add("Красный");
        colorsArrayList.add("Оранжевый");
        colorsArrayList.add("Желтый");
        colorsArrayList.add("Зеленый");
        colorsArrayList.add("Голубой");
        colorsArrayList.add("Синий");
        colorsArrayList.add("Фиолетовый");
        colorsArrayList.add("Красный");
        colorsArrayList.add("Оранжевый");
        colorsArrayList.add("Желтый");
        colorsArrayList.add("Зеленый");
        colorsArrayList.add("Голубой");
        colorsArrayList.add("Синий");
        colorsArrayList.add("Фиолетовый");
        colorsArrayList.add("Красный");
        colorsArrayList.add("Оранжевый");
        colorsArrayList.add("Желтый");
        colorsArrayList.add("Зеленый");
        colorsArrayList.add("Голубой");
        colorsArrayList.add("Синий");
        colorsArrayList.add("Фиолетовый");
        colorsArrayList.add("Красный");
        colorsArrayList.add("Оранжевый");
        colorsArrayList.add("Желтый");
        colorsArrayList.add("Зеленый");
        colorsArrayList.add("Голубой");
        colorsArrayList.add("Синий");
        colorsArrayList.add("Фиолетовый");
        colorsArrayList.add("Красный");
        colorsArrayList.add("Оранжевый");
        colorsArrayList.add("Желтый");
        colorsArrayList.add("Зеленый");
        colorsArrayList.add("Голубой");
        colorsArrayList.add("Синий");
        colorsArrayList.add("Фиолетовый");
        colorsArrayList.add("Красный");
        colorsArrayList.add("Оранжевый");
        colorsArrayList.add("Желтый");
        colorsArrayList.add("Зеленый");
        colorsArrayList.add("Голубой");
        colorsArrayList.add("Синий");
        colorsArrayList.add("Фиолетовый");

        for (String str : colorsArrayList) {
            Log.i("...color: ", str);
        }

        /* ArrayAdapter - для звязку ListView та ArrayList
            android.R.layout.simple_list_item_1 - вбудований XML-шаблон розмітки.
            Слугує, щоб запакувати елементи ArrayList в <TextView> які потім передаються в ListView.
            Схожі шаблони:
            Назва	                                    Опис
            simple_list_item_1	                    Один рядок тексту (TextView)
            simple_list_item_2	                    Два рядки тексту (TextView + TextView)
            simple_list_item_checked	            Текст + чекбокс (вибір)
            simple_list_item_multiple_choice	    Список з можливістю вибору кількох
            simple_list_item_single_choice	        Список з радіо-кнопками
          */
        ArrayAdapter<String> colorsArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, colorsArrayList);

        colorsListView.setAdapter(colorsArrayAdapter);

        colorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* parent - це ListView, view - окремий елемент, */
                //parent.setVisibility(View.GONE); - зникає весь список
                //view.setVisibility(View.GONE); - зникає один пункт списку
                // Вивести текст на екран телефона:
                Toast.makeText(MainActivity.this, "Номер " + position +
                        " - " + colorsArrayList.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
