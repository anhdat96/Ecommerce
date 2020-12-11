package com.example.demo.service.mapper;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.dto.OrderDetailDTO;
import org.modelmapper.ModelMapper;

public class OrderDetailMapper {
    ModelMapper modelMapper = new ModelMapper();
    /* convert tu entity -->DTO*/

    public OrderDetailDTO convertToDTO(OderDetail orderDetail){
        OrderDetailDTO orderDTO = modelMapper.map(orderDetail,OrderDetailDTO.class);
        return orderDTO;
    }

    /* convert tu DTO --> Entity*/
    public OderDetail convertToEntity(OrderDTO orderDTO){
        OderDetail order = modelMapper.map(orderDTO,OderDetail.class);
        return order;
    }
}