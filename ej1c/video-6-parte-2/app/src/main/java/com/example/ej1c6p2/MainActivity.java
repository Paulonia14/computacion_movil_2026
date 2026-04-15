package com.example.ej1c6p2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnStop;
    private TextView tcvClock;
    private EditText txEdit;
    private TextView txvMensaje;

    private boolean running = false;

    @SuppressLint("ClickableViewAccessibility")
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

        // SETEAR REFERENCIAS

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        tcvClock = findViewById(R.id.txvClock);
        txEdit = findViewById(R.id.txEdit);
        txvMensaje = findViewById(R.id.tcvMensaje);

        // EVENTOS TECLADO Y TAPS

        txEdit.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                txvMensaje.setText("Tecla presionada: " + keyCode);
            }
            return false;
        });

        txvMensaje.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    txvMensaje.setText("Presionado");
                    return true;
                case MotionEvent.ACTION_MOVE:
                    txvMensaje.setText("Movido");
                    return true;
                case MotionEvent.ACTION_UP:
                    txvMensaje.setText("Soltado");
                    return true;
                default:
                    return false;
            }
        });

        // BOTONES CONTROLAN HILO

        btnStart.setOnClickListener(v -> {
            if (running)
                return;
            running = true;
            new Thread(this::actualizarReloj).start();
            txvMensaje.setText("Se inició el hilo");
        });

        btnStop.setOnClickListener(v -> {
            running = false;
            txvMensaje.setText("Se detuvo el hilo");
        });
    }

    private void actualizarReloj() {
        while (running) {
            try {
                Thread.sleep(200);
                runOnUiThread(() -> {
                    String hora = new java.text.SimpleDateFormat("HH:mm:ss.SSS",
                            java.util.Locale.getDefault())
                            .format(new java.util.Date());

                    tcvClock.setText(hora);
                });
            } catch (InterruptedException e) {
                running = false;
                Log.e("ERROR", e.getMessage());
                break;
            }
        }
    }
}