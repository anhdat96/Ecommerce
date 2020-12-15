package com.example.demo.controller;

import com.example.demo.models.Orders;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    @PostMapping(value = "/create")
    @Transactional
    public OrderDTO create(@RequestBody OrderDTO orderDTO) {
        return orderServiceImpl.save(orderDTO);
    }

    @GetMapping(value = "/get-all")
    public List<OrderDTO> findAll() {
        return orderServiceImpl.findAll();
    }

    @GetMapping(value = "/get-one")
    public OrderDTO findById(@RequestParam Long id) {
        return orderServiceImpl.findById(id);
    }

    @PutMapping(value = "/update")
    @Transactional
    public OrderDTO update(@RequestParam Long id,@RequestBody OrderDTO orderDTO){
        return orderServiceImpl.update(id, orderDTO);
    }

    @DeleteMapping(value = "/delete")
    @Transactional
    public void delete(@RequestParam Long id){
        orderServiceImpl.deleteById(id);
    }
}