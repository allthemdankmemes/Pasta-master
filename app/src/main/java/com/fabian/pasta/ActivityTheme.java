package com.fabian.pasta;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fabian.pasta.db.TaskContract;

/**
 * Created by fabian on 26.08.16.
 */
public class ActivityTheme extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
    }

    public void appThemeGreen(View view) {
        SharedPreferences theme = getSharedPreferences("themePrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = theme.edit();
        editor.putInt("LightGreen", 2);
        editor.commit();
        Toast.makeText(getApplicationContext(), getString(R.string.lightGreenApplied), Toast.LENGTH_SHORT).show();
    }
}
