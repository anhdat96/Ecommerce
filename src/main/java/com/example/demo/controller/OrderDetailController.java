package com.example.demo.controller;

import com.example.demo.service.IOrderDetailService;
import com.example.demo.service.dto.OrderDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order-detail")
public class OrderDetailController {
    @Autowired
    @Qualifier("orderDetailServiceImpl")
    private IOrderDetailService orderDetailService;

    @PostMapping(value = "/create")
    public ResponseEntity<OrderDetailDTO> create(@Valid @RequestBody OrderDetailDTO orderDetail) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderDetailService.save(orderDetail));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<OrderDetailDTO>> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderDetailService.findAll(page, size));
    }

    @GetMapping(value = "/get-one")
    public ResponseEntity<OrderDetailDTO> findById(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderDetailService.findById(id));
    }

    @GetMapping(value = "/get-by-name")
    public ResponseEntity<List<OrderDetailDTO>> findByDetailName(@RequestParam String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderDetailService.findByDetailName("detailName", name));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<OrderDetailDTO> update(@RequestParam Long id, @Valid @RequestBody OrderDetailDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderDetailService.update(id, dto));
    }

    @DeleteMapping(value = "/delete")
    @Transactional
    public void delete(@RequestParam Long id) {
        orderDetailService.deleteById(id);
    }
}