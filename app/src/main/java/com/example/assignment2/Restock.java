package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Restock extends AppCompatActivity {

    Product selectedProduct;
    ListView products;
    EditText newQuantity;
    Button confirmation;
    Button back;
    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        productList = (ArrayList<Product>) getIntent().getSerializableExtra("products");

        products = (ListView) findViewById(R.id.restockList);
        newQuantity = (EditText) findViewById(R.id.newQuantityInput);
        confirmation = (Button) findViewById(R.id.confirmButton);
        back = (Button) findViewById(R.id.cancelButton);

        ProductAdapter arrayAdapter = new ProductAdapter(this, productList);

        products.setAdapter(arrayAdapter);

        products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProduct = (Product) products.getItemAtPosition(i);
            }
        });

        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedProduct == null) {
                    Toast.makeText(getApplicationContext(), "ERROR: Please select a product", Toast.LENGTH_SHORT).show();
                }
                else if(newQuantity.getText().equals("0") || newQuantity.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "ERROR: Please input a valid quantity", Toast.LENGTH_SHORT).show();
                }
                else {
                    selectedProduct.quantity += Integer.parseInt(String.valueOf(newQuantity.getText()));
                    selectedProduct = null;
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("products",productList);
        setResult(RESULT_OK, intent);
        finish();
    }
}