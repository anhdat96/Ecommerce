package com.example.demo.service.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.service.IOrderDetailService;
import com.example.demo.service.dto.OrderDetailDTO;
import com.example.demo.service.mapper.IOrderDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepo;
    @Autowired
    private IOrderDetailMapper orderDetailMapper;



    @Override
    public OrderDetailDTO save(OrderDetailDTO dto) {
        OderDetail entity = orderDetailRepo.save(orderDetailMapper.convertToEntity(dto));

        return orderDetailMapper.convertToDTO(entity);
    }

    @Override
    public List<OrderDetailDTO> findAll() {
        Set<OrderDetailDTO> set = new HashSet<>();

        for (OderDetail entity : orderDetailRepo.findAll()) {
            set.add(orderDetailMapper.convertToDTO(entity));
        }

        return set.stream()
                .sorted(Comparator.comparing(OrderDetailDTO::getDetailID))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO findById(Long id) {
        Optional<OderDetail> optionalOrderDetail = orderDetailRepo.findById(id);
        if (!optionalOrderDetail.isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        }

        return orderDetailMapper.convertToDTO(optionalOrderDetail.get());
    }

    @Override
    public List<OrderDetailDTO> findByDetailName(String name) {
        return null;
    }

    @Override
    public OrderDetailDTO update(Long id, OrderDetailDTO dto) {
        findById(id);

        dto.setDetailID(id);
        return save(dto);
    }

    @Override
    public void deleteById(Long id) {
        orderDetailRepo.deleteById(id);
    }
}