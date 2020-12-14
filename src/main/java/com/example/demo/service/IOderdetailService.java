package com.example.demo.service;

import com.example.demo.service.dto.OderdetailDTO;

import java.util.Optional;

public interface IOderdetailService {
    OderdetailDTO save(OderdetailDTO oderdetailDTO);

    Optional<OderdetailDTO> findById(Long id);
}
