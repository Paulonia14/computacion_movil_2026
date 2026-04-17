package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.controladores.LibroBD;
import com.example.myapplication.modelos.Libro;

public class GestionarLibroActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;
    EditText txttitulo, txtsubtitulo, txtautor, txtisbn, txtaniopublicacion, txtprecio;
    int id;
    Button btnguardar, btnactualizar, btnborrar;
    LibroBD libroBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestionar_libro);
        init();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void init(){
        context = getApplicationContext();
        txttitulo = findViewById(R.id.ges_titulo);
        txtsubtitulo = findViewById(R.id.ges_subtitulo);
        txtautor = findViewById(R.id.ges_autor);
        txtisbn = findViewById(R.id.ges_isbn);
        txtaniopublicacion = findViewById(R.id.ges_anio_publicacion);
        txtprecio = findViewById(R.id.ges_precio);
        btnactualizar= findViewById(R.id.ges_btnactualizar);
        btnborrar = findViewById(R.id.ges_btnborrar);
        btnguardar = findViewById(R.id.ges_btnguardar);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        id = bundle.getInt("id");
        if (id != 0 ){
            txttitulo.setText(bundle.getString("titulo"));
            txtsubtitulo.setText(bundle.getString("subtitulo"));
            txtautor.setText(bundle.getString("autor"));
            txtisbn.setText(bundle.getString("isbn"));
            txtaniopublicacion.setText(bundle.getInt("anio_publicacion") + "");
            txtprecio.setText(bundle.getDouble("precio") + "");
            btnguardar.setEnabled(false);
        } else {
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
        }
    }

    private void limpiarCampos(){
        id = 0;
        txttitulo.setText("");
        txtprecio.setText("");
        txtsubtitulo.setText("");
        txtaniopublicacion.setText("");
        txtisbn.setText("");
        txtautor.setText("");
    }

    private Libro llenarDatosLibro(){
        Libro libro = new Libro();
        String t = txttitulo.getText().toString();
        String st = txtsubtitulo.getText().toString();
        String a = txtautor.getText().toString();
        String i = txtisbn.getText().toString();
        String anio = txtaniopublicacion.getText().toString();
        String p= txtprecio.getText().toString();

        libro.setId(id);;
        libro.setTitulo(t);
        libro.setIsbn(i);
        libro.setPrecio(Double.parseDouble(p));
        libro.setSubtitulo(st);
        libro.setAutor(a);
        libro.setAnioPublicacion(Integer.parseInt(anio));

        return libro;

    }

    private void guardar(){
        libroBD = new LibroBD(context, "LibrosBD.db", null, 1);
        Libro libro = llenarDatosLibro();
        if (id==0){
            libroBD.agregar(libro);
            Toast.makeText(context, "Guardado nuevo ok", Toast.LENGTH_LONG).show();
            limpiarCampos();
        }else {
            libroBD.actualizar(id, libro);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(context, "Actualizado ok", Toast.LENGTH_LONG).show();

        }
    }

    private void borrar(){
        libroBD = new LibroBD(context, "LibrosBD.db", null, 1);
        Libro libro = llenarDatosLibro();
        if (id==0){
            Toast.makeText(context, "No es posible borrar (no existe)", Toast.LENGTH_LONG).show();
        }else {
            libroBD.borrar(id);
            limpiarCampos();
            btnguardar.setEnabled(true);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(context, "Se borró el registro", Toast.LENGTH_LONG).show();

        }

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ges_btnguardar) {
            guardar();

        } else if (v.getId() == R.id.ges_btnactualizar) {
            guardar();

        } else if (v.getId() == R.id.ges_btnborrar) {
            borrar();
        }

    }
}