package com.compa.ej_2b;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.compa.ej_2b.Entidades.conexionSQLite;
import com.compa.ej_2b.utilidades.Utilidades;

public class MainActivity extends AppCompatActivity {

    EditText id, nom, ape, doc;
    Button btnRegistrar, btnEditar, btnEliminar, btnListado;
    conexionSQLite con;
    int idSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.txtIdCliente);
        nom = findViewById(R.id.txtNombre);
        ape = findViewById(R.id.txtApellido);
        doc = findViewById(R.id.txtDocumento);
        btnRegistrar = findViewById(R.id.BtnRegistrar);
        btnEditar = findViewById(R.id.BtnEditar);
        btnEliminar = findViewById(R.id.BtnEliminar);
        btnListado = findViewById(R.id.BtnListadoClientes);

        con = new conexionSQLite(getApplicationContext(), "BD_CLIENTES", null, 1);
        idCliente();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int idRecibido = bundle.getInt("ID");
            cargarCliente(idRecibido);
        }

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarCliente();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarCliente();
            }
        });

        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, listadoClientes.class);
                startActivity(intent);
                finish();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarCliente();
            }
        });

    }

    private void cargarCliente(int idRecibido) {   // Esto para que pase correctamente el cliente cuando lo selecciono del listado
        SQLiteDatabase db = con.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_CLIENTE +
                            " WHERE " + Utilidades.CAMPO_ID + " = ?",
                    new String[]{String.valueOf(idRecibido)}
            );
            if (cursor.moveToFirst()) {
                id.setText(cursor.getString(0));
                nom.setText(cursor.getString(1));
                ape.setText(cursor.getString(2));
                doc.setText(cursor.getString(3));
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar cliente", Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
    }

    private void idCliente() {
        SQLiteDatabase db = con.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("Select max(" + Utilidades.CAMPO_ID + ") from " + Utilidades.TABLA_CLIENTE, null); // Sacar el maximo id existente
            cursor.moveToFirst();
            idSiguiente = cursor.getInt(0);

            if (idSiguiente == 0) {
                idSiguiente = 1;
                id.setText(idSiguiente + "");
            } else {
                idSiguiente = idSiguiente + 1;
                id.setText(idSiguiente + "");
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }
    }

    private void registrarCliente() {
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, id.getText().toString());
        values.put(Utilidades.CAMPO_NOM, nom.getText().toString());
        values.put(Utilidades.CAMPO_APE, ape.getText().toString());
        values.put(Utilidades.CAMPO_DOC, doc.getText().toString());

        long idresultante=db.insert(Utilidades.TABLA_CLIENTE, Utilidades.CAMPO_ID, values);
        Toast.makeText(getApplicationContext(), "Registro Exitoso con ID " + idresultante, Toast.LENGTH_LONG).show();
        db.close();
        limpiarCampos();
        idCliente();
    }

    private void editarCliente(){
        SQLiteDatabase db = con.getWritableDatabase();
        String [] parametro={id.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, id.getText().toString());
        values.put(Utilidades.CAMPO_NOM, nom.getText().toString());
        values.put(Utilidades.CAMPO_APE, ape.getText().toString());
        values.put(Utilidades.CAMPO_DOC, doc.getText().toString());

        db.update(Utilidades.TABLA_CLIENTE, values, Utilidades.CAMPO_ID + "=?",parametro);
        Toast.makeText(getApplicationContext(), "Cliente actualizado",Toast.LENGTH_LONG).show();
        db.close();
        limpiarCampos();
        idCliente();
    }

    private void eliminarCliente(){
        SQLiteDatabase db = con.getWritableDatabase();
        String [] parametro={id.getText().toString()};
        db.delete(Utilidades.TABLA_CLIENTE, Utilidades.CAMPO_ID + "=?",parametro);

        Toast.makeText(getApplicationContext(), "Cliente eliminado",Toast.LENGTH_LONG).show();
        db.close();
        limpiarCampos();
        idCliente();
    }

    private void limpiarCampos(){
        id.setText("");
        nom.setText("");
        ape.setText("");
        doc.setText("");
    }
}