package com.fabian.pasta;

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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fabian.pasta.db.TaskContract;
import com.fabian.pasta.db.TaskDbHelper;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TaskDbHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;
    private static final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new TaskDbHelper(this);
        mTaskListView = (ListView) findViewById(R.id.list_todo);

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

            default:
                return super.onOptionsItemSelected(item);
        }
                return true;
    }

    public void navhot(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(this, ActivityPositive.class);
        this.startActivity(intent2);
        return;
    }

    public void navfavorites(MenuItem item){
    }

    public void navunicodes(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navpositive(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navneutral(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navnegative(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navupvote(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navdownvote(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navstop(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navidentification(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navcongratulations(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navshitposts(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navtrolls(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navlove(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navgames(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navanimals(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navstories(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navbadass(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

    public void navnerdy(MenuItem item){
        Toast.makeText(getApplicationContext(), getString(R.string.notworking), Toast.LENGTH_SHORT).show();
    }

}
