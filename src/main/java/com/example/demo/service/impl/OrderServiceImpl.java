package com.example.demo.service.impl;

import com.example.demo.models.Orders;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.service.IOrderService;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.mapper.impl.OrderMapperImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements IOrderService {
    private final IOrderRepository orderRepo;
    private final OrderMapperImpl orderMapperImpl;
    private final IOrderDetailRepository orderDetailRepo;
    private final EntityManager em;

    public OrderDTO save(OrderDTO orderDTO) {
        Orders order = orderRepo.save(orderMapperImpl.convertToEntity(orderDTO));

        return orderMapperImpl.convertToDTO(order);
    }

    public List<OrderDTO> findAll() {
        Set<OrderDTO> set = new HashSet<>();

        for (Orders order : orderRepo.findAll()) {
            set.add(orderMapperImpl.convertToDTO(order));
        }

        return set.stream()
                .sorted(Comparator.comparing(OrderDTO::getOrderID))
                .collect(Collectors.toList());
    }

    public OrderDTO findById(Long id) {
        Optional<Orders> opt = orderRepo.findById(id);
        if (!opt.isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        }

        return orderMapperImpl.convertToDTO(opt.get());
    }

    public OrderDTO update(Long id, OrderDTO orderDTO) {
        this.findById(id);

        Orders order = orderMapperImpl.convertToEntity(orderDTO);
        order.setOrderID(id);
        return orderMapperImpl.convertToDTO(orderRepo.save(order));
    }

    public void deleteById(Long id) {
        orderDetailRepo.deleteAll(orderRepo.getOne(id).getOderDetailList());
        orderRepo.deleteById(id);
    }
}