package com.example.demo.constant;

public enum ProductTypes {
    ADIDAS("adidas"),
    NIKE("nike");
    private final String name;

    ProductTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
