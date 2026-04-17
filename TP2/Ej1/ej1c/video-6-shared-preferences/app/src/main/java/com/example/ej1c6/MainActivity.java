package com.example.ej1c6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText txtEditNote;
    private TextView txtNote;
    private Button btnSave;

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

        txtEditNote = findViewById(R.id.txtEditNote);
        txtNote = findViewById(R.id.txtNote);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNota();
            }
        });

        // Evento teclado
        txtEditNote.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                guardarNota();
                return true;
            }
            return false;
        });

        cargarNotas();
    }

    private void cargarNotas() {
        SharedPreferences sharedPreferences = getSharedPreferences("Notas", MODE_PRIVATE);
        String nota = sharedPreferences.getString("nota", "");

        if (!nota.isEmpty()) {
            txtNote.setText(nota);
        }
    }

    private void guardarNota() {
        String nota = txtEditNote.getText().toString().trim();

        if (!nota.isEmpty()) {
            SharedPreferences sharedPreferences = getSharedPreferences("Notas", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nota", nota);
            editor.apply();

            txtNote.setText(nota);
            txtEditNote.setText("");
            Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "La nota está vacía", Toast.LENGTH_SHORT).show();
        }
    }
}