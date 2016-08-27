package com.fabian.pasta.dbhot;

import android.provider.BaseColumns;

public class TaskContractHot {
    public static final String DB_NAME2 = "com.fabian.pastahot.dbhot";
    public static final int DB_VERSION2 = 2;

    public class TaskEntryHot implements BaseColumns {
        public static final String TABLE2 = "hotties";

        public static final String COL_TASK_TITLE2 = "hot";
    }
}
