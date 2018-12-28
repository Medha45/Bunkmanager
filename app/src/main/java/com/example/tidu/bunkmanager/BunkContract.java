package com.example.tidu.bunkmanager;

import android.provider.BaseColumns;

public class BunkContract {
    private BunkContract()
    {

    }
    public static final class BunkEntry implements BaseColumns{
        public static final String TABLE_NAME = "subjectList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_MIN = "minimum";
        public static final String COLUMN_PRESENTS = "presents";
        public static final String COLUMN_ABSENTS = "absents";
        public static final String COLUMN_TIMESTAMP = "timestamp";


    }
}
