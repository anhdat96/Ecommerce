package com.example.demo.service.mapper;

import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.dto.ProductDTO;
import org.modelmapper.ModelMapper;

/*nguyentrong đề xuất: thêm class abstract Mapper cha bao gồm:
1. attributes: ModelMapper
2. methods:
- convertToDTO()
- convertToEntity()
**note: nếu thêm class abstract Mapper cha thì cần thêm class abstract DTO cha*/
public class OrderMapper {
    ModelMapper modelMapper = new ModelMapper();
    /* convert tu entity -->DTO*/

    public OrderDTO convertToDTO(OrderMapper order){
        OrderDTO orderDTO = modelMapper.map(order,OrderDTO.class);
        return orderDTO;
    }

    /* convert tu DTO --> Entity*/
    public Orders convertToEntity(OrderDTO orderDTO){
        Orders order = modelMapper.map(orderDTO,Orders.class);
        return order;
    }
}