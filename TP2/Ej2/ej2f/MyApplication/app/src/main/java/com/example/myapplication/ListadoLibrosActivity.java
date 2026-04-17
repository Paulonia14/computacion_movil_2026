package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapters.LibroAdapter;
import com.example.myapplication.controladores.LibroArchivo;
import com.example.myapplication.modelos.Libro;

import java.util.ArrayList;
import java.util.List;

public class ListadoLibrosActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> nombresLibros;
    ArrayList<Integer> idLibros;

    @Override
    protected void onResume() {
        super.onResume();
        llenarVistaView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_libros);

        listView = findViewById(R.id.listLibros);

        llenarVistaView();
    }

    private void llenarVistaView(){

        List<Libro> listaLibros = LibroArchivo.leerLibros(this);

        LibroAdapter adapter = new LibroAdapter(this, listaLibros);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, pos, id) -> {

            Libro libro = listaLibros.get(pos);

            Bundle bundle = new Bundle();
            bundle.putString("origen", "lista");
            bundle.putInt("id", libro.getId());
            bundle.putString("titulo", libro.getTitulo());
            bundle.putString("subtitulo", libro.getSubtitulo());
            bundle.putString("autor", libro.getAutor());
            bundle.putString("isbn", libro.getIsbn());
            bundle.putInt("anio_publicacion", libro.getAnioPublicacion());
            bundle.putDouble("precio", libro.getPrecio());

            Intent i = new Intent(this, GestionarLibroActivity.class);
            i.putExtras(bundle);
            startActivity(i);
        });
    }
}