package com.example.demo.service.mapper;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.dto.OrderDetailDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface IOrderDetailMapper {
    OrderDetailDTO convertToDTO(OderDetail orderDetail);

    OderDetail convertToEntity(OrderDetailDTO orderDetailDTO);
}
