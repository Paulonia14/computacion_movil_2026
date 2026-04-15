package com.example.lectorqrbarras;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button btnGenerate;
    private Button btnScan;
    private EditText teTexto;
    private ImageView imgCodigoQr;

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

        btnGenerate = findViewById(R.id.btnGenerate);
        btnScan = findViewById(R.id.btnScan);
        teTexto = findViewById(R.id.etTexto);
        imgCodigoQr = findViewById(R.id.imgCodigoQr);

        btnGenerate.setOnClickListener(this::generarCodigo);
        btnScan.setOnClickListener(this::escanearCodigo);
    }

    private void generarCodigo(View v) {
        try {
            BarcodeEncoder encoder = new BarcodeEncoder();
            imgCodigoQr.setImageBitmap(encoder.encodeBitmap(teTexto.getText().toString(), BarcodeFormat.QR_CODE, 750, 750));
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
            Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
        }
    }

    private void escanearCodigo(View v) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Lectura de códigos");
        integrator.setCameraId(0);

        integrator.setOrientationLocked(false);
        integrator.setCaptureActivity(CapturaVertical.class);

        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result == null)
            super.onActivityResult(requestCode, resultCode, data);

        if (result.getContents() == null)
            Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_SHORT).show();

        teTexto.setText(result.getContents());
    }
}