package com.example.demo.service.mapper;

import com.example.demo.models.Orders;
import com.example.demo.service.dto.OrdersDTO;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrdersMapperImpl implements IOrdersMapper {
    @Override
    public Orders toEntity(OrdersDTO ordersDTO) {
        if(ordersDTO == null){
            return null;
        }
        Orders orders = new Orders();
        orders.setOrderID(ordersDTO.getOrderID());
        orders.setOrderAmount(ordersDTO.getOrderAmount());
        orders.setOrderCity(ordersDTO.getOrderCity());
        orders.setOrderDate(ordersDTO.getOrderDate());
        orders.setOrderEmail(ordersDTO.getOrderEmail());
        orders.setOrderCountry(ordersDTO.getOrderCountry());
        orders.setOrderPhone(ordersDTO.getOrderPhone());
        orders.setOrderShipName(ordersDTO.getOrderShipName());
        orders.setOrderShipAddress(ordersDTO.getOrderShipAddress());

        return orders;
    }

    @Override
    public OrdersDTO toDto(Orders orders) {
        if(orders == null){
            return null;
        }
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrderID(orders.getOrderID());
        ordersDTO.setOrderAmount(orders.getOrderAmount());
        ordersDTO.setOrderCity(orders.getOrderCity());
        ordersDTO.setOrderDate(orders.getOrderDate());
        ordersDTO.setOrderEmail(orders.getOrderEmail());
        ordersDTO.setOrderCountry(orders.getOrderCountry());
        ordersDTO.setOrderPhone(orders.getOrderPhone());
        ordersDTO.setOrderShipName(orders.getOrderShipName());
        ordersDTO.setOrderShipAddress(orders.getOrderShipAddress());
        ordersDTO.setOrderUserID(orders.getUser().getUserID());
        return ordersDTO;
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
