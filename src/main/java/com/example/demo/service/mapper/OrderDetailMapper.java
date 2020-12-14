package com.example.demo.service.mapper;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.dto.OrderDetailDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailMapper {
    private final IProductRepository productRepo;
    private final IOrderRepository orderRepo;
    ModelMapper modelMapper = new ModelMapper();
    /* convert tu entity -->DTO*/

    public OrderDetailDTO convertToDTO(OderDetail orderDetail){
        OrderDetailDTO dto = modelMapper.map(orderDetail,OrderDetailDTO.class);

        return dto;
    }

    /* convert tu DTO --> Entity*/
    public OderDetail convertToEntity(OrderDetailDTO dto){
        OderDetail entity = modelMapper.map(dto,OderDetail.class);

        //confirg field
        entity.setProducts(this.getRelationRecordProduct(dto.getProduct_id()));
        entity.setOrders(this.getRelationRecordOrder(dto.getOrder_id()));

        return entity;
    }

    private Orders getRelationRecordOrder(Long id) {
        if (null==id) return null;
        Optional<Orders> opt = orderRepo.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }else {
            return null;
        }
    }

    private Products getRelationRecordProduct(Long id) {
        if (null==id) return null;
        Optional<Products> opt = productRepo.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }else {
            return null;
        }
    }
}