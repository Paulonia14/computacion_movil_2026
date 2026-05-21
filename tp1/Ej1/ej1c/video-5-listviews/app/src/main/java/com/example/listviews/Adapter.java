package com.example.listviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Adapter extends ArrayAdapter<Modelo> {
    public Adapter(@NonNull Context context, @NonNull List<Modelo> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.pais, parent, false);
        }

        Modelo pais = getItem(position);

        ImageView image = convertView.findViewById(R.id.imgPais);
        TextView title = convertView.findViewById(R.id.txvPaisNombre);
        TextView description = convertView.findViewById(R.id.txvPaisDescripcion);

        image.setImageResource(pais.getImagen());
        title.setText(pais.getNombre());
        description.setText(pais.getDescripcion());

        return convertView;
    }
}
