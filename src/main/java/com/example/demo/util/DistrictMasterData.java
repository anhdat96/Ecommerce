package com.example.demo.util;

import com.example.demo.constant.Districts;
import com.example.demo.service.dto.output.MasterDataOutPut;

import java.util.ArrayList;
import java.util.List;

public class DistrictMasterData implements MasterDataUtil {

    @Override
    public List<MasterDataOutPut> getMasterDataOutPuts() {
        List<MasterDataOutPut> outPuts = new ArrayList<>();
        for (Districts districts : Districts.values()) {
            MasterDataOutPut masterDataOutPut = new MasterDataOutPut();
            masterDataOutPut.setCode(districts.name());
            masterDataOutPut.setName(districts.getName());
            outPuts.add(masterDataOutPut);
        }
        return outPuts;
    }
}
