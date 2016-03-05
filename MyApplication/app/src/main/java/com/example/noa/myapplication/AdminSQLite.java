package com.example.noa.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by noa on 05/03/16.
 */
public class AdminSQLite extends SQLiteOpenHelper {
    public AdminSQLite(Context context, String nombre,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table votantes(dni integer primary key,"+
                "nombre text, colegio text, nromesa integer)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte,
                          int versionNue) {
        db.execSQL("drop table if exists votantes");
        db.execSQL("create table votantes(dni integer primary key,"+
                "nombre text, colegio text, nromesa integer)");
    }
}


