package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesAdapter.ViewHolder> {

    private static ArrayList<Purchase> purchaseList = new ArrayList<>();
    private static Context context;

    public PurchasesAdapter(ArrayList<Purchase> purchases, Context c) {
        purchaseList = purchases;
        context = c;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textName;
        private final TextView textQuantity;
        private final TextView textTotal;

        public ViewHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.text_pName);
            textQuantity = (TextView) itemView.findViewById(R.id.text_pQuantity);
            textTotal = (TextView) itemView.findViewById(R.id.text_pTotal);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, PurchaseDetailDisplay.class);
            intent.putExtra("purchase",purchaseList.get(getLayoutPosition()));
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.purchases, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText(purchaseList.get(position).name);
        holder.textQuantity.setText(String.valueOf(purchaseList.get(position).quantity));
        holder.textTotal.setText(String.valueOf(purchaseList.get(position).total));

    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }


}
