package com.example.crudretrofitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.crudretrofitapp.activities.CreateActivity;
import com.example.crudretrofitapp.adapters.ProductsAdapter;
import com.example.crudretrofitapp.interfaces.CRUDInterface;
import com.example.crudretrofitapp.model.Product;
import com.example.crudretrofitapp.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Product> products;
    ListView listView;
    FloatingActionButton createButton;

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

        getAll();
        listView = findViewById(R.id.listView);
        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                callCreate();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAll();
    }

    private void callCreate() {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CRUDInterface crudInterface = retrofit.create(CRUDInterface.class);
        Call<List<Product>> call = crudInterface.getAll();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()){
                    Log.e("Error", response.message());
                    System.out.println(response.message());
                    return;
                }
                products = response.body();

                ProductsAdapter productsAdapter = new ProductsAdapter(products, MainActivity.this);
                listView.setAdapter(productsAdapter);

                products.forEach(p -> {
                    Log.i("Product", p.toString());
                });
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                if (t.getMessage() != null){
                    Log.e("Error", t.getMessage());
                } else {
                    Log.e("Error", "Unknown error");
                }
            }
        });
    }
}