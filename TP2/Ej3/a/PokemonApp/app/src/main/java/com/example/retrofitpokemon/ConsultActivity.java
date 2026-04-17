package com.example.retrofitpokemon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class ConsultActivity extends AppCompatActivity {
    TextView pokeDataView;
    ImageView pokeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consult);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pokeDataView = findViewById(R.id.pokeDataView);
        pokeImageView = findViewById(R.id.pokeImageView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String response = extras.getString("response");
            String image = extras.getString("image");
            pokeDataView.setText(response);
            if (image != null && !image.isEmpty()) {
                Picasso.get().load(image).into(pokeImageView);
            }
        }

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}