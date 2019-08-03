package com.gsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsys.core.dto.ProductPaymentDTO;
import com.gsys.exception.DataAlreadyExistsException;
import com.gsys.exception.DataNotFoundException;
import com.gsys.model.Product;
import com.gsys.model.ProductPayment;
import com.gsys.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/products")
	public List<Product> getAllProducts() {
		return this.productService.getAllProducts();
	}

	@GetMapping(path = "/products/{productId}")
	public Product getProduct(@PathVariable long productId) throws DataNotFoundException {
		return this.productService.getProduct(productId);
	}

	@PostMapping(path = "/products/new")
	public ResponseEntity<?> createProduct(@RequestBody Product product) throws DataAlreadyExistsException {
		return this.productService.createProduct(product);
	}

	@DeleteMapping(path = "/products/{productId}")
	public void deleteProduct(@PathVariable long productId) {
		productService.deleteProduct(productId);
	}
	
	@PostMapping(path = "/products/buy")
	public void buyProduct(@RequestBody ProductPaymentDTO productPaymentDTO) throws DataNotFoundException {
		productService.buyProduct(productPaymentDTO);
	}
	
	@GetMapping(path = "/products/history-sales")
	public List<ProductPayment> getProductSalesHistory() {
		return productService.getProductSalesHistory();
	}
	

}
