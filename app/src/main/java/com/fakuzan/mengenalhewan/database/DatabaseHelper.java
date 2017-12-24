package com.fakuzan.mengenalhewan.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.fakuzan.mengenalhewan.database.DatabaseContract.HewanColumns.DESKRIPSI;
import static com.fakuzan.mengenalhewan.database.DatabaseContract.HewanColumns.GAMBAR;
import static com.fakuzan.mengenalhewan.database.DatabaseContract.HewanColumns.JENIS;
import static com.fakuzan.mengenalhewan.database.DatabaseContract.HewanColumns.NAMA;
import static com.fakuzan.mengenalhewan.database.DatabaseContract.HewanColumns.SUARA;
import static com.fakuzan.mengenalhewan.database.DatabaseContract.TABLE_NAME;

/**
 * Created by intel on 19/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbhewan";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_HEWAN = "create table " + TABLE_NAME +
            " (" + NAMA + " text primary key, " +
            SUARA + " text not null, " +
            DESKRIPSI + " text not null, " +
            GAMBAR + " text not null, " +
            JENIS + " text not null);";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_HEWAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
