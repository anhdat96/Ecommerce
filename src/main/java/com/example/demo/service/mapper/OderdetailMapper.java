package com.example.demo.service.mapper;

import com.example.demo.models.OderDetail;
import com.example.demo.service.dto.OderdetailDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OderdetailMapper {
    ModelMapper modelMapper = new ModelMapper();
    /* convert tu entity -->DTO*/

    public OderdetailDTO convertToDTO(OderDetail oderDetail) {
        OderdetailDTO oderdetailDTO = modelMapper.map(oderDetail, OderdetailDTO.class);
        return oderdetailDTO;
    }

    /* convert tu DTO --> Entity*/
    public OderDetail convertToEntity(OderdetailDTO oderdetailDTO) {
        OderDetail oderDetail = modelMapper.map(oderdetailDTO, OderDetail.class);
        return oderDetail;
    }
}
