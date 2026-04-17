package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.controladores.LibroBD;
import com.example.myapplication.controladores.SelectListener;
import com.example.myapplication.modelos.Libro;

import java.util.ArrayList;
import java.util.List;

public class ListadoLibrosActivity extends AppCompatActivity implements SelectListener {

    ListView listView;
    ArrayList<String> nombresLibros;
    ArrayList<Integer> idLibros;
    LibroBD libroBD;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_libros);
        init();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void init(){
        context = this.getApplicationContext();
        libroBD = new LibroBD(context, "LibrosBD.db", null, 1);
        listView = findViewById(R.id.listLibros);
        llenarVistaView();
    }

    private void llenarVistaView(){
        nombresLibros= new ArrayList<String>();
        idLibros = new ArrayList<Integer>();

        List<Libro> listaLibros = libroBD.lista();
        Log.d("DEBUG", "Cantidad libros: " + listaLibros.size());
        for(int i=0; i<listaLibros.size();i++){
            Libro l = listaLibros.get(i);
            nombresLibros.add(l.getTitulo());
            idLibros.add(l.getId());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nombresLibros
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Libro libro = libroBD.elemento(idLibros.get(pos));
                Bundle bundle = new Bundle();
                bundle.putInt("id", libro.getId());
                bundle.putString("titulo", libro.getTitulo());
                bundle.putString("subtitulo", libro.getSubtitulo());
                bundle.putString("autor", libro.getAutor());
                bundle.putString("isbn", libro.getIsbn());
                bundle.putInt("anio_publicacion", libro.getAnioPublicacion());
                bundle.putDouble("precio", libro.getPrecio());

                Intent i = new Intent(context, GestionarLibroActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }
    @Override
    public void onItemClick(String titulo) {


    }
}