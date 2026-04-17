package com.example.listviews;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        ListView listView = findViewById(R.id.lsvPaises);

        List<Modelo> items = new ArrayList<>();
        items.add(new Modelo("Argentina", "Sudamérica", R.drawable.ar));
        items.add(new Modelo("Austria", "Europa", R.drawable.at));
        items.add(new Modelo("Brasil", "Sudamérica", R.drawable.br));
        items.add(new Modelo("Canadá", "Norteamérica", R.drawable.ca));

        Adapter adapter = new Adapter(this, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
           Modelo pais = (Modelo) parent.getItemAtPosition(position);
           Toast.makeText(this, pais.getNombre(), Toast.LENGTH_SHORT).show();
        });
    }
}