package com.example.demo.controller;

import com.example.demo.service.IOderdetailService;
import com.example.demo.service.dto.OderdetailDTO;
import com.example.demo.service.dto.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/oder-detail")
public class OderDetailController {

    @Autowired
    IOderdetailService iOderdetailService;

    @PostMapping(value = "/create")
    public OderdetailDTO create(@RequestBody OderdetailDTO oderdetailDTO) {
        return iOderdetailService.save(oderdetailDTO);
    }


    @GetMapping("/get-one-oderdetail/{id}")
    public OderdetailDTO findOne(@PathVariable Long id) {
        return iOderdetailService.findById(id).get();
    }

    @PutMapping("/update-oderdetail/{id}")
    public OderdetailDTO update(@RequestBody OderdetailDTO oderdetailDTO, @PathVariable Long id) {
        return  iOderdetailService.update(oderdetailDTO, id);
    }

    @DeleteMapping(value = "/delete-oderdetail/{id}")
    public void delete(@PathVariable Long id) {
        iOderdetailService.delete(id);
    }



}
