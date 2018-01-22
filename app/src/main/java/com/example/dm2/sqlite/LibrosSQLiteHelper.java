package com.example.dm2.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dm2 on 12/01/2018.
 */

public class LibrosSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate =
            "CREATE TABLE Libros (idLibro INTEGER PRIMARY KEY, titulo TEXT, autor TEXT, genero TEXT)";
    public LibrosSQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Libros");
        db.execSQL(sqlCreate);
    }
}