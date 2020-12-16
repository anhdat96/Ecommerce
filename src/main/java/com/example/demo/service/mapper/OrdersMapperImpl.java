package com.example.demo.service.mapper;

import com.example.demo.models.Orders;
import com.example.demo.service.dto.OrdersDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrdersMapperImpl implements IOrdersMapper {
    @Override
    public Orders toEntity(OrdersDTO DTO) {
        return null;
    }

    @Override
    public OrdersDTO toDto(Orders entity) {
        return null;
    }

    @Override
    public List<Orders> toEntity(List<OrdersDTO> dtoList) {
        return null;
    }

    @Override
    public List<OrdersDTO> toDto(List<Orders> entityList) {
        return null;
    }
}
