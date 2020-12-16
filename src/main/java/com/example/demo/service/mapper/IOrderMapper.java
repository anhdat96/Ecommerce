package com.example.demo.service.mapper;

import com.example.demo.models.Orders;
import com.example.demo.service.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public interface IOrderMapper {
    OrderDTO convertToDTO(Orders order);

    Orders convertToEntity(OrderDTO orderDTO);
}
