package com.example.demo.service;

import com.example.demo.service.dto.OrdersDTO;
import com.example.demo.service.dto.ProductCategoryDTO;

import java.util.Optional;

public interface IOrderService {
    OrdersDTO save(OrdersDTO ordersDTO);
    Optional<OrdersDTO> findById(Long id);
    OrdersDTO update(OrdersDTO ordersDTO,Long id);
    void delete(Long id);
}
