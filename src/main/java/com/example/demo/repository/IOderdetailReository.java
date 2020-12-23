package com.example.demo.repository;

import com.example.demo.models.OderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface IOderdetailReository extends JpaRepository<OderDetail, Long> {

}
