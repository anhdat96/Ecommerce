package com.example.demo.service.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.repository.IOderdetailReository;
import com.example.demo.service.IOderdetailService;
import com.example.demo.service.dto.OderdetailDTO;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.mapper.OderdetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OderdetailImpl implements IOderdetailService {

    private final IOderdetailReository iOderdetailReository;
    private final OderdetailMapper oderdetailMapper;
    @Override
    public OderdetailDTO save(OderdetailDTO oderdetailDTO) {
        OderDetail oderDetail = oderdetailMapper.convertToEntity(oderdetailDTO);
        oderDetail = iOderdetailReository.save(oderDetail);
        return oderdetailMapper.convertToDTO(oderDetail);
    }

    @Override
    public Optional<OderdetailDTO> findById(Long id) {
        return iOderdetailReository.findById(id).map(oderdetailMapper::convertToDTO);
    }
}
