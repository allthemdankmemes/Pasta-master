package com.fabian.pasta;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Toast;

/**
 * Created by fabian on 28.08.16.
 */
public class ActivityPositive extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
    }

    public void appThemeNight(View view) {

        setNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        Toast.makeText(getApplicationContext(), getString(R.string.nightThemeApplied), Toast.LENGTH_SHORT).show();
    }

    public void appThemeDay(View view) {

        setNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Toast.makeText(getApplicationContext(), getString(R.string.dayThemeApplied), Toast.LENGTH_SHORT).show();
    }

    private void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);

        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        }
    }
}
