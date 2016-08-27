package com.fabian.pasta;

import android.app.FragmentManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.NotificationCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fabian.pasta.db.TaskContract;
import com.fabian.pasta.db.TaskDbHelper;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TaskDbHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;
    private static final Random random = new Random();
    private int mThemeId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new TaskDbHelper(this);
        mTaskListView = (ListView) findViewById(R.id.list_todo);

        final ClipboardManager clipBoard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        updateUI();
    }

    public void addItems(View v){
        if(v.getId() == R.id.addBtn){
            final EditText taskEditText = new EditText(this);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.addpasta))
                    .setView(taskEditText)
                    .setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String task = String.valueOf(taskEditText.getText());
                            SQLiteDatabase db = mHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put(TaskContract.TaskEntry.COL_TASK_TITLE, task);
                            db.insertWithOnConflict(TaskContract.TaskEntry.TABLE,
                                    null,
                                    values,
                                    SQLiteDatabase.CONFLICT_REPLACE);
                            db.close();
                            updateUI();
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), null)
                    .create();
            dialog.show();
        }

    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(TaskContract.TaskEntry.TABLE,
                TaskContract.TaskEntry.COL_TASK_TITLE + " = ?",
                new String[]{task});
        db.close();
        updateUI();
    }

    public void copyTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label",task);
        clipboard.setPrimaryClip(clip);

        String[] toastMessages = new String[] {getString(R.string.copy0), getString(R.string.copy1), getString(R.string.copy2),getString(R.string.copy3),getString(R.string.copy4),getString(R.string.copy5),getString(R.string.copy6),getString(R.string.copy7),getString(R.string.copy8),getString(R.string.copy9),getString(R.string.copy10),getString(R.string.copy11),getString(R.string.copy12),getString(R.string.copy13),getString(R.string.copy14),getString(R.string.copy15),getString(R.string.copy16),getString(R.string.copy17),getString(R.string.copy18),getString(R.string.copy19),getString(R.string.copy20),getString(R.string.copy21),getString(R.string.copy22),getString(R.string.copy23),getString(R.string.copy24),getString(R.string.copy25),getString(R.string.copy26),getString(R.string.copy27),getString(R.string.copy28),getString(R.string.copy29)};

        //random toast
        int randomMsgIndex = random.nextInt(toastMessages.length - 1);
        Toast.makeText(getApplicationContext(), toastMessages[randomMsgIndex], Toast.LENGTH_SHORT).show();

        //specific toast
        //Toast.makeText(getApplicationContext(), "Yummy!", Toast.LENGTH_SHORT).show();

    }

    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE,
                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE);
            taskList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.item_todo,
                    R.id.task_title,
                    taskList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
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

            case R.id.menu_night_mode_system:
                setTheme(R.style.AppTheme_Green);
                setContentView(R.layout.activity_theme);
                break;
            case R.id.menu_night_mode_day:
                setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case R.id.menu_night_mode_night:
                setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case R.id.menu_night_mode_auto:
                setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
                return true;
    }

    public void navhot(MenuItem item){
        Intent intent = new Intent(this, ActivityHot.class);
        this.startActivity(intent);
    }

    public void navfavorites(MenuItem item){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    private void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);

        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        }
    }

}
