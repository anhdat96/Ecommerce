package com.example.demo.service.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements IOrderDetailService {

    private final IOrderDetailRepository iOrderDetailRepository;

    public List<OderDetail> finAll(){
        return iOrderDetailRepository.findAll();
    }

    public Optional<OderDetail> findById(Long id){
        return iOrderDetailRepository.findById(id);
    }

    public OderDetail save(OderDetail oderDetail){
        return  iOrderDetailRepository.save(oderDetail);
    }
    public void deleteById(Long id)
    {
        iOrderDetailRepository.deleteById(id);
    }
}