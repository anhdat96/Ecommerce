package com.example.demo.service.mapper.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.dto.OrderDetailDTO;
import com.example.demo.service.mapper.BaseMapper;
import com.example.demo.service.mapper.IOrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDetailMapperImpl extends BaseMapper implements IOrderDetailMapper {
    @Autowired
    private IProductRepository productRepo;
    @Autowired
    private IOrderRepository orderRepo;

    /* convert tu entity -->DTO*/
    @Override
    public OrderDetailDTO convertToDTO(OderDetail input) {
        OrderDetailDTO output = this.tranferData(input, OrderDetailDTO.class);

        this.getIdFromRelationTable(input, output);

        return output;
    }

    private void getIdFromRelationTable(OderDetail input, OrderDetailDTO output) {
        this.getIdFromOrderTable(input,output);
        this.getIdFromProductTable(input, output);
    }

    //region get id from product table n order table
    private void getIdFromProductTable(OderDetail input, OrderDetailDTO output) {
        output.setProductId(input.getProducts().getProductID());
    }

    private void getIdFromOrderTable(OderDetail input, OrderDetailDTO output) {
        output.setOrderId(input.getOrders().getOrderID());
    }
    //endregion

    @Override
    public List<OrderDetailDTO> convertToDTO(List<OderDetail> orderDetails) {
        List<OrderDetailDTO> list = new ArrayList<>();

        for (OderDetail oderDetail : orderDetails) {
            list.add(this.convertToDTO(oderDetail));
        }

        return list;
    }

    /* convert tu DTO --> Entity*/
    @Override
    public OderDetail convertToEntity(OrderDetailDTO dto) {
        OderDetail entity = this.tranferData(dto, OderDetail.class);

        //get data from id
        entity.setProducts(this.getDataById(dto.getProductId(), productRepo, Products.class));
        entity.setOrders(this.getDataById(dto.getOrderId(), orderRepo, Orders.class));

        return entity;
    }
}