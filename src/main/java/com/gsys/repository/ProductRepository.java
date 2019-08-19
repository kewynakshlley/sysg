package com.gsys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gsys.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query(value = " select * from product order by total_sold desc", nativeQuery = true)
	List<Product> findBestSellers();
	@Query(value = "select * from product where stock > 0", nativeQuery =true)
	List<Product> retrieveProductsInStock();

}
