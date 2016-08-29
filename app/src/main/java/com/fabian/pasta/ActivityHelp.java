package com.fabian.pasta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

/**
 * Created by fabian on 26.08.16.
 */
public class ActivityHelp extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_help:
                Intent intent = new Intent(this, ActivityHelp.class);
                this.startActivity(intent);
                break;

            case R.id.menu_item_theme:
                Intent intent2 = new Intent(this, ActivityTheme.class);
                this.startActivity(intent2);
                break;

            case R.id.menu_item_backup:
                Intent intent3 = new Intent(this, ActivityBackup.class);
                this.startActivity(intent3);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}