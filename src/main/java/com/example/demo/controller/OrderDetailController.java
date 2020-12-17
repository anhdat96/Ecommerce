package com.example.demo.controller;

import com.example.demo.service.IOrderDetailService;
import com.example.demo.service.dto.OrderDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController {
    @Autowired
    @Qualifier("orderDetailServiceImpl")
    private IOrderDetailService orderDetailService;

    @PostMapping(value = "/create")
    public OrderDetailDTO create(@RequestBody OrderDetailDTO orderDetail) {
        return orderDetailService.save(orderDetail);
    }

    @GetMapping(value = "/get-all")
    public List<OrderDetailDTO> findAll() {
        return orderDetailService.findAll();
    }

    @GetMapping(value = "/get-one")
    public OrderDetailDTO findById(@RequestParam Long id) {
        return orderDetailService.findById(id);
    }

    @PutMapping(value = "/update")
    public OrderDetailDTO update(@RequestParam Long id, @RequestBody OrderDetailDTO dto) {
        return orderDetailService.update(id, dto);
    }

    @DeleteMapping(value = "/delete")
    @Transactional
    public void delete(@RequestParam Long id) {
        orderDetailService.deleteById(id);
    }
}