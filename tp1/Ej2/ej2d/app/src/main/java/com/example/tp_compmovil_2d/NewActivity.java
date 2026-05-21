package com.example.tp_compmovil_2d;

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

import com.example.tp_compmovil_2d.db.DbContacts;

public class NewActivity extends AppCompatActivity {

    private EditText txtName, txtPhone, txtEmail;
    private Button btnSaveRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        btnSaveRegistry = findViewById(R.id.btnUpdate);

        btnSaveRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContacts dbContacts = new DbContacts(NewActivity.this);
                long id = dbContacts.insert(txtName.getText().toString(), txtPhone.getText().toString(), txtEmail.getText().toString());
                if (id > 0) {
                    Toast.makeText(NewActivity.this, "Registro guardado correctamente", Toast.LENGTH_LONG).show();
                    cleanAttributes();
                } else {
                    Toast.makeText(NewActivity.this, "Registro no guardado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cleanAttributes(){
        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }
}