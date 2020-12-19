package com.example.demo.service.mapper.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.dto.*;
import com.example.demo.service.mapper.BaseMapper;
import com.example.demo.service.mapper.IOrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class OrderDetailMapperImpl extends BaseMapper implements IOrderDetailMapper {
    @Autowired
    private IProductRepository productRepo;
    @Autowired
    private IOrderRepository orderRepo;

    /* convert tu entity -->DTO*/
    @Override
    public OrderDetailDTO convertToDTO(OderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = this.tranferData(orderDetail, OrderDetailDTO.class);

        displayConfig(orderDetailDTO);

        return orderDetailDTO;
    }

    @Override
    public List<OrderDetailDTO> convertToDTO(List<OderDetail> orderDetails) {
        List<OrderDetailDTO> list = new ArrayList<>();

        for (OderDetail oderDetail : orderDetails){
            list.add(this.convertToDTO(oderDetail));
        }

        return list;
    }

    //region cấu hình cách hiển thị order detail table
    private void displayConfig(OrderDetailDTO orderDetailDTO) {
        //config order
        orderDetailDTO.getOrders().setOderDetailList(new HashSet<>());
        UserDTO userDTO = orderDetailDTO.getOrders().getUser();
        if (null != userDTO) {
            for (RoleDTO roleDTO : userDTO.getRoles()) {
                roleDTO.setUsers(new HashSet<>());
            }
            userDTO.setOrdersList(new HashSet<>());
        }

        //config product
        ProductDTO productDTO = orderDetailDTO.getProducts();
        productDTO.setOderDetailList(new HashSet<>());
        ProductCategoryDTO productCategoryDTO = productDTO.getProductCategories();
        if (null != productCategoryDTO) {
            productCategoryDTO.setProductsList(new HashSet<>());
        }
    }
    //endregion

    /* convert tu DTO --> Entity*/
    @Override
    public OderDetail convertToEntity(OrderDetailDTO dto) {
        OderDetail entity = this.tranferData(dto, OderDetail.class);

        //get data from id
        entity.setProducts(this.getDataById(dto.getProduct_id(), productRepo, Products.class));
        entity.setOrders(this.getDataById(dto.getOrder_id(), orderRepo, Orders.class));

        return entity;
    }
}