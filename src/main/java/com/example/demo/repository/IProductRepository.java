package com.example.demo.repository;

import com.example.demo.models.Products;
import com.example.demo.repository.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*spring data repository for the Product*/
@Repository
public interface IProductRepository extends JpaRepository<Products , Long>, ProductRepositoryCustom {

    // only have JPA methods


}
