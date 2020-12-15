package com.example.demo.service.mapper;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderDetailMapper extends BaseMapper {
    private final IProductRepository productRepo;
    private final IOrderRepository orderRepo;
    /* convert tu entity -->DTO*/

    public OrderDetailDTO convertToDTO(OderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = this.tranferData(orderDetail, OrderDetailDTO.class);

        //config order
        orderDetailDTO.getOrders().setOderDetailList(new HashSet<>());
        UserDTO userDTO = orderDetailDTO.getOrders().getUser();
        if (null!=userDTO){
            for(RoleDTO roleDTO : userDTO.getRoles()){
                roleDTO.setUsers(new HashSet<>());
            }
            userDTO.setOrdersList(new HashSet<>());
        }

        //config product
        ProductDTO productDTO = orderDetailDTO.getProducts();
        productDTO.setOderDetailList(new HashSet<>());
        ProductCategoriesDTO productCategoriesDTO = productDTO.getProductCategories();
        if (null!=productCategoriesDTO){
            productCategoriesDTO.setProductsList(new HashSet<>());
        }


        return orderDetailDTO;
    }

    /* convert tu DTO --> Entity*/
    public OderDetail convertToEntity(OrderDetailDTO dto) {
        OderDetail entity = this.tranferData(dto, OderDetail.class);

        //confirg field
        entity.setProducts(this.getRelationRecordProduct(dto.getProduct_id()));
        entity.setOrders(this.getRelationRecordOrder(dto.getOrder_id()));

        return entity;
    }

    private Orders getRelationRecordOrder(Long id) {
        if (null == id) return null;
        Optional<Orders> opt = orderRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }

    private Products getRelationRecordProduct(Long id) {
        if (null == id) return null;
        Optional<Products> opt = productRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }
}