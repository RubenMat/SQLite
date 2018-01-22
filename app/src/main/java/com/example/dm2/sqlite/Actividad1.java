package com.example.dm2.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Actividad1 extends AppCompatActivity {

    TextView txtContacto,txtContacto2,txtContacto3,txtContacto4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad1);

        ContactosSQLiteHelper contactosDB = new ContactosSQLiteHelper(this,"contactosDB",null,8);

        SQLiteDatabase db = contactosDB.getWritableDatabase();

        if(db!=null){
            db.execSQL("INSERT INTO Contactos(idContacto,nombre,numero)VALUES(1,'Ruben',666098789)");
            db.execSQL("INSERT INTO Contactos(idContacto,nombre,numero)VALUES(2,'Daven',777098789)");
            db.execSQL("INSERT INTO Contactos(idContacto,nombre,numero)VALUES(3,'Slama',888098789)");
            db.execSQL("INSERT INTO Contactos(idContacto,nombre,numero)VALUES(4,'LordCasian',999098789)");
        }


        txtContacto =(TextView)findViewById(R.id.txtContacto1);
        txtContacto2 =(TextView)findViewById(R.id.txtContacto2);
        txtContacto3 =(TextView)findViewById(R.id.txtContacto3);
        txtContacto4 =(TextView)findViewById(R.id.txtContacto4);

        TextView[] contactos = {txtContacto,txtContacto2,txtContacto3,txtContacto4};


        Cursor c =db.rawQuery("SELECT nombre,numero FROM Contactos ", null);

        int cont=0;

        while(c.moveToNext()){
            contactos[cont].setText("Nombre: " + c.getString(0) + "\n Número: " + c.getInt(1));
            cont++;
        }

        db.close();

    }
}
