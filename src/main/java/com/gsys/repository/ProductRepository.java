package com.gsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsys.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
