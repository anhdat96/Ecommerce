package com.example.demo.service.impl;


import com.example.demo.service.Ifactorial;
import org.springframework.stereotype.Service;

@Service
public class factorialImpl implements Ifactorial {
    @Override
    public int countfactorial(int a) {
        int fact =  1;
        for (int i =1 ; i <= a ; i++){
            fact = fact*i;
        }
        return fact;
    }
}
