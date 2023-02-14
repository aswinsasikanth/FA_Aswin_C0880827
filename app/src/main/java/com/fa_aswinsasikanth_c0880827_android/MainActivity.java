package com.fa_aswinsasikanth_c0880827_android;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fa_aswinsasikanth_c0880827_android.DatabaseClass.ProductDatabase;
import com.fa_aswinsasikanth_c0880827_android.Models.ProductModel;
import com.fa_aswinsasikanth_c0880827_android.ProductAdapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView productRecyclerView;
    private List<ProductModel> productList = new ArrayList<>();

    private SearchView searchView;
    private TextView count;

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchProduct);
        count=findViewById(R.id.count);
        productRecyclerView = findViewById(R.id.productList);
        productAdapter = new ProductAdapter(getAllProducts(), this);
        productRecyclerView.setAdapter(productAdapter);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView.setOnQueryTextListener(this);

        count.setText(productAdapter.getItemCount()+" products are shown");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                productAdapter = new ProductAdapter(getAllProducts(), this);
                productRecyclerView.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }
        }
    }

    public List<ProductModel> getAllProducts() {
        ProductDatabase productDatabase = new ProductDatabase(this);
        productList = productDatabase.getAllProducts();
        return productList;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        productAdapter.filter(newText);
        return false;
    }

    public void AddNew(View view) {
        startActivity(new Intent(MainActivity.this,SelectLocation.class));
    }
}