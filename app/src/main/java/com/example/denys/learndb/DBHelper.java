package com.example.denys.learndb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by denys on 19.07.17.
 */

public class DBHelper extends SQLiteOpenHelper{
    public static final int    DATABASE_VERSION = 1;
    public static final String DB_NAME = "myDB";
    public static final String T_CONTACTS = "myCont";

    public static final String KEY_ID = "_id";
    public static final String KEY_F_NAME = "fName";
    public static final String KEY_L_NAME = "lName";
    public static final String KEY_AGE = "age";

    public DBHelper(Context context, int version) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("create", "base");
        sqLiteDatabase.execSQL("create table " + T_CONTACTS + "(" + KEY_ID +
                " integer primary key," + KEY_F_NAME + " text, " + KEY_L_NAME + " text, " +
        KEY_AGE + " int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("upgrade", "base");
        sqLiteDatabase.execSQL("drop table if exists " + T_CONTACTS);
        onCreate(sqLiteDatabase);
    }
}
