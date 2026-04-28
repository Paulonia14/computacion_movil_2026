package com.example.crudretrofitapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.crudretrofitapp.activities.DetailActivity;
import com.example.crudretrofitapp.MainActivity;
import com.example.crudretrofitapp.R;
import com.example.crudretrofitapp.model.Product;

import java.util.List;

public class ProductsAdapter extends BaseAdapter {
    List<Product> products;
    Context context;
    TextView nameText;
    Button viewButton;

    public ProductsAdapter(List<Product> products, MainActivity mainActivity) {
        this.products = products;
        this.context = mainActivity;
    }


    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.product_list, null);
        }
        nameText = view.findViewById(R.id.nameText);
        nameText.setText(products.get(position).getName());
        viewButton = view.findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDetail(products.get(position).getId());
            }
        });

        return view;
    }

    private void callDetail(int id){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }
}
