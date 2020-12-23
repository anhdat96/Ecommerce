package com.example.demo.controller;

import com.example.demo.service.IOrderService;
import com.example.demo.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    @Qualifier("orderServiceImpl")
    private IOrderService orderService;

    @PostMapping(value = "/create")
    @Transactional
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO orderDTO) {
        // sử dụng Map<String, Object> để tạo json có field name tùy chỉnh

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.save(orderDTO));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<OrderDTO>> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.findAll(page, size));
    }

    @GetMapping(value = "/get-one")
    public ResponseEntity<OrderDTO> findById(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.findById(id));
    }

    @PutMapping(value = "/update")
    @Transactional
    public ResponseEntity<OrderDTO> update(@RequestParam Long id, @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.update(id, orderDTO));
    }

    @DeleteMapping(value = "/delete")
    @Transactional
    public void delete(@RequestParam Long id) {
        orderService.deleteById(id);
    }
}