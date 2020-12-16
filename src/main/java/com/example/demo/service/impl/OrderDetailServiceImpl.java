package com.example.demo.service.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.service.IOrderDetailService;
import com.example.demo.service.dto.OrderDetailDTO;
import com.example.demo.service.mapper.IOrderDetailMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements IOrderDetailService {
    private final IOrderDetailRepository orderDetailRepo;
    private final IOrderDetailMapper orderDetailMapper;


    public List<OrderDetailDTO> findAll() {
        Set<OrderDetailDTO> set = new HashSet<>();

        for (OderDetail entity : orderDetailRepo.findAll()) {
            set.add(orderDetailMapper.convertToDTO(entity));
        }

        return set.stream()
                .sorted(Comparator.comparing(OrderDetailDTO::getDetailID))
                .collect(Collectors.toList());
    }

    public OrderDetailDTO findById(Long id) {
        Optional<OderDetail> optionalOrderDetail = orderDetailRepo.findById(id);
        if (!optionalOrderDetail.isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        }

        return orderDetailMapper.convertToDTO(optionalOrderDetail.get());
    }

    public OrderDetailDTO save(OrderDetailDTO dto) {
        OderDetail entity = orderDetailRepo.save(orderDetailMapper.convertToEntity(dto));

        return orderDetailMapper.convertToDTO(entity);
    }

    public void deleteById(Long id) {
        orderDetailRepo.deleteById(id);
    }

    public OrderDetailDTO update(Long id, OrderDetailDTO dto) {
        findById(id);

        dto.setDetailID(id);
        return save(dto);
    }
}