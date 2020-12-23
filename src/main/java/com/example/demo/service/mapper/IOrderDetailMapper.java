package com.example.demo.service.mapper;

import com.example.demo.models.OrderDetail;
import com.example.demo.service.dto.OrderDetailDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IOrderDetailMapper {
    OrderDetailDTO convertToDTO(OrderDetail orderDetail);

    List<OrderDetailDTO> convertToDTO(List<OrderDetail> orderDetails);

    OrderDetail convertToEntity(OrderDetailDTO orderDetailDTO);

    /*List<OrderDetail> convertToEntity(List<OrderDetailDTO> orderDetailDTOs);*/
}
