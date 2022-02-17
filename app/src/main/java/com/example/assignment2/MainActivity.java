package com.example.assignment2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

Product selectedProduct;
NumberPicker quantityPicker;
TextView productType;
TextView quantity;
TextView total;
ListView products;
Button manager;
Button buy;
ArrayList<Product> productList;
ArrayList<Purchase> historyList;
ActivityResultLauncher<Intent> managerSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantity = (TextView) findViewById(R.id.tv_quantity);
        productType = (TextView) findViewById(R.id.tv_productType);
        quantityPicker = (NumberPicker) findViewById(R.id.numberPicker);
        products = (ListView) findViewById(R.id.Products);
        total = (TextView) findViewById(R.id.tv_total);
        buy = (Button) findViewById(R.id.buyButton);
        manager = (Button) findViewById(R.id.managerButton);


        //ListView Initialization
        Product[] items = new Product[] {
                new Product("Hat",30, 10.44),
                new Product("Shoes",100, 10.44),
                new Product("Shirt",74, 15.00),
        };

        productList = new ArrayList<Product>(Arrays.asList(items));
        historyList = new ArrayList<Purchase>();

        ProductAdapter arrayAdapter = new ProductAdapter(this, productList);

        products.setAdapter(arrayAdapter);

        products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProduct = (Product) products.getItemAtPosition(i);
                productType.setText(selectedProduct.name);
                calculateTotal();
            }
        });

        //NumberPicker Initialization
        quantityPicker.setMinValue(0);
        quantityPicker.setMaxValue(100);
        quantity.setText(String.valueOf(0));
        total.setText(String.valueOf(0.0));

        quantityPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                quantity.setText(String.valueOf(i1));
                if(selectedProduct != null) {
                    calculateTotal();
                }
            }
        });

        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Manager.class);
                intent.putExtra("products",productList);
                intent.putExtra("purchases", historyList);
                managerSettings.launch(intent);
            }
        });

        managerSettings = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bundle bundle = data.getExtras();
                            arrayAdapter.clear();
                            arrayAdapter.addAll((ArrayList<Product>) bundle.get("products"));
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchaseItem();
                arrayAdapter.notifyDataSetChanged();

            }
        });

    }


    void calculateTotal() {
        total.setText(String.valueOf(Double.parseDouble((String)quantity.getText()) * selectedProduct.price));
    }

    public void purchaseItem() {
        if (quantity.getText().equals("0") || selectedProduct == null) {
            Toast.makeText(getApplicationContext(), "The Quantity and Product Type fields need to be filled", Toast.LENGTH_SHORT).show();
        }
        else if (Integer.parseInt(String.valueOf(quantity.getText())) > selectedProduct.quantity) {
            Toast.makeText(getApplicationContext(), "Not enough " + selectedProduct.name + "s in stock!", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("You've purchased " + quantity.getText() + " " + selectedProduct.name + " for $" + total.getText())
                    .setTitle("Thank You!!");

            Purchase temp = new Purchase(selectedProduct.name, Integer.parseInt((String) quantity.getText()),
                    Double.parseDouble((String) total.getText()), new Date());
            historyList.add(temp);


            selectedProduct.quantity -= Integer.parseInt((String) quantity.getText());
            selectedProduct = null;
            productType.setText("Product Type");
            quantityPicker.setValue(0);
            quantity.setText(String.valueOf(0));
            total.setText(String.valueOf(0.0));
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

}