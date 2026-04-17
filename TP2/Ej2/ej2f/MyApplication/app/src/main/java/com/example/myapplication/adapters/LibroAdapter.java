package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.controladores.LibroArchivo;
import com.example.myapplication.modelos.Libro;

import java.util.List;

public class LibroAdapter extends ArrayAdapter<Libro> {

    public LibroAdapter(Context context, List<Libro> lista) {
        super(context, 0, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_libro, parent, false);
        }

        Libro libro = getItem(position);

        TextView titulo = convertView.findViewById(R.id.item_titulo);
        TextView autor = convertView.findViewById(R.id.item_autor);
        TextView precio = convertView.findViewById(R.id.item_precio);
        Button btnEliminar = convertView.findViewById(R.id.btn_eliminar);

        titulo.setText(libro.getTitulo());
        autor.setText("Autor: " + libro.getAutor());
        precio.setText("Precio: $" + libro.getPrecio());

        btnEliminar.setOnClickListener(v -> {

            List<Libro> lista = LibroArchivo.leerLibros(getContext());

            for (int i = 0; i < lista.size(); i++){
                if (lista.get(i).getId() == libro.getId()){
                    lista.remove(i);
                    break;
                }
            }

            LibroArchivo.sobrescribirLibros(getContext(), lista);

            remove(libro); 
            notifyDataSetChanged();
        });

        return convertView;
    }
}
