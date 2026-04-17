package com.example.crudretrofitapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.crudretrofitapp.MainActivity;
import com.example.crudretrofitapp.R;
import com.example.crudretrofitapp.dto.ProductDTO;
import com.example.crudretrofitapp.interfaces.CRUDInterface;
import com.example.crudretrofitapp.model.Product;
import com.example.crudretrofitapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditActivity extends AppCompatActivity {

    EditText nameText;
    EditText priceText;
    Button editButton;
    CRUDInterface crudInterface;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameText = findViewById(R.id.nameText);
        priceText = findViewById(R.id.priceText);
        editButton = findViewById(R.id.editButton);

        id = getIntent().getExtras().getInt("id");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(CRUDInterface.class);

        // Cargamos los datos actuales
        getOne(id);

        editButton.setOnClickListener(v -> {
            String name = nameText.getText().toString();
            int price = Integer.parseInt(priceText.getText().toString());
            ProductDTO dto = new ProductDTO(name, price);
            update(id, dto);
        });
    }

    private void getOne(int id) {
        Call<Product> call = crudInterface.getOne(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product product = response.body();
                    nameText.setText(product.getName());
                    priceText.setText(String.valueOf(product.getPrice()));
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void update(int id, ProductDTO dto) {
        Call<Product> call = crudInterface.update(id, dto);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "Producto actualizado", Toast.LENGTH_SHORT).show();
                callMain();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}