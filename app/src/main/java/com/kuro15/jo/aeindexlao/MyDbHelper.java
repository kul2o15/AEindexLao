package com.kuro15.jo.aeindexlao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jo on 06-05-17.
 */

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "myDatabase.db";

    private static final int DB_VERSION = 2;
    public static final String TABLE_NAME = "T_Index";
    public static final String TABLE_NAME2 = "T_Bank";
    public static final String TABLE_NAME3 = "T_Log";

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) { }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
    }


}
