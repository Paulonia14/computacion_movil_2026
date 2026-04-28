package com.compa.menucontextual_4c;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Button btnAgregar;
    EditText texto;
    ArrayList<String> listado;
    ArrayAdapter<String> adapter;
    int posicionLista;

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

        // Inicializar variables
        listado= new ArrayList<>();
        lista=findViewById(R.id.lista);
        btnAgregar=findViewById(R.id.btnAgregar);
        texto=findViewById(R.id.texto);

        // Para que aparezca el menu contextual
        registerForContextMenu(lista);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texto.getText().toString().isEmpty()) {
                    texto.setError("");
                }else {
                    if (btnAgregar.getText().toString().equals("Agregar")) {
                        listado.add(texto.getText().toString());
                        llenarLista();
                        texto.setText("");
                        texto.requestFocus();
                    }else {
                        listado.set(posicionLista, texto.getText().toString());
                        llenarLista();
                        texto.setText("");
                        texto.requestFocus();
                        btnAgregar.setText("Agregar");
                    }
                }
            }
        });

    }

    // Para poder editar items del listado
    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.lista) { //Verifica si está seleccionada la lista
            posicionLista = ((AdapterView.AdapterContextMenuInfo) menuInfo).position; //Obtiene la posicion de la lista con el adaptador
            menu.setHeaderTitle(lista.getAdapter().getItem(posicionLista).toString()); //Obtiene lo que tiene el array en esa pos
            this.getMenuInflater().inflate(R.menu.menu_main, menu); //Se lo manda al menu
        }
        super.onCreateContextMenu(menu, v, menuInfo); // Inflamos el menu
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        if (item.getItemId()==R.id.opEditar) {
            texto.setText(adapter.getItem(posicionLista));
            texto.setSelection(texto.getText().length()); //Cursor se posiciona en la parte de adelante del texto
            btnAgregar.setText("Editar");
        }
        if (item.getItemId()==R.id.opEliminar) {
            msg("Desea Eliminar este Registro?");
        }
        return super.onContextItemSelected(item);
    }

    private void msg(String mensaje) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Mensaje");
        dialogo.setMessage(mensaje);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listado.remove(posicionLista); //Si dice que si quiere borrar, eliminamos de la lista
                llenarLista(); //Volvemos a llenar la lista
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogo.show();
    }

    private void llenarLista() {
        adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listado);
        lista.setAdapter(adapter);
    }
}