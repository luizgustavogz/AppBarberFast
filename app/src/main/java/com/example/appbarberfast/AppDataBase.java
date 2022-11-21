package com.example.appbarberfast;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AppDataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "BarberFast.sqlite";
    private static final int DB_VERSION = 1;

    public AppDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tblUsuario \n" +
                "(intIdUsuario INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "vchNome TEXT,\n" +
                "vchUsuario TEXT,\n" +
                "vchEmail TEXT,\n" +
                "vchSenha TEXT,\n" +
                "vchConfSenha TEXT\n" +
                " )";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
