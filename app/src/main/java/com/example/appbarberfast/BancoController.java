package com.example.appbarberfast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoController extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "barberfast.db";
    private SQLiteDatabase db;

    public BancoController(Context context) {
        super(context, "barberfast.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, usuario TEXT, email TEXT, senha TEXT, confSenha TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists usuarios");
    }

    public Boolean insereDadoCadastro(String nome, String usuario, String email, String senha, String confSenha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nome", nome);
        valores.put("usuario", usuario);
        valores.put("email", email);
        valores.put("senha", senha);
        valores.put("confSenha", confSenha);

        long resultado = db.insert("usuarios", null, valores);

        if (resultado == -1)
            return false;
        else
            return true;
    }

    public Boolean verificarUsuario(String usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuarios where usuario=?", new String[]{usuario});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean verificarUsuarioSenha(String usuario, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuarios where usuario=? and senha=?", new String[]{usuario, senha});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
