package com.example.tp_compmovil_2d;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.actionmodecallback.MenuItemOption;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_compmovil_2d.adapters.ListContactsAdapter;
import com.example.tp_compmovil_2d.db.DbContacts;
import com.example.tp_compmovil_2d.db.DbHelper;
import com.example.tp_compmovil_2d.entities.Contact;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements androidx.appcompat.widget.SearchView.OnQueryTextListener {

    RecyclerView listContacts;
    ArrayList<Contact> contacts;
    ListContactsAdapter adapter;

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

        listContacts = findViewById(R.id.listContacts);
        listContacts.setLayoutManager(new LinearLayoutManager(this));

        DbContacts dbContacts = new DbContacts(this);
        ArrayList<Contact> list = dbContacts.getAll();

        if (list == null) {
            list = new ArrayList<>();
        }

        adapter = new ListContactsAdapter(list);
        listContacts.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.newMenu) {
            newRegistry();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void newRegistry() {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Volver a cargar la lista para ver los nuevos registros
        DbContacts dbContacts = new DbContacts(this);
        ArrayList<Contact> list = dbContacts.getAll();
        if (list == null) list = new ArrayList<>();

        adapter = new ListContactsAdapter(list);
        listContacts.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (adapter != null) {
            adapter.filter(newText);
        }
        return true;
    }
}