package com.fabian.pasta.dbhot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fabian.pasta.db.TaskContract;

public class TaskDbHelperHot extends SQLiteOpenHelper {

    public TaskDbHelperHot(Context context) {
        super(context, TaskContractHot.DB_NAME2, null, TaskContractHot.DB_VERSION2);
    }

    @Override
    public void onCreate(SQLiteDatabase dbhot) {
        String createTable = "CREATE TABLE " + TaskContract.TaskEntry.TABLE + " ( " +
                TaskContractHot.TaskEntryHot._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskContractHot.TaskEntryHot.COL_TASK_TITLE2 + " TEXT NOT NULL);";

        dbhot.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbhot, int oldVersion, int newVersion) {
        dbhot.execSQL("DROP TABLE IF EXISTS " + TaskContractHot.TaskEntryHot.TABLE2);
        onCreate(dbhot);
    }
}
