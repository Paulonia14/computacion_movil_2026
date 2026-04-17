package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.controladores.LibroArchivo;
import com.example.myapplication.modelos.Libro;

import java.util.List;

public class GestionarLibroActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText txttitulo, txtsubtitulo, txtautor, txtisbn, txtaniopublicacion, txtprecio;
    int id;
    Button btnguardar, btnactualizar, btnborrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_libro);
        init();
    }

    private void init(){
        context = this;

        txttitulo = findViewById(R.id.ges_titulo);
        txtsubtitulo = findViewById(R.id.ges_subtitulo);
        txtautor = findViewById(R.id.ges_autor);
        txtisbn = findViewById(R.id.ges_isbn);
        txtaniopublicacion = findViewById(R.id.ges_anio_publicacion);
        txtprecio = findViewById(R.id.ges_precio);

        btnguardar = findViewById(R.id.ges_btnguardar);
        btnactualizar = findViewById(R.id.ges_btnactualizar);
        btnborrar = findViewById(R.id.ges_btnborrar);

        btnguardar.setOnClickListener(this);
        btnactualizar.setOnClickListener(this);
        btnborrar.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            id = bundle.getInt("id", 0);

            if (id != 0){
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
    }

    private void limpiarCampos(){
        id = 0;
        txttitulo.setText("");
        txtsubtitulo.setText("");
        txtautor.setText("");
        txtisbn.setText("");
        txtaniopublicacion.setText("");
        txtprecio.setText("");
    }

    private Libro llenarDatosLibro(){
        Libro libro = new Libro();

        libro.setId(id);
        libro.setTitulo(txttitulo.getText().toString());
        libro.setSubtitulo(txtsubtitulo.getText().toString());
        libro.setAutor(txtautor.getText().toString());
        libro.setIsbn(txtisbn.getText().toString());
        libro.setAnioPublicacion(Integer.parseInt(txtaniopublicacion.getText().toString()));
        libro.setPrecio(Double.parseDouble(txtprecio.getText().toString()));

        return libro;
    }

    private void guardar(){
        Libro libro = llenarDatosLibro();

        if (id == 0){
            List<Libro> lista = LibroArchivo.leerLibros(this);
            libro.setId(lista.size() + 1);

            LibroArchivo.guardarLibro(this, libro);

            Toast.makeText(context, "Guardado nuevo ok", Toast.LENGTH_LONG).show();
            limpiarCampos();

        } else {
            List<Libro> lista = LibroArchivo.leerLibros(this);

            for (int i = 0; i < lista.size(); i++){
                if (lista.get(i).getId() == id){
                    lista.set(i, libro);
                    break;
                }
            }

            LibroArchivo.sobrescribirLibros(this, lista);

            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);

            Toast.makeText(context, "Actualizado ok", Toast.LENGTH_LONG).show();
        }
    }

    private void borrar(){
        if (id == 0){
            Toast.makeText(context, "No se puede borrar", Toast.LENGTH_LONG).show();
            return;
        }

        List<Libro> lista = LibroArchivo.leerLibros(this);

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == id){
                lista.remove(i);
                break;
            }
        }

        LibroArchivo.sobrescribirLibros(this, lista);

        limpiarCampos();
        btnguardar.setEnabled(true);
        btnactualizar.setEnabled(false);
        btnborrar.setEnabled(false);

        Toast.makeText(context, "Libro eliminado", Toast.LENGTH_LONG).show();
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