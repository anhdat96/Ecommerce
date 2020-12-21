package com.example.demo.repository;

import com.example.demo.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*spring data repository for the Product*/



public interface IProductRepository extends JpaRepository<Products, Long> {

    // only have JPA methods


}
