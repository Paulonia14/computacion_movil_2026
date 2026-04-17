package com.example.retrofitpokemon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.retrofitpokemon.models.ReturnModel;
import com.google.android.material.textfield.TextInputEditText;
// Importar View
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public Button btnQuery;
    public EditText queryText;
    public String response = "", image = "";
    public ReturnModel pokedex = new ReturnModel();

    @SuppressLint("WrongViewCast")
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

        btnQuery = findViewById(R.id.btnQuery);
        queryText = findViewById(R.id.queryText);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokemonName = queryText.getText().toString().trim().toLowerCase();
                if (pokemonName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor ingrese un nombre", Toast.LENGTH_SHORT).show();
                    return;
                }

                ConsultPokeAPI consultPokeAPI = new ConsultPokeAPI();
                Toast.makeText(MainActivity.this, "Procesando consulta...", Toast.LENGTH_SHORT).show();
                
                consultPokeAPI.response(pokemonName, new ConsultPokeAPI.OnResultListener() {
                    @Override
                    public void onResult(ReturnModel pokedex) {
                        String response = "ID: " + pokedex.getId() + "\n" +
                                "Nombre: " + pokedex.getName() + "\n" +
                                "Altura(cm): " + pokedex.getHeight() + "\n" +
                                "Peso(kg): " + pokedex.getWeight();
                        String image = pokedex.getFrontDefault();

                        Intent intent = new Intent(MainActivity.this, ConsultActivity.class);
                        intent.putExtra("response", response);
                        intent.putExtra("image", image);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}