package com.example.appbarberfast;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AppDataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "barber_fast.db";
    private static final String TBL_NAME = "tblUsuario";
    private static final int DB_VERSION = 1;

    public AppDataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TBL_NAME
                + "(intIdUsuario integer primary key autoincrement,"
                + "vchNome text,"
                + "vchUsuario text,"
                + "vchEmail text,"
                + "vchSenha text,"
                + "vchConfSenha text"
                + " )";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
