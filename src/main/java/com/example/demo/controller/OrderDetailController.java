package com.example.demo.controller;

import com.example.demo.service.dto.OrderDetailDTO;
import com.example.demo.service.impl.OrderDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/api/order-detail")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailServiceImpl orderDetailServiceImpl;

    @PostMapping(value = "/create")
    public OrderDetailDTO create(@RequestBody OrderDetailDTO orderDetail) {
        return orderDetailServiceImpl.save(orderDetail);
    }

    @GetMapping(value = "/get-all")
    public Set<OrderDetailDTO> findAll() {
        return orderDetailServiceImpl.findAll();
    }

    @GetMapping(value = "/get-one")
    public OrderDetailDTO findById(@RequestParam Long id) {
        return orderDetailServiceImpl.findById(id);
    }

    @PutMapping(value = "/update")
    public OrderDetailDTO update(@RequestParam Long id, @RequestBody OrderDetailDTO dto) {
        return orderDetailServiceImpl.update(id, dto);
    }

    @DeleteMapping(value = "/delete")
    @Transactional
    public void delete(@RequestParam Long id) {
        orderDetailServiceImpl.deleteById(id);
    }
}