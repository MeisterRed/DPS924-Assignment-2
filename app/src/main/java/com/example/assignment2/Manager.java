package com.example.assignment2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Manager extends AppCompatActivity {

    Button restock;
    Button history;
    ArrayList<Product> restockList;
    ArrayList<Purchase> historyList;
    ActivityResultLauncher<Intent> restockSettings;
    ActivityResultLauncher<Intent> historySettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        restock = (Button) findViewById(R.id.restockButton);
        history = (Button) findViewById(R.id.historyButton);
        restockList = (ArrayList<Product>) getIntent().getSerializableExtra("products");
        historyList = (ArrayList<Purchase>) getIntent().getSerializableExtra("purchases");

        restock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager.this, Restock.class);
                intent.putExtra("products",restockList);
                restockSettings.launch(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manager.this, History.class);
                intent.putExtra("purchases",historyList);
                historySettings.launch(intent);
            }
        });

        historySettings = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Bundle bundle = data.getExtras();
                    historyList = (ArrayList<Purchase>) bundle.get("purchases");
                }
            }
        });

        restockSettings = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Bundle bundle = data.getExtras();
                    restockList = (ArrayList<Product>) bundle.get("products");
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("products",restockList);
        setResult(RESULT_OK, intent);
        finish();
    }

}