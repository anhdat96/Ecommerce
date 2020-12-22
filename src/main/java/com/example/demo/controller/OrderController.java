package com.example.demo.controller;

import com.example.demo.service.IOrderService;
import com.example.demo.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    @Qualifier("orderServiceImpl")
    private IOrderService orderService;

    @PostMapping(value = "/create")
    @Transactional
    public OrderDTO create(@RequestBody OrderDTO orderDTO) {
        // sử dụng Map<String, Object> để tạo json có field name tùy chỉnh

        return orderService.save(orderDTO);
    }

    @GetMapping(value = "/get-all")
    public List<OrderDTO> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return orderService.findAll(page, size);
    }

    @GetMapping(value = "/get-one")
    public OrderDTO findById(@RequestParam Long id) {
        return orderService.findById(id);
    }

    @PutMapping(value = "/update")
    @Transactional
    public OrderDTO update(@RequestParam Long id, @RequestBody OrderDTO orderDTO) {
        return orderService.update(id, orderDTO);
    }

    @DeleteMapping(value = "/delete")
    @Transactional
    public void delete(@RequestParam Long id) {
        orderService.deleteById(id);
    }
}