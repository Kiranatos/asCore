package net.kiranatos.demo12;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;

/* {1}: - дублюється, бо виконуються в різний час: один — при старті, другий — при зміні.

        onCreatePreferences(...) - метод викликається один раз, коли Preferences тільки створюються.
        Використовується, щоб: після завантаження PreferenceScreen - вяти поточні значення з SharedPreferences.
        І встановити початкові summary для ListPreference, EditTextPreference. Якщо не зробити це —
        підписів (summary) під налаштуваннями не буде видно одразу.
        Тут {1} ініціалізує значення (показує summary при відкритті екрана).

        В onSharedPreferenceChanged(...) - метод викликається кожного разу, коли користувач змінює налаштування.
        Використовується, щоб: відстежити зміну конкретного ключа та оновити summary у відповідного елемента
        Без цього — summary не оновиться після зміни.
        Тут {1} оновлює summary, коли користувач змінює значення. */
public class SettingsFragment extends PreferenceFragmentCompat // need to add dependency in build.gradle.kts
        implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.timer_preferences); // підключає XML-файл до фрагмента та створює екран із 3-ма налаштуваннями: чекбокс, список ListPreference і поле вводу.

        SharedPreferences sharedPreferences = getPreferenceScreen()
                .getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount(); // 3 штуки: CheckBoxPreference, ListPreference, EditTextPreference
        Log.d("[class SettingsFragment:", " count =  " + count);

        for (int i = 0; i < count; i++) {
            // {1}:
            Preference preference = preferenceScreen.getPreference(i);

            if (!(preference instanceof CheckBoxPreference)) { // список і поле вводу, але не чекбокс
                String value = sharedPreferences.getString(preference.getKey(),""); // Отримуються bell, alarm_siren, bip для ListPreference та числа для EditTextPreference
                Log.d("[class SettingsFragment:", " value =  " + value);
                setPreferenceLabel(preference, value);
            }
        }

        Preference preference = findPreference("default_interval");
        preference.setOnPreferenceChangeListener(this); // слухач зміни конкретної Preference - EditTextPreference (локально)
    }

    // встановлюється підпис значення під настройкою списка або поля вводу
    private void setPreferenceLabel(Preference preference, String value) {
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(value);
            if (index >= 0) {
                listPreference.setSummary(listPreference.getEntries()[index]);
            }
        } else if (preference instanceof EditTextPreference) {
            preference.setSummary(value);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // {1}:
        Preference preference = findPreference(key);
        if (!(preference instanceof CheckBoxPreference)) {
            String value = sharedPreferences.getString(preference.getKey(), "");
            setPreferenceLabel(preference, value);
        }
    }

    // рееєструємо Listener для списку ListPreference, для глобальних змін
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    // прибираємо Listener для списку ListPreference, для глобальних змін
    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {

        Toast toast = Toast.makeText(getContext(), "Please enter an integer number", Toast.LENGTH_LONG);

        if (preference.getKey().equals("default_interval")) {
            String defaultIntervalString =(String) o;

            try {
                int defaultInterval = Integer.parseInt(defaultIntervalString);
            } catch (NumberFormatException nef) {
                toast.show();
                return false;
            }
        }

        return true;
    }
}

