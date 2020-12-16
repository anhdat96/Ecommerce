package com.example.demo.constant;

public enum Districts {
    QUAN1("quan1"), QUAN2("quan2"), QUAN3("quan3");

    private final String name;

    Districts(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
