package com.example.demo.service.impl;

import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IOrderService;
import com.example.demo.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository iOrderRepository;

    public List<Orders> finAll(){
        return iOrderRepository.findAll();
    }

    public Optional<Orders> findById(Long id){
        return iOrderRepository.findById(id);
    }

    public Orders save(Orders orders){
        return  iOrderRepository.save(orders);
    }
    public void deleteById(Long id)
    {
        iOrderRepository.deleteById(id);
    }
}