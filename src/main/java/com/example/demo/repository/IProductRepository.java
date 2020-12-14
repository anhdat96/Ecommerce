package com.example.demo.repository;

import com.example.demo.models.Products;
import com.example.demo.repository.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*spring data repository for the Product*/



@Repository
public interface IProductRepository extends JpaRepository<Products , Long>, ProductRepositoryCustom {

    // only have JPA methods


}
