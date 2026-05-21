package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapplication.R;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;
    Button btnListar, btnRegistrar, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        context = getApplicationContext();
        btnBuscar = findViewById(R.id.btnbuscar);
        btnListar = findViewById(R.id.btnlistar);
        btnRegistrar = findViewById(R.id.btnregistrar);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnregistrar) {
            Toast.makeText(context, "Registrar", Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, GestionarLibroActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", 0);
            i.putExtras(bundle);
            startActivity(i);

        } else if (v.getId() == R.id.btnlistar) {
            Toast.makeText(context, "Listar", Toast.LENGTH_LONG).show();
            Intent i2 = new Intent(context, ListadoLibrosActivity.class);
            startActivity(i2);

        } else if (v.getId() == R.id.btnbuscar) {
            Toast.makeText(context, "Buscar", Toast.LENGTH_LONG).show();
            Intent i3 = new Intent(context, BuscarLibroActivity.class);
            startActivity(i3);
        }
    }
}