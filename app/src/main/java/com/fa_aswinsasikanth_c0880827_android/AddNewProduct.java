package com.fa_aswinsasikanth_c0880827_android;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fa_aswinsasikanth_c0880827_android.DatabaseClass.ProductDatabase;
import com.fa_aswinsasikanth_c0880827_android.Models.ProductModel;



public class AddNewProduct extends AppCompatActivity {

    private EditText nameTxt;
    private EditText DesTxt;
    private EditText PriceTxt;

    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        nameTxt = findViewById(R.id.editNameTxt);
        DesTxt = findViewById(R.id.editDes);
        PriceTxt = findViewById(R.id.editPrice);

        addButton = findViewById(R.id.addBtn);

        addButton.setOnClickListener(this::addProduct);

    }

    public void addProduct(View view) {
            ProductDatabase productDatabase = new ProductDatabase(getApplicationContext());
            String location=getIntent().getStringExtra("locationLL");
            ProductModel contact = new ProductModel();
            contact.setName(nameTxt.getText().toString());
            contact.setDescription(DesTxt.getText().toString());
            contact.setPrice(PriceTxt.getText().toString());
            contact.setLocation(location);
            productDatabase.addProduct(contact);
            nameTxt.setText("");
            DesTxt.setText("");
            PriceTxt.setText("");
            Toast.makeText(this, "Product has been added", Toast.LENGTH_SHORT).show();
        }

}