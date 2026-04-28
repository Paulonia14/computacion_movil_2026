package com.example.tp_compmovil_2d;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import com.example.tp_compmovil_2d.entities.Contact;

public class EditActivity extends AppCompatActivity {

    EditText txtName, txtPhone, txtEmail;
    Button btnUpdate;
    Contact contact;
    int id = 0;
    boolean isUpdate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        btnUpdate = findViewById(R.id.btnUpdate);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbContacts dbContacts = new DbContacts(this);
        contact = dbContacts.getById(id);

        if (contact != null) {
            txtName.setText(contact.getName());
            txtPhone.setText(contact.getPhone());
            txtEmail.setText(contact.getEmail());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtName.getText().toString().equals("") && !txtPhone.getText().toString().equals("")) {
                    isUpdate = dbContacts.update(id, txtName.getText().toString(), txtPhone.getText().toString(), txtEmail.getText().toString());

                    if (isUpdate) {
                        Toast.makeText(EditActivity.this, "Registro actualizado", Toast.LENGTH_SHORT).show();
                        viewRegistry();
                    } else {
                        Toast.makeText(EditActivity.this, "Error al actualizar el registro", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditActivity.this, "Debe completar los campos nombre y teléfono", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void viewRegistry() {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
