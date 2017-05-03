package com.example.echo.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kikii on 4/26/2017.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "info.db";
    private static final int DATABASE_VERSION = 4;

    public static final String TABLE_NAME = "profile";
    public static final String COLUMN_ID =  "userid";
    public static final String COLUMN_FULLNAME =  "fullname";
    public static final String COLUMN_EMAIL =  "email";
    public static final String COLUMN_PASSWORD =  "password";
    public static final String COLUMN_MOBILE =  "mobile";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FULLNAME + " TEXT, "+
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_MOBILE + " TEXT " + ");";

    //modified constructor
    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("SQLite", "onCreate: " + CREATE_TABLE_QUERY);
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion >= newVersion) return;

        Log.d("SQLite", "onUpgrade: Version = " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}