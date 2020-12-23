package com.example.demo.service;

import com.example.demo.service.dto.OderdetailDTO;
import com.example.demo.service.dto.OrdersDTO;

import java.util.Optional;

public interface IOderdetailService {
    OderdetailDTO save(OderdetailDTO oderdetailDTO);

    Optional<OderdetailDTO> findById(Long id);

    OderdetailDTO update(OderdetailDTO oderdetailDTO, Long id);

    void delete(Long id);
}
