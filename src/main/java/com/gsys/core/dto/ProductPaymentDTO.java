package com.gsys.core.dto;

import java.util.List;

public class ProductPaymentDTO {
	private long id;
	private long sellerId;
	private List<ProductDTO> products;
	private double totalValue;
	
	public ProductPaymentDTO() {
	}

	public ProductPaymentDTO(long id, long sellerId, List<ProductDTO> products, double totalValue) {
		super();
		this.id = id;
		this.sellerId = sellerId;
		this.products = products;
		this.totalValue = totalValue;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	


	
	
	
}
