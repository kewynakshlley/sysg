package com.gsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsys.model.ProductPayment;
@Repository
public interface ProductPaymentRepository extends JpaRepository<ProductPayment, Long>{

}
