package com.example.retrofit_intro;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    interface RequestUser{
        @GET("/api/users/{uid}")
        Call<Object> getUser(@Path("uid") String uid);

        @POST("/api/users")
        Call<ResponsePost> postUser(@Body RequestPost requestPost);
    }
    TextView textView;


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

        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestUser requestUser = retrofit.create(RequestUser.class);

        requestUser.postUser(new RequestPost("benkenobi","jedi")).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                if (response.isSuccessful() && response.body() != null){
                    textView.setText(response.body().toString());
                } else {
                    textView.setText("Error en servidor: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    /*
        requestUser.getUser("1").enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful() && response.body() != null) {
                    textView.setText(response.body().toString());
                } else {
                    textView.setText("Error en servidor: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t){
                textView.setText(t.getMessage());
            }

        });
    */


    }


}