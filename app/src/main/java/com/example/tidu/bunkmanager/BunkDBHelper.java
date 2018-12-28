package com.example.tidu.bunkmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.tidu.bunkmanager.BunkContract.*;


public class BunkDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "subject.db";
    public BunkDBHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SqlString = "CREATE TABLE " +
                BunkEntry.TABLE_NAME + " (" +
                BunkEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BunkEntry.COLUMN_NAME + " TEXT NOT NULL UNIQUE, " +
                BunkEntry.COLUMN_MIN + " TEXT NOT NULL, " +
                BunkEntry.COLUMN_PRESENTS + " TEXT NOT NULL, " +
                BunkEntry.COLUMN_ABSENTS + " TEXT NOT NULL, " +
               BunkEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SqlString);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + BunkEntry.TABLE_NAME);
        onCreate(db);
    }


}
