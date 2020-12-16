package com.example.demo.repository;

import com.example.demo.models.OderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderDetailRepository extends JpaRepository<OderDetail, Long> {
}