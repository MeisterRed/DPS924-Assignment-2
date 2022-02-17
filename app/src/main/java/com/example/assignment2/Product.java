package com.example.assignment2;
import java.io.Serializable;

public class Product implements Serializable {
    String name;
    int quantity;
    double price;

    public Product(String n, int q, double p) {
        name = n;
        quantity = q;
        price = p;
    }
}
