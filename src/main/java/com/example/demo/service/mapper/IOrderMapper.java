package com.example.demo.service.mapper;

import com.example.demo.models.Orders;
import com.example.demo.service.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Mapper
public interface IOrderMapper {
    OrderDTO convertToDTO(Orders order);

    Orders convertToEntity(OrderDTO orderDTO);
}
