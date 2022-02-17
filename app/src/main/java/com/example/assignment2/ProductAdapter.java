package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    public ProductAdapter(Context context, ArrayList<Product> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Product item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.textName);
        TextView price = (TextView) convertView.findViewById(R.id.textPrice);
        TextView quantity = (TextView) convertView.findViewById(R.id.textQuantity);

        name.setText(item.name);
        price.setText(String.valueOf(item.price));
        quantity.setText(String.valueOf(item.quantity));


        return convertView;
    }


}
