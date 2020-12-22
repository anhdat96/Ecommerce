package com.example.demo.service.mapper;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import com.example.demo.service.dto.OderdetailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OderdetailMapperImpl implements IOderDetailMapper {
    private final Logger log = LoggerFactory.getLogger(OderdetailMapperImpl.class);
    @Override
    public OderDetail toEntity(OderdetailDTO oderdetailDTO) {
        if (oderdetailDTO == null) {
            return null;
        }
        try {
            OderDetail oderDetail = new OderDetail();
            oderDetail.setDetailID(oderdetailDTO.getDetailID());
            oderDetail.setDetailOder(oderdetailDTO.getDetailOder());
            oderDetail.setDetailName(oderdetailDTO.getDetailName());
            oderDetail.setDetailPrice(oderdetailDTO.getDetailPrice());
            oderDetail.setQuantity(oderdetailDTO.getQuantity());
            if(oderdetailDTO.getProductId() != null && oderdetailDTO.getOrdersId() != null){
                Products products = new Products();
                products.setProductID(oderdetailDTO.getProductId());
                oderDetail.setProducts(products);
                Orders orders = new Orders();
                orders.setOrderID(oderdetailDTO.getOrdersId());
                return oderDetail;
            }
        }catch (Exception e){
             e.printStackTrace();
             log.info("id of order or id of product is not exit !");

        }
        return null;
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
        oderdetailDTO.setQuantity(oderDetail.getQuantity());
        oderdetailDTO.setOrdersId(oderDetail.getProducts().getProductID());
        oderdetailDTO.setOrdersId(oderDetail.getOrders().getOrderID());

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
