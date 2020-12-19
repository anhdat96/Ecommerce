package com.example.demo.service;

import com.example.demo.service.dto.OrderDetailDTO;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailDTO save(OrderDetailDTO dto);

    List<OrderDetailDTO> findAll();

    OrderDetailDTO findById(Long id);

    List<OrderDetailDTO> findByDetailName(final String fieldName, String name);

    OrderDetailDTO update(Long id, OrderDetailDTO dto);

    void deleteById(Long id);
}