package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    RecyclerView purchases;
    ArrayList<Purchase> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        purchases  = (RecyclerView) findViewById(R.id.recyclerView);
        historyList = (ArrayList<Purchase>) getIntent().getSerializableExtra("purchases");

        purchases.setLayoutManager(new LinearLayoutManager(this));

        PurchasesAdapter adapter = new PurchasesAdapter(historyList, this);

        purchases.setAdapter(adapter);

        adapter.notifyItemRangeInserted(0, historyList.size());
    }
}