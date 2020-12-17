package com.example.demo.service;

import com.example.demo.service.dto.OrderDetailDTO;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailDTO save(OrderDetailDTO dto);

    List<OrderDetailDTO> findAll();

    OrderDetailDTO findById(Long id);

    OrderDetailDTO update(Long id, OrderDetailDTO dto);

    void deleteById(Long id);
}