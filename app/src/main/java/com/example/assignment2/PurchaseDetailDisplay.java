package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PurchaseDetailDisplay extends AppCompatActivity {

    TextView productType;
    TextView total;
    TextView date;

    Purchase purchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_details);

        productType = (TextView) findViewById(R.id.detailName);
        total = (TextView) findViewById(R.id.detailPrice);
        date = (TextView) findViewById(R.id.detailDate);

        purchase = (Purchase) getIntent().getSerializableExtra("purchase");

        productType.setText(purchase.name);
        total.setText(String.valueOf(purchase.total));
        date.setText(String.valueOf(purchase.purchaseTime));


    }
}