/* GPT { 1) Різниця між `SharedPreferences.OnSharedPreferenceChangeListener` та `Preference.OnPreferenceChangeListener`**

| Характеристика         | `SharedPreferences.OnSharedPreferenceChangeListener`              | `Preference.OnPreferenceChangeListener`           |
| ---------------------- | ----------------------------------------------------------------- | ------------------------------------------------- |
| **Що слухає?**         | Зміни **будь-якого значення** у SharedPreferences (глобально)     | Зміни **конкретної Preference** (локально)        |
| **Коли викликається?** | Після того, як зміна вже відбулась і збережена                    | **Перед** тим, як зміна буде збережена            |
| **Що можна зробити?**  | Оновити UI, логіку, повідомити користувача                        | Відмінити або модифікувати значення до збереження |
| **Куди реєструється?** | `sharedPreferences.registerOnSharedPreferenceChangeListener(...)` | `preference.setOnPreferenceChangeListener(...)`   |

#### 🔍 У твоєму коді:

* `OnSharedPreferenceChangeListener` — слухає **всі зміни**, щоб автоматично оновлювати summary (текст під налаштуванням) після зміни.
* `OnPreferenceChangeListener` — лише на `default_interval`, щоб **перевірити**, чи це валідне число перед збереженням (використовується як валідація).

### ✅ **2) Чому в `onDestroy()` потрібно знімати `OnSharedPreferenceChangeListener`**

Це **дуже важливо** з точки зору пам’яті та безпеки:

* Коли фрагмент знищується, слухач залишається прив'язаним до `SharedPreferences`, **якщо його не зняти**.
* Це може спричинити **витік пам'яті (memory leak)**, бо слухач тримає посилання на фрагмент, який уже мав би бути знищений.
* Android не знімає таких слухачів автоматично — треба вручну.

🔧 **Підсумок:** завжди знімай `OnSharedPreferenceChangeListener` в `onDestroy()` або раніше, коли фрагмент більше не потрібен.

### ✅ **3) Чому `OnPreferenceChangeListener` не знімається вручну?**

Тут все простіше:

* `Preference.OnPreferenceChangeListener` реєструється **на один конкретний об’єкт `Preference`**.
* Коли фрагмент або екран налаштувань закривається, Android **автоматично звільняє** всі об’єкти `Preference` (оскільки це частина `PreferenceFragmentCompat`).
* **Не потрібно вручну знімати** цей слухач, бо об’єкт `Preference` сам знищується.

🔧 **Підсумок:** `setOnPreferenceChangeListener` — це як додавання обробника події до кнопки. Його знімати необов’язково, бо він живе разом із UI.

### 🔁 Що відбувається в твоєму фрагменті

| Подія                                             | Слухач                             | Що відбувається                                  |
| ------------------------------------------------- | ---------------------------------- | ------------------------------------------------ |
| Встановлення нового значення в `default_interval` | `OnPreferenceChangeListener`       | Перевіряється, чи значення — це ціле число       |
| Після зміни будь-якого іншого поля                | `OnSharedPreferenceChangeListener` | Оновлюється summary для налаштувань              |
| Завершення фрагменту                              | `onDestroy()`                      | Знімається глобальний слухач `SharedPreferences` |

### 🔚 Висновок:

1. Використання **двох слухачів логічно обґрунтоване**: один для валідації, інший для оновлення UI.
2. **Знімати потрібно лише `OnSharedPreferenceChangeListener`**, бо він прив’язаний до об’єкта поза UI.
3. **`OnPreferenceChangeListener` не потребує зняття**, бо автоматично зникає разом із Preference.
#####################################################################################################################################################################
## 🔁 1. Чому для `EditTextPreference` використовують **локальний** `OnPreferenceChangeListener`, а не глобальний `OnSharedPreferenceChangeListener`?
### 🔹 Тому що `OnPreferenceChangeListener` спрацьовує **до того**, як значення буде збережено.
Це дозволяє:
* **Перевірити або обмежити** введене значення.
* **Заборонити збереження**, якщо значення некоректне (наприклад, не число).
### 🔹 А от `OnSharedPreferenceChangeListener` спрацьовує **після** збереження — він просто повідомляє: "Щось змінилось".
## ✅ Приклад з `EditTextPreference`
java
@Override
public boolean onPreferenceChange(Preference preference, Object newValue) {
    // Перевірка, чи це число
    try {
        int val = Integer.parseInt((String) newValue);
    } catch (NumberFormatException e) {
        Toast.makeText(getContext(), "Please enter a number", Toast.LENGTH_LONG).show();
        return false; // ❌ Забороняємо збереження
    }
    return true; // ✅ Дозволяємо збереження
}
Без цього перевірки `SharedPreferences` вже б зберігло, наприклад, `"abc"`, що призвело б до помилки при використанні.
## 🧠 2. Чому для `ListPreference` і `CheckBoxPreference` **не використовують локальний OnPreferenceChangeListener**?
Бо:
* Вони зазвичай мають **обмежений набір правильних значень** (checkbox: true/false, list: з наперед заданими пунктами).
* Там **важко ввести щось неправильне**, тому **перевірки не потрібні**.
* Достатньо просто **показати summary після збереження** — і тут якраз ідеально підходить глобальний `OnSharedPreferenceChangeListener`.

## 🔁 Отже, у двох словах:

| Preference              | Що використовує                    | Навіщо                        |
| ----------------------- | ---------------------------------- | ----------------------------- |
| ✅ `CheckBoxPreference`  | `OnSharedPreferenceChangeListener` | Просто оновити UI після зміни |
| ✅ `ListPreference`      | `OnSharedPreferenceChangeListener` | Оновити label/summary         |
| 🔒 `EditTextPreference` | `OnPreferenceChangeListener`       | Валідувати перед збереженням  |
} */