package com.example.assignment2;

import java.io.Serializable;
import java.util.Date;

public class Purchase implements Serializable {
    String name;
    int quantity;
    double total;
    Date purchaseTime;

    public Purchase(String n, int q, double t, Date p) {
        name = n;
        quantity = q;
        total = t;
        purchaseTime = p;
    }
}
