package com.fa_aswinsasikanth_c0880827_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fa_aswinsasikanth_c0880827_android.DatabaseClass.ProductDatabase;
import com.fa_aswinsasikanth_c0880827_android.Models.ProductModel;
import com.google.android.material.snackbar.Snackbar;

public class ModifyProductDetails extends AppCompatActivity {

    private ProductModel model;
    private EditText product_name;
    private EditText product_description;

    private EditText product_price;

    private Button editButton;
    private int productId;

    private String name;
    private String des;
    private String price;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_product_details);

        product_name = findViewById(R.id.name);
        product_description = findViewById(R.id.description);
        product_price = findViewById(R.id.price);
        editButton = findViewById(R.id.editBtn);



        Intent productIntent = getIntent();
        productId = productIntent.getIntExtra("id", 0);
        name = productIntent.getStringExtra("name");
        des = productIntent.getStringExtra("des");
        price = productIntent.getStringExtra("price");
        location = productIntent.getStringExtra("address");

        product_name.setText(name);
        product_description.setText(des);
        product_price.setText(price);

        editButton.setOnClickListener(this::updateProductAction);
    }

    public void updateProductAction(View view) {
        Snackbar.make(view, "Update Product Details?", Snackbar.LENGTH_LONG)
                .setAction("Confirm", listener -> {

                    updateProduct();
                }).show();
    }

    private void updateProduct() {
        ProductDatabase productDatabase = new ProductDatabase(getApplicationContext());

        model = new ProductModel();
        model.setId(productId);
        model.setName(product_name.getText().toString().equals("") ? name : product_name.getText().toString());
        model.setDescription(product_description.getText().toString().equals("") ? des : product_description.getText().toString());
        model.setPrice(product_price.getText().toString().equals("") ? price : product_price.getText().toString());
       // model.setLocation(product_location.getText().toString().equals("") ? location : product_location.getText().toString());

        productDatabase.updateProduct(model);
        Intent result = new Intent();
        result.putExtra("result", "OK");
        setResult(RESULT_OK, result);
        Toast.makeText(this, "Product details have been updated", Toast.LENGTH_SHORT).show();
        finish();
    }

}