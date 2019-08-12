package com.gsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gsys.core.dto.ProductDTO;
import com.gsys.core.dto.ProductPaymentDTO;
import com.gsys.core.util.NotificationUtil;
import com.gsys.exception.DataNotFoundException;
import com.gsys.model.Administrator;
import com.gsys.model.Notification;
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
	@Autowired
	private NotificationService notificationService;
	
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
		generateProductNotification(NotificationUtil.PRODUCT_REGISTRATION, product.getName(), 
				NotificationUtil.REGISTRATION_CATEGORY);
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
		productPayment.setTotalValue(productPaymentDTO.getTotalValue());
		for(ProductDTO pdto: productPaymentDTO.getProducts()) {
			Product pd = productRepository.getOne(pdto.getProductId());
			pd.setStock(pd.getStock() - pdto.getAmount());
			productPayment.getProductsList().add(pd);
		}
		admTemp.getProductPayment().add(productPayment);
		
		generateProductNotification(NotificationUtil.PRODUCT_SALE, productPaymentDTO.getProducts().size()+" produtos vendidos",
				NotificationUtil.SALE_CATEGORY);
		this.administratorRepository.save(admTemp);
		this.productPaymentRepository.save(productPayment);
	}

	public List<ProductPayment> getProductSalesHistory() {
		return this.productPaymentRepository.findAll();
		
	}

	public void editProduct(Product product) {
		this.productRepository.save(product);
		
	}
	
	private void generateProductNotification(String title, String description, String category) {
		Notification nf = new Notification();
		nf.setTitle(title);
		nf.setDescription(description);
		nf.setCategory(category);
		notificationService.saveNotification(nf);
	}
	

}
