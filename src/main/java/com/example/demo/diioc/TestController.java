package com.example.demo.diioc;

public class TestController implements ITest {
    Manager m;

    public TestController(Manager m) {
        this.m = m;
    }

    public static void main(String[] args) {
    }

    public void test() {
        m.toString();
    }
}
