package com.example.demo.service.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.repository.IOderdetailReository;
import com.example.demo.service.IOderdetailService;
import com.example.demo.service.dto.OderdetailDTO;
import com.example.demo.service.mapper.OderdetailMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OderdetailImpl implements IOderdetailService {

    private final IOderdetailReository iOderdetailReository;
    private final OderdetailMapperImpl oderdetailMapperImpl;
    @Override
    public OderdetailDTO save(OderdetailDTO oderdetailDTO) {
        OderDetail oderDetail = oderdetailMapperImpl.toEntity(oderdetailDTO);
        oderDetail = iOderdetailReository.save(oderDetail);
        return oderdetailMapperImpl.toDto(oderDetail);
    }

    @Override
    public Optional<OderdetailDTO> findById(Long id) {
        return iOderdetailReository.findById(id).map(oderdetailMapperImpl::toDto);
    }

    @Override
    public OderdetailDTO update(OderdetailDTO oderdetailDTO, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
