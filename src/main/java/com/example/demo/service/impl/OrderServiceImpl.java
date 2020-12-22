package com.example.demo.service.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.models.User;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IOrderService;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.mapper.IOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderRepository orderRepo;
    @Autowired
    private IOrderMapper orderMapper;
    @Autowired
    private IOrderDetailRepository orderDetailRepo;
    @Autowired
    private IUserRepository userRepo;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Orders order = orderRepo.save(orderMapper.convertToEntity(orderDTO));

        this.updateRelationTable(order);

        return orderMapper.convertToDTO(order);
    }

    //region inner convertToEntity methods
    private void updateRelationTable(Orders order) {
        this.updateOrderDetailTable(order);
        this.updateUserTable(order);
    }

    //region inner updateRelationTable methods
    private void updateUserTable(Orders order) {
        User user = order.getUser();
        if (null != user) {
            user.getOrdersList().add(order);
            userRepo.save(user);
        }
    }

    private void updateOrderDetailTable(Orders order) {
        for (OderDetail orderDetail : order.getOderDetailList()) {
            if (orderDetail != null) {
                orderDetail.setOrders(order);
                orderDetailRepo.save(orderDetail);
            }
        }
    }
    //endregion
    //endregion
    @Override
    public List<OrderDTO> findAll() {
        Set<OrderDTO> set = new HashSet<>();

        for (Orders order : orderRepo.findAll()) {
            set.add(orderMapper.convertToDTO(order));
        }
        // phần này cần thay bằng hql
        return set.stream()
                .sorted(Comparator.comparing(OrderDTO::getOrderID))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(Long id) {
        Optional<Orders> opt = orderRepo.findById(id);
        if (!opt.isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        }

        return orderMapper.convertToDTO(opt.get());
    }

    @Override
    public OrderDTO update(Long id, OrderDTO orderDTO) {
        this.findById(id);

        Orders order = orderMapper.convertToEntity(orderDTO);
        order.setOrderID(id);
        this.updateRelationTable(order);
        return orderMapper.convertToDTO(orderRepo.save(order));
    }

    @Override
    public void deleteById(Long id) {
        orderDetailRepo.deleteAll(orderRepo.getOne(id).getOderDetailList());
        orderRepo.deleteById(id);
    }
}