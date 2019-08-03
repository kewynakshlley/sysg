package com.gsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gsys.core.dto.ProductDTO;
import com.gsys.core.dto.ProductPaymentDTO;
import com.gsys.exception.DataNotFoundException;
import com.gsys.model.Administrator;
import com.gsys.model.Product;
import com.gsys.model.ProductPayment;
import com.gsys.repository.AdministratorRepository;
import com.gsys.repository.ProductPaymentRepository;
import com.gsys.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private AdministratorRepository administratorRepository;
	@Autowired
	private ProductPaymentRepository productPaymentRepository;
	
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

	public Product getProduct(long productId) throws DataNotFoundException {
		Product productTemp = this.productRepository.getOne(productId);
		if(productTemp == null) throw new DataNotFoundException("Product not found.");
		return productTemp;
	}

	public ResponseEntity<?> createProduct(Product product) {
		Product createdProduct = this.productRepository.save(product);
		return new ResponseEntity<Product>(createdProduct, HttpStatus.OK);
	}

	public void deleteProduct(long productId) {
		this.productRepository.deleteById(productId);
	}

	public void buyProduct(ProductPaymentDTO productPaymentDTO) throws DataNotFoundException {
		ProductPayment productPayment = new ProductPayment();
		Administrator admTemp = administratorRepository.getOne(productPaymentDTO.getSellerId());
		if(admTemp == null) throw new DataNotFoundException("Seller not found.");
		productPayment.setProductSeller(admTemp);
		for(ProductDTO pdto: productPaymentDTO.getProducts()) {
			Product pd = productRepository.getOne(pdto.getProductId());
			pd.setStock(pd.getStock() - pdto.getAmount());
			productPayment.getProductsList().add(pd);
		}
		admTemp.getProductPayment().add(productPayment);
		this.administratorRepository.save(admTemp);
		this.productPaymentRepository.save(productPayment);
	}

	public List<ProductPayment> getProductSalesHistory() {
		return this.productPaymentRepository.findAll();
		
	}

}
