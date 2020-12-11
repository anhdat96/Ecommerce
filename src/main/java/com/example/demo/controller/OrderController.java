package com.example.demo.controller;

import com.example.demo.models.Orders;
import com.example.demo.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    @GetMapping(value = "/all_orders")
    public List<Orders> findAll() {
        return orderServiceImpl.finAll();
    }

    @PostMapping(value = "/create")
    public Orders create(@RequestBody Orders orders) {
        return orderServiceImpl.save(orders);
    }

    @GetMapping(value = "/get-one-order/{id}")
    public Orders findById(@PathVariable Long id) {
        Optional<Orders> optionalOrders = orderServiceImpl.findById(id);
        if (!optionalOrders.isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        }
        return optionalOrders.get();
    }
    @PutMapping(value = "/update/{id}")
    public Orders update(@PathVariable Long id,@RequestBody Orders order){
        Optional<Orders> optionalOrders = orderServiceImpl.findById(id);
        if(!orderServiceImpl.findById(id).isPresent()){
            log.error("ID "+ id + "is not exist");
            ResponseEntity.badRequest().build();
            return optionalOrders.get();
        }else {
            order.setOrderID(id);
            return orderServiceImpl.save(order);
        }
    }

    @DeleteMapping(value = "/delete-order/{id}")
    public void delete(@PathVariable Long id){
        if(!orderServiceImpl.findById(id).isPresent()){
            log.error("ID "+ id + "is not exist");
            ResponseEntity.badRequest().build();
        }
        orderServiceImpl.deleteById(id);
    }
}