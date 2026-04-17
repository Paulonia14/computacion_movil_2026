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

public class BuscarLibroActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText txttitulo;
    Button btnbuscar;
    LibroBD libroBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar_libro);
        init();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void init(){
        context = getApplicationContext();
        txttitulo = findViewById(R.id.bus_txttitulo);
        btnbuscar = findViewById(R.id.bus_btnbuscar);
    }

    @Override
    public void onClick(View view){
        if(view.getId()== R.id.bus_btnbuscar){
            String titulo = txttitulo.getText().toString();
            Libro libro = buscarLibro( titulo);
            if (libro!= null){
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
            }else {
                Toast.makeText(context, "No existe el libro solicitado", Toast.LENGTH_LONG).show();
            }
        }
    }

    private Libro buscarLibro(String titulo) {
        libroBD = new LibroBD(context, "LibrosBD.db", null, 1);
        Libro libro = libroBD.elemento(titulo);
        return libro;
    }


}