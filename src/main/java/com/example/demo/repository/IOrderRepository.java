package com.example.demo.repository;

import com.example.demo.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Orders, Long> {

}