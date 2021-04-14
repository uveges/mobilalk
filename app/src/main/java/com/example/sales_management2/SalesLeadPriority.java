package com.example.sales_management2;

public enum SalesLeadPriority {

    low(1), medium(2), high(3);

    private int prior;

    SalesLeadPriority(int x) {
        this.prior = x;
    }
}
