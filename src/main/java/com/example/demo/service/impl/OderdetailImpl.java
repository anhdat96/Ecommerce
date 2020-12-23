package com.example.demo.service.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.repository.IOderdetailReository;
import com.example.demo.service.IOderdetailService;
import com.example.demo.service.dto.OderdetailDTO;
import com.example.demo.service.mapper.IOderDetailMapper;
import com.example.demo.service.mapper.OderdetailMapperImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OderdetailImpl implements IOderdetailService {

    private final Logger log = LoggerFactory.getLogger(OderdetailImpl.class);

    private final IOderdetailReository iOderdetailReository;
    private final IOderDetailMapper iOderDetailMapper;
    @Override
    public OderdetailDTO save(OderdetailDTO oderdetailDTO) {
        log.info("Request to save oderdetail:{}", oderdetailDTO);
        OderDetail oderDetail = iOderDetailMapper.toEntity(oderdetailDTO);
        oderDetail = iOderdetailReository.save(oderDetail);
        return iOderDetailMapper.toDto(oderDetail);
    }

    @Override
    public Optional<OderdetailDTO> findById(Long id) {
        log.info("Request to get one oderdetail");
        return iOderdetailReository.findById(id).map(iOderDetailMapper::toDto);
    }

    @Override
    public OderdetailDTO update(OderdetailDTO oderdetailDTO, Long id) {
        log.info("Request to update one oderdetail:{}", oderdetailDTO);
        OderDetail oderDetail = iOderdetailReository.findById(id).get();
        if(oderDetail == null){
            log.info("can not save because this user id " + id + " is not exist in table Oderdetail ");
            return null;
        }
        oderDetail = iOderDetailMapper.toEntity(oderdetailDTO);
        oderDetail = iOderdetailReository.save(oderDetail);
        return iOderDetailMapper.toDto(oderDetail);
    }

    @Override
    public void delete(Long id) {
        log.info("request to delete orders: ", id);
        iOderdetailReository.deleteById(id);

    }
}
