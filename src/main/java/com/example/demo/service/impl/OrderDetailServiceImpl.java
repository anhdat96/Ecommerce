package com.example.demo.service.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IOrderDetailService;
import com.example.demo.service.dto.OrderDetailDTO;
import com.example.demo.service.mapper.OrderDetailMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements IOrderDetailService {
    private final IOrderDetailRepository orderDetailRepo;
    private final IOrderRepository orderRepo;
    private final IProductRepository productRepo;
    private final OrderDetailMapper orderDetailMapper;


    public Set<OrderDetailDTO> findAll(){
        Set<OrderDetailDTO> set = new HashSet<>();

        for (OderDetail entity : orderDetailRepo.findAll()){
            set.add(orderDetailMapper.convertToDTO(entity));
        }

        return set;
    }

    public OrderDetailDTO findById(Long id){
        Optional<OderDetail> optionalOrderDetail = orderDetailRepo.findById(id);
        if (!optionalOrderDetail.isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        }

        return orderDetailMapper.convertToDTO(optionalOrderDetail.get());
    }

    public OrderDetailDTO save(OrderDetailDTO dto){
        OderDetail entity = orderDetailRepo.save(orderDetailMapper.convertToEntity(dto));

        return orderDetailMapper.convertToDTO(entity);
    }
    public void deleteById(Long id)
    {
        /*OderDetail orderDetail = orderDetailRepo.findById(id).get();
        this.removeRelationTable(orderDetail);
        orderDetailRepo.deleteById(id);*/
        orderDetailRepo.deleteById(id);
    }

    /*private void removeRelationTable(OderDetail orderDetail) {
        this.removeRelationOrder(orderDetail);
        this.removeRelationProduct(orderDetail);
    }

    private void removeRelationProduct(OderDetail orderDetail) {
        Products product = orderDetail.getProducts();

        product.getOderDetailList().remove(orderDetail);
        productRepo.save(product);
    }

    private void removeRelationOrder(OderDetail orderDetail) {
        Orders order = orderDetail.getOrders();

        order.getOderDetailList().remove(orderDetail);
        orderRepo.save(order);
    }*/

    public OrderDetailDTO update(Long id, OrderDetailDTO dto) {
        findById(id);

        dto.setDetailID(id);
        return save(dto);
    }
}