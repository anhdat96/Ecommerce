package com.example.demo.controller;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.service.impl.OrderDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-detail")
@Slf4j
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailServiceImpl orderDetailServiceImpl;

    @GetMapping(value = "/all_order_detail")
    public List<OderDetail> findAll() {
        return orderDetailServiceImpl.finAll();
    }

    @PostMapping(value = "/create")
    public OderDetail create(@RequestBody OderDetail orderDetail) {
        return orderDetailServiceImpl.save(orderDetail);
    }

    @GetMapping(value = "/get-one-order-detail/{id}")
    public OderDetail findById(@PathVariable Long id) {
        Optional<OderDetail> optionalOrderDetail = orderDetailServiceImpl.findById(id);
        if (!optionalOrderDetail.isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        }
        return optionalOrderDetail.get();
    }
    @PutMapping(value = "/update/{id}")
    public OderDetail update(@PathVariable Long id,@RequestBody OderDetail orderDetail){
        Optional<OderDetail> optionalOrderDetail = orderDetailServiceImpl.findById(id);
        if(!orderDetailServiceImpl.findById(id).isPresent()){
            log.error("ID "+ id + "is not exist");
            ResponseEntity.badRequest().build();
            return optionalOrderDetail.get();
        }else {
            orderDetail.setDetailID(id);
            return orderDetailServiceImpl.save(orderDetail);
        }
    }

    @DeleteMapping(value = "/delete-order-detail/{id}")
    public void delete(@PathVariable Long id){
        if(!orderDetailServiceImpl.findById(id).isPresent()){
            log.error("ID "+ id + "is not exist");
            ResponseEntity.badRequest().build();
        }
        orderDetailServiceImpl.deleteById(id);
    }
}