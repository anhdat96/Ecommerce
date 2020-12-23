package com.example.demo.service.mapper.impl;

import com.example.demo.models.OrderDetail;
import com.example.demo.models.Orders;
import com.example.demo.models.User;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.mapper.BaseMapper;
import com.example.demo.service.mapper.IOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderMapperImpl extends BaseMapper implements IOrderMapper {
    @Autowired
    private IOrderDetailRepository orderDetailRepo;
    @Autowired
    private IUserRepository userRepo;

    /* convert tu entity -->DTO*/
    @Override
    public OrderDTO convertToDTO(Orders input) {
        OrderDTO output = this.tranferData(input, OrderDTO.class);

        this.getIdFromRelationTable(input, output);

        return output;
    }

    private void getIdFromRelationTable(Orders input, OrderDTO output) {
        this.getIdFromUserTable(input, output);
        this.getIdFromOrderDetailTable(input, output);
    }

    //region get id from user table n order detail table
    private void getIdFromOrderDetailTable(Orders input, OrderDTO output) {
        output.setOrderDetailIds(new ArrayList<>());

        for (OrderDetail detail : input.getOrderDetailList()) {
            if (null == detail) continue;
            output.getOrderDetailIds().add(detail.getId());
        }
    }

    private void getIdFromUserTable(Orders input, OrderDTO output) {
        User user = input.getUser();
        if (null == user) return;

        output.setUserId(input.getUser().getId());
    }
    //endregion

    /* convert tu DTO --> Entity*/
    @Override
    public Orders convertToEntity(OrderDTO input) {
        Orders output = this.tranferData(input, Orders.class);

        output.setOrderDetailList(this.getDataById(input.getOrderDetailIds(), orderDetailRepo, OrderDetail.class));
        output.setUser(this.getDataById(input.getUserId(), userRepo, User.class));

        return output;
    }
}