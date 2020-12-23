package com.example.demo.util;

import com.example.demo.constant.ProductTypes;
import com.example.demo.service.dto.output.MasterDataOutPut;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeMasterData implements MasterDataUtil{

    @Override
    public List<MasterDataOutPut> getMasterDataOutPuts() {
        List<MasterDataOutPut> outPut= new ArrayList<>();

        for (ProductTypes productTypes : ProductTypes.values()){
            MasterDataOutPut masterDataOutPut = new MasterDataOutPut();
            masterDataOutPut.setName(productTypes.getName());
            masterDataOutPut.setCode(productTypes.name());
            outPut.add(masterDataOutPut);
        }
        return outPut;
    }
}
