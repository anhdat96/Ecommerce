package com.example.demo.util;

import com.example.demo.service.dto.output.MasterDataOutPut;

import java.util.List;

public interface MasterDataUtil {
    static MasterDataUtil of(String code) {
        if (code.equals("district")) {
            return new DistrictMasterData();
        } else if (code.equals("productsType")) {
            return new ProductTypeMasterData();
        }
        return null;
    }

    List<MasterDataOutPut> getMasterDataOutPuts();


}
