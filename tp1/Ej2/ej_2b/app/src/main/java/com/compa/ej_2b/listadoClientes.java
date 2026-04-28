package com.compa.ej_2b;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.compa.ej_2b.Adaptador.adapterClientes;
import com.compa.ej_2b.Entidades.clientes;
import com.compa.ej_2b.Entidades.conexionSQLite;
import com.compa.ej_2b.utilidades.Utilidades;

import java.util.ArrayList;

public class listadoClientes extends AppCompatActivity implements adapterClientes.RecyclerItemClick {

    RecyclerView recyclerLista;
    conexionSQLite con;
    ArrayList<clientes> listClientes;
    adapterClientes adapter;

    Button buscar, btnHome;
    EditText txtbuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_clientes);

        recyclerLista = findViewById(R.id.ListadeCliente);
        buscar = findViewById(R.id.btnBuscar);
        btnHome = findViewById(R.id.btnHome);
        txtbuscar = findViewById(R.id.txtdocBuscar);

        con = new conexionSQLite(getApplicationContext(), "BD_CLIENTES", null, 1);

        recyclerLista.setLayoutManager(new LinearLayoutManager(this));

        listarClientes("");

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarClientes(txtbuscar.getText().toString());
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listadoClientes.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void listarClientes(String documento) {
        SQLiteDatabase db = con.getReadableDatabase();
        clientes clientes;
        listClientes = new ArrayList<>();

        Cursor cursor = db.rawQuery("Select * from " + Utilidades.TABLA_CLIENTE+" where "+Utilidades.CAMPO_DOC+" like '%"+documento+"%'", null);
        while (cursor.moveToNext()) {
            clientes = new clientes();
            clientes.setIdCliente(cursor.getInt(0));
            clientes.setNombre(cursor.getString(1));
            clientes.setApellido(cursor.getString(2));
            clientes.setDocumento(cursor.getString(3));
            listClientes.add(clientes);
        }

        adapter = new adapterClientes(listClientes, this);
        recyclerLista.setAdapter(adapter);
        db.close();
    }

    @Override
    public void itemClick(clientes clientes) {
        Toast.makeText(getApplicationContext(), clientes.getNombre(), Toast.LENGTH_LONG).show();

        Intent miintent = new Intent(listadoClientes.this, MainActivity.class);
        Bundle mibundle = new Bundle();

        mibundle.putInt("ID", clientes.getIdCliente());
        miintent.putExtras(mibundle);
        startActivity(miintent);
    }
}
