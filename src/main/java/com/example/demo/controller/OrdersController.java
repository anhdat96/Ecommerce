package com.example.demo.controller;

import com.example.demo.service.IOrderService;
import com.example.demo.service.dto.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/oders")
public class OrdersController {
    @Autowired
    IOrderService iOrderService;

    @PostMapping(value = "/create")
    public OrdersDTO create(@RequestBody OrdersDTO ordersDTO) {
        return iOrderService.save(ordersDTO);
    }

    @GetMapping("/get-all-orders")
    public List<OrdersDTO> findall() {
        List<OrdersDTO> ordersDTOS = new ArrayList<>();
        return ordersDTOS;
    }

    @GetMapping("/get-one-orders/{id}")
    public OrdersDTO findOne(@PathVariable Long id) {
        return iOrderService.findById(id).get();
    }

    @PutMapping("/update-orders/{id}")
    public OrdersDTO update(@RequestBody OrdersDTO ordersDTO, @PathVariable Long id) {
        return  iOrderService.update(ordersDTO, id);
    }

    @DeleteMapping(value = "/delete-orders/{id}")
    public void delete(@PathVariable Long id) {
        iOrderService.delete(id);
    }
}
