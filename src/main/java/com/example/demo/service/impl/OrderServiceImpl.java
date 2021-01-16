package com.example.demo.service.impl;

import com.example.demo.models.Orders;
import com.example.demo.models.User;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IOrderService;
import com.example.demo.service.dto.OrdersDTO;
import com.example.demo.service.mapper.IOrdersMapper;
import com.example.demo.service.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final IOrdersMapper iOrdersMapper;
    private final IUserMapper iUserMapper;

    @Autowired
    IOrderRepository iOrderRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public OrdersDTO save(OrdersDTO ordersDTO) {
        log.info("Request to save orders:{}", ordersDTO);
        Optional<User> user = userRepository.findById(ordersDTO.getOrderUserID());
        if (!user.isPresent()) {
            log.info("can not save because this user id : " + ordersDTO.getOrderUserID() + " is not exist in table User ");
            return null;
        }
        Orders orders = iOrdersMapper.toEntity(ordersDTO);
        orders.setUser(user.get());
        orders = iOrderRepository.save(orders);
        return iOrdersMapper.toDto(orders);
    }

    @Override
    public Optional<OrdersDTO> findById(Long id) {
        log.info("Request to get one orders");
        return iOrderRepository.findById(id).map(iOrdersMapper::toDto);
    }

    @Override
    public OrdersDTO update(OrdersDTO ordersDTO, Long id) {
        log.info("Request to update one orders:{}", ordersDTO);
        Orders orders = iOrderRepository.findById(id).get();
        if (orders != null) {
            Optional<User> user = userRepository.findById(ordersDTO.getOrderUserID());
            if (user.isPresent()) {
                orders = iOrdersMapper.toEntity(ordersDTO);
                orders.setUser(user.get());
                orders = iOrderRepository.save(orders);
                return iOrdersMapper.toDto(orders);
            }
            log.info("can not save because this user id " + ordersDTO.getOrderUserID() + " is not exist in table user ");
            return null;
        }
        log.info("can not update because this id: " + id + " is not exist in table orders ");
        return null;
    }

    @Override
    public void delete(Long id) {
        log.info("request to delete orders: ", id);
        iOrderRepository.deleteById(id);

    }
}
