package com.fakuzan.mengenalhewan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import com.fakuzan.mengenalhewan.model.HewanDb;

import java.util.ArrayList;

import static com.fakuzan.mengenalhewan.database.DatabaseContract.HewanColumns.DESKRIPSI;
import static com.fakuzan.mengenalhewan.database.DatabaseContract.HewanColumns.GAMBAR;
import static com.fakuzan.mengenalhewan.database.DatabaseContract.HewanColumns.JENIS;
import static com.fakuzan.mengenalhewan.database.DatabaseContract.HewanColumns.NAMA;
import static com.fakuzan.mengenalhewan.database.DatabaseContract.HewanColumns.SUARA;
import static com.fakuzan.mengenalhewan.database.DatabaseContract.TABLE_NAME;

/**
 * Created by intel on 19/12/2017.
 */

public class HewanHelper {

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public HewanHelper(Context context) {
        this.context = context;
    }

    public HewanHelper open() throws SQLiteException {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<HewanDb> getAllData(){
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,NAMA+ " ASC",null);
        cursor.moveToFirst();
        ArrayList<HewanDb> arrayList = new ArrayList<>();
        HewanDb hewanDb;
        if (cursor.getCount()>0) {
            do {
                String nama = cursor.getString(cursor.getColumnIndexOrThrow(NAMA));
                String suara = cursor.getString(cursor.getColumnIndexOrThrow(SUARA));
                String deskripsi = cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI));
                String gambar = cursor.getString(cursor.getColumnIndexOrThrow(GAMBAR));
                String jenis = cursor.getString(cursor.getColumnIndexOrThrow(JENIS));
                hewanDb = new HewanDb(nama, suara, deskripsi, gambar, jenis);

                arrayList.add(hewanDb);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<HewanDb> getAllDataByJenis(String jenisKelas){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + JENIS + " = " + "'" + jenisKelas + "'", null);
        cursor.moveToFirst();
        ArrayList<HewanDb> arrayList = new ArrayList<>();
        HewanDb hewanDb;
        if (cursor.getCount()>0) {
            do {
                String nama = cursor.getString(cursor.getColumnIndexOrThrow(NAMA));
                String suara = cursor.getString(cursor.getColumnIndexOrThrow(SUARA));
                String deskripsi = cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI));
                String gambar = cursor.getString(cursor.getColumnIndexOrThrow(GAMBAR));
                String jenis = cursor.getString(cursor.getColumnIndexOrThrow(JENIS));
                hewanDb = new HewanDb(nama, suara, deskripsi, gambar, jenis);

                arrayList.add(hewanDb);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<HewanDb> getDataByNama(String namaHewan){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAMA + " = " + "'" + namaHewan + "'", null);
        cursor.moveToFirst();
        ArrayList<HewanDb> arrayList = new ArrayList<>();
        HewanDb hewanDb;
        if (cursor.getCount()>0) {
            do {
                String nama = cursor.getString(cursor.getColumnIndexOrThrow(NAMA));
                String suara = cursor.getString(cursor.getColumnIndexOrThrow(SUARA));
                String deskripsi = cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI));
                String gambar = cursor.getString(cursor.getColumnIndexOrThrow(GAMBAR));
                String jenis = cursor.getString(cursor.getColumnIndexOrThrow(JENIS));
                hewanDb = new HewanDb(nama, suara, deskripsi, gambar, jenis);

                arrayList.add(hewanDb);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(HewanDb hewanDb){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(NAMA, hewanDb.getNama());
        initialValues.put(SUARA, hewanDb.getDeskripsi());
        initialValues.put(DESKRIPSI, hewanDb.getDeskripsi());
        initialValues.put(GAMBAR, hewanDb.getGambar());
        initialValues.put(JENIS, hewanDb.getJenis());
        return sqLiteDatabase.insert(TABLE_NAME, null, initialValues);
    }

    public void beginTransaction(){
        sqLiteDatabase.beginTransaction();
    }

    public void setTransactionSuccess(){
        sqLiteDatabase.setTransactionSuccessful();
    }

    public void endTransaction(){
        sqLiteDatabase.endTransaction();
    }

    public void insertTransaction(HewanDb hewanDb){
        String sql = "INSERT INTO "+ TABLE_NAME+" ("+
                NAMA+", "+
                SUARA+", "+
                DESKRIPSI+", "+
                GAMBAR+", "+
                JENIS+", "+
                ") VALUES (?, ?, ?, ?, ?)";
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(sql);
        stmt.bindString(1, hewanDb.getNama());
        stmt.bindString(2, hewanDb.getSuara());
        stmt.bindString(3, hewanDb.getDeskripsi());
        stmt.bindString(4, hewanDb.getGambar());
        stmt.bindString(5, hewanDb.getJenis());
        stmt.execute();
        stmt.clearBindings();

    }

}
