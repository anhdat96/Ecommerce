package com.example.demo.repository;

import com.example.demo.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Products , Long> {

}
