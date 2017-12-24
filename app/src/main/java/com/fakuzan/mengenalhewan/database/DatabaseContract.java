package com.fakuzan.mengenalhewan.database;

import android.provider.BaseColumns;

/**
 * Created by intel on 19/12/2017.
 */

public class DatabaseContract {

    static String TABLE_NAME = "hewan";

    static final class HewanColumns implements BaseColumns {

        static String NAMA = "nama";
        static String SUARA = "suara";
        static String DESKRIPSI = "deskripsi";
        static String GAMBAR = "gambar";
        static String JENIS = "jenis";

    }

}
