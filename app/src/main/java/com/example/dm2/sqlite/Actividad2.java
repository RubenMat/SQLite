package com.example.dm2.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Actividad2 extends AppCompatActivity {

    TextView txtCodigo,txtTitulo,txtAutor,txtGenero,txtApoteosico;
    Button btnInsert, btnDel, btnUpd, btnListar;
    LibrosSQLiteHelper librosDB;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        librosDB = new LibrosSQLiteHelper(this,"librosDB",null,1);

        db = librosDB.getWritableDatabase();

        txtCodigo=(TextView)findViewById(R.id.txtCodigo);
        txtTitulo=(TextView)findViewById(R.id.txtTitulo);
        txtAutor=(TextView)findViewById(R.id.txtAutor);
        txtGenero=(TextView)findViewById(R.id.txtGenero);
        txtApoteosico=(TextView)findViewById(R.id.txtApoteosico);

        btnInsert=(Button)findViewById(R.id.btnInsertar);
        btnDel=(Button)findViewById(R.id.btnBorrar);
        btnUpd=(Button)findViewById(R.id.btnModificar);
        btnListar=(Button)findViewById(R.id.btnListar);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] datos = {txtCodigo.getText().toString(),txtTitulo.getText().toString(),txtAutor.getText().toString(),txtGenero.getText().toString()};
                db.execSQL("Insert into Libros (idLibro,titulo,autor,genero) VALUES (?,?,?,?)",datos);

            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtApoteosico.setText("");
                Cursor c =db.rawQuery("SELECT * FROM Libros ", null);
                while(c.moveToNext()){
                    txtApoteosico.append("\n" + "ID: " + c.getInt(0) + " Titulo: " + c.getString(1) + " Autor: " + c.getString(2) + " Genero: " + c.getString(3));
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] datos= {txtCodigo.getText().toString()};
                db.execSQL("Delete FROM Libros WHERE idLibro=?",datos);
            }
        });

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] datos= {txtTitulo.getText().toString(),txtAutor.getText().toString(),txtGenero.getText().toString(),txtCodigo.getText().toString()};
                db.execSQL("UPDATE Libros set titulo=?, autor=?, genero=? WHERE idLibro=?",datos);
            }
        });

    }
}
