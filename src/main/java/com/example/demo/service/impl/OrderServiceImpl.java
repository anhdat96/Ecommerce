package com.example.demo.service.impl;

import com.example.demo.models.Orders;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.service.IOrderService;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements IOrderService {
    private final IOrderRepository orderRepo;
    private final OrderMapper orderMapper;
    private final IOrderDetailRepository orderDetailRepo;
    private final EntityManager em;

    public OrderDTO save(OrderDTO orderDTO) {
        Orders order = orderRepo.save(orderMapper.convertToEntity(orderDTO));

        return orderMapper.convertToDTO(order);
    }

    public List<OrderDTO> findAll() {
        List<OrderDTO> list = new ArrayList<>();

        for (Orders order : orderRepo.findAll()) {
            list.add(orderMapper.convertToDTO(order));
        }

        return list;
    }

    public OrderDTO findById(Long id) {
        Optional<Orders> opt = orderRepo.findById(id);
        if (!opt.isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        }

        return orderMapper.convertToDTO(opt.get());
    }

    public OrderDTO update(Long id, OrderDTO orderDTO) {
        this.findById(id);

        Orders order = orderMapper.convertToEntity(orderDTO);
        order.setOrderID(id);
        return orderMapper.convertToDTO(orderRepo.save(order));
    }

    public void deleteById(Long id) {
        orderDetailRepo.deleteAll(orderRepo.getOne(id).getOderDetailList());
        orderRepo.deleteById(id);
    }
}