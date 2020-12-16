package com.example.demo.controller;

import com.example.demo.service.dto.output.MasterDataOutPut;
import com.example.demo.util.MasterDataUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*API load types of product and load type of districts will be using for search*/
@RestController
public class MasterDataAPI {
    @GetMapping("/masterdata/{code}")
    public List<MasterDataOutPut> getMasterData(@PathVariable("code") String code){
        return MasterDataUtil.of(code).getMasterDataOutPuts();
    }
}
