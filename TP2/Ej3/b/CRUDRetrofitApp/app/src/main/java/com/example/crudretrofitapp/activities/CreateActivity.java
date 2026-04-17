package com.example.crudretrofitapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
import com.example.crudretrofitapp.adapters.ProductsAdapter;
import com.example.crudretrofitapp.dto.ProductDTO;
import com.example.crudretrofitapp.interfaces.CRUDInterface;
import com.example.crudretrofitapp.model.Product;
import com.example.crudretrofitapp.utils.Constants;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateActivity extends AppCompatActivity {

    EditText nameText;
    EditText priceText;
    Button createButton;
    CRUDInterface crudInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameText = findViewById(R.id.nameText);
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                createButton.setEnabled(buttonEnabled());
            }
        });

        priceText = findViewById(R.id.priceText);
        priceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                createButton.setEnabled(buttonEnabled());
            }
        });

        createButton = findViewById(R.id.createButton);
        createButton.setEnabled(buttonEnabled());
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDTO dto = new ProductDTO(nameText.getText().toString(), Integer.parseInt(priceText.getText().toString()));
                create(dto);
            }
        });

    }

    private void create(ProductDTO dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(CRUDInterface.class);
        Call<Product> call = crudInterface.create(dto);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (!response.isSuccessful()){
                    Log.e("Error", response.message());
                    return;
                }
                Product product = response.body();
                if (product != null) {
                    Toast.makeText(getApplicationContext(), product.getName() + ": Producto creado", Toast.LENGTH_LONG).show();
                }
                callMain();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("Error", t.getMessage() != null ? t.getMessage() : "Unknown error");
            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private boolean buttonEnabled(){
        return !nameText.getText().toString().trim().isEmpty() && !priceText.getText().toString().trim().isEmpty();
    }
}