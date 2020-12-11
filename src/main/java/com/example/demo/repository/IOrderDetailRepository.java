package com.example.demo.repository;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OderDetail, Long> {

}