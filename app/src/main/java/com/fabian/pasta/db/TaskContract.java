package com.fabian.pasta.db;

import android.provider.BaseColumns;

public class TaskContract {
    public static final String DB_NAME = "com.fabian.pastafavorites.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";
        public static final String TABLE2 = "hot";
        public static final String TABLE3 = "unicodes";
        public static final String TABLE4 = "positive";
        public static final String TABLE5 = "neutral";
        public static final String TABLE6 = "negative";
        public static final String TABLE7 = "upvote";
        public static final String TABLE8 = "downvote";
        public static final String TABLE9 = "stop";
        public static final String TABLE10 = "identification";
        public static final String TABLE11 = "congratulations";
        public static final String TABLE12 = "shitposts";
        public static final String TABLE13 = "trolls";
        public static final String TABLE14 = "love";
        public static final String TABLE15 = "animals";
        public static final String TABLE16 = "stories";
        public static final String TABLE17 = "badass";
        public static final String TABLE18 = "nerdy";

        public static final String COL_TASK_TITLE = "title";
        public static final String COL_TASK_TITLE2 = "hot";
    }
}
