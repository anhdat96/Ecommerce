package com.example.demo.service.impl;

import com.example.demo.models.OrderDetail;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.service.IOrderDetailService;
import com.example.demo.service.dto.OrderDetailDTO;
import com.example.demo.service.mapper.IOrderDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
@Slf4j
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepo;
    @Autowired
    private IOrderDetailMapper orderDetailMapper;


    @Override
    public OrderDetailDTO save(OrderDetailDTO dto) {
        OrderDetail entity = orderDetailRepo.save(orderDetailMapper.convertToEntity(dto));

        return orderDetailMapper.convertToDTO(entity);
    }

    @Override
    public List<OrderDetailDTO> findAll(Integer page, Integer size){
        List<OrderDetailDTO> list = new ArrayList<>();
        if (page < 1) {
            throw new IllegalArgumentException("Page must be more than zero!");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Size must be more than zero!");
        }
        PageRequest pageRQ = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "detailID"));
        for (OrderDetail entity : orderDetailRepo.findAll(pageRQ)) {
            list.add(orderDetailMapper.convertToDTO(entity));
        }

        return list;
    }

    @Override
    public OrderDetailDTO findById(Long id) {
        Optional<OrderDetail> opt = orderDetailRepo.findById(id);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException("ID " + id + " is not exist");
        }

        return orderDetailMapper.convertToDTO(opt.get());
    }

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<OrderDetailDTO> findByDetailName(final String fieldName,String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderDetail> cq = cb.createQuery(OrderDetail.class);
        // tạo query
        Root<OrderDetail> root = cq.from(OrderDetail.class);
        cq.select(root)
                .where(cb.like(
                        root.get(fieldName), "%" + name + "%"
                ));

        return orderDetailMapper.convertToDTO(em.createQuery(cq).getResultList());
    }

    @Override
    public OrderDetailDTO update(Long id, OrderDetailDTO dto) {
        findById(id);

        dto.setId(id);
        return save(dto);
    }

    @Override
    public void deleteById(Long id) {
        orderDetailRepo.deleteById(id);
    }
}