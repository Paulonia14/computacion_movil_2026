package com.example.tp_compmovil_2d;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tp_compmovil_2d.db.DbContacts;
import com.example.tp_compmovil_2d.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewActivity extends AppCompatActivity {

    EditText txtName, txtPhone, txtEmail;
    FloatingActionButton floatingEditButton;
    FloatingActionButton floatingDeleteButton;
    Button btnUpdate;
    Contact contact;
    int id = 0;


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
        floatingEditButton = findViewById(R.id.floatingEditButton);
        floatingDeleteButton = findViewById(R.id.floatingDeleteButton);

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
            btnUpdate.setVisibility(View.INVISIBLE);
            txtName.setInputType(InputType.TYPE_NULL);
            txtPhone.setInputType(InputType.TYPE_NULL);
            txtEmail.setInputType(InputType.TYPE_NULL);
        }

        floatingEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewEdit();
            }
        });

        floatingDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRegistry();
            }
        });

    }

    private void deleteRegistry() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar eliminación");
        builder.setMessage("¿Estás seguro de que deseas eliminar este registro?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DbContacts dbContacts = new DbContacts(ViewActivity.this);
                        if (dbContacts.delete(id)) {
                            Toast.makeText(ViewActivity.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ViewActivity.this, "Error al eliminar el registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ViewActivity.this, "Operación cancelada", Toast.LENGTH_SHORT).show();
                    }
                }).show();

    }

    private void viewEdit() {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}