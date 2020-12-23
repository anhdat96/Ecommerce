package com.example.demo.service.mapper;

import com.example.demo.models.OderDetail;
import com.example.demo.service.dto.OderdetailDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface IOderDetailMapper extends EntityMapper<OderdetailDTO , OderDetail>{

}
