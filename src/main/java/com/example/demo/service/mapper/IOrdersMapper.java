package com.example.demo.service.mapper;

import com.example.demo.models.Orders;
import com.example.demo.service.dto.OrdersDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {})
public interface IOrdersMapper extends EntityMapper<OrdersDTO , Orders>{

}
