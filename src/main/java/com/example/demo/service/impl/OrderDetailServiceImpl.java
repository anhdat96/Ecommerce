package com.example.demo.service.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.service.IOrderDetailService;
import com.example.demo.service.dto.OrderDetailDTO;
import com.example.demo.service.mapper.IOrderDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
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

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<OrderDetailDTO> findByDetailName(final String fieldName,String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OderDetail> cq = cb.createQuery(OderDetail.class);
        // tạo query
        Root<OderDetail> root = cq.from(OderDetail.class);
        cq.select(root)
                .where(cb.like(
                        root.get(fieldName), "%" + name + "%"
                ));

        return orderDetailMapper.convertToDTO(em.createQuery(cq).getResultList());
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