package com.example.demo.service.mapper;

import com.example.demo.models.OderDetail;
import com.example.demo.service.dto.OderdetailDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OderdetailMapperImpl implements IOderDetailMapper {
    @Override
    public OderDetail toEntity(OderdetailDTO oderdetailDTO) {
        if (oderdetailDTO == null) {
            return null;
        }
        OderDetail oderDetail = new OderDetail();
        oderDetail.setDetailID(oderdetailDTO.getDetailID());
        oderDetail.setDetailOder(oderdetailDTO.getDetailOder());
        oderDetail.setDetailName(oderdetailDTO.getDetailName());
        oderDetail.setDetailPrice(oderdetailDTO.getDetailPrice());
        oderDetail.setDetailProductID(oderdetailDTO.getDetailProductID());
        oderDetail.setQuantity(oderdetailDTO.getQuantity());
        oderDetail.setProducts(oderdetailDTO.getProducts());

        return oderDetail;
    }

    @Override
    public OderdetailDTO toDto(OderDetail oderDetail) {
        if (oderDetail == null) {
            return null;
        }
        OderdetailDTO oderdetailDTO = new OderdetailDTO();
        oderdetailDTO.setDetailID(oderDetail.getDetailID());
        oderdetailDTO.setDetailOder(oderDetail.getDetailOder());
        oderdetailDTO.setDetailName(oderDetail.getDetailName());
        oderdetailDTO.setDetailPrice(oderDetail.getDetailPrice());
        oderdetailDTO.setDetailProductID(oderDetail.getDetailProductID());
        oderdetailDTO.setQuantity(oderDetail.getQuantity());
        oderdetailDTO.setProducts(oderDetail.getProducts());

        return oderdetailDTO;
    }

    @Override
    public List<OderDetail> toEntity(List<OderdetailDTO> dtoList) {
        return null;
    }

    @Override
    public List<OderdetailDTO> toDto(List<OderDetail> entityList) {
        return null;
    }


}
