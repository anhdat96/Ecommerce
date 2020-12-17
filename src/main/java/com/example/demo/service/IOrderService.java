package com.example.demo.service;

import com.example.demo.service.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    OrderDTO save(OrderDTO orderDTO);

    List<OrderDTO> findAll();

    OrderDTO findById(Long id);

    OrderDTO update(Long id, OrderDTO orderDTO);

    void deleteById(Long id);
}