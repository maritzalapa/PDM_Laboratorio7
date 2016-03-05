package com.example.noa.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2, et3, et4;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.editTextDni);
        et2 = (EditText) findViewById(R.id.editTextNombreApellido);
        et3 = (EditText) findViewById(R.id.editTextNombreColegio);
        et4 = (EditText) findViewById(R.id.editTextNumeroMesa);

    }

    public void alta(View v) {
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        String nombre = et2.getText().toString();
        String colegio = et3.getText().toString();
        String nromesa = et4.getText().toString();
        Cursor fila = bd.rawQuery("select * from votantes where dni="
                + dni, null);
        if(!fila.moveToFirst()) {
            ContentValues registro = new ContentValues();
            registro.put("dni", dni);
            registro.put("nombre", nombre);
            registro.put("colegio", colegio);
            registro.put("nromesa", nromesa);
            bd.insert("votantes", null, registro);
            bd.close();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
            Toast.makeText(this, "Datos actualizados",
                    Toast.LENGTH_SHORT).show();
        } else {
            bd.close();
            Toast.makeText(this, "Contacto existente",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void baja(View v) {
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        int cant = bd.delete("votantes", "dni=" + dni, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant == 1)
            Toast.makeText(this, "Borrado", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe", Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        Cursor fila = bd.rawQuery("select nombre,colegio,nromesa" +
                "from votantes where dni=" + dni, null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe persona", Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void modificacion(View v) {
        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        String nombre = et2.getText().toString();
        String colegio = et3.getText().toString();
        String nromesa = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("colegio", colegio);
        registro.put("nromesa", nromesa);
        int cant = bd.update("votantes", registro, "dni=" + dni, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "Modificación realizada",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No se encuentra",
                    Toast.LENGTH_SHORT).show();
    }

    public void inicio(View view){
        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        fila = bd.rawQuery("select * from votantes order by dni asc ", null);
        if (fila.moveToFirst()) {
            et1.setText(fila.getString(0));
            et2.setText(fila.getString(1));
            et3.setText(fila.getString(2));
            et4.setText(fila.getString(3));
        } else
            Toast.makeText(this, "No hay registrados" ,
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void anterior(View view){
        try {
            if (!fila.isFirst()) {
                fila.moveToPrevious();
                et1.setText(fila.getString(0));
                et2.setText(fila.getString(1));
                et3.setText(fila.getString(2));
                et4.setText(fila.getString(3));
            } else
                Toast.makeText(this, "Inicio de la tabla",
                        Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void siguiente(View view){
        try {
            if (!fila.isLast()) {
                fila.moveToNext();
                et1.setText(fila.getString(0));
                et2.setText(fila.getString(1));
                et3.setText(fila.getString(2));
                et4.setText(fila.getString(3));
            } else
                Toast.makeText(this, "Llegó al final",
                        Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void fin(View view){
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select * from votantes order by dni asc ", null);
        if (fila.moveToLast()) {
            et1.setText(fila.getString(0));
            et2.setText(fila.getString(1));
            et3.setText(fila.getString(2));
            et4.setText(fila.getString(3));
        } else
            Toast.makeText(this, "No hay registros" ,
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void onReset(View view){
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
    }

}































