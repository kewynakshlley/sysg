package com.gsys.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ProductPayment {
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference(value = "productPayment")
	private Administrator productSeller;
	@ManyToMany
	@JoinTable(
	  name = "payment_product", 
	  joinColumns = @JoinColumn(name = "payment_id"), 
	  inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> productsList = new ArrayList<>();
	@Column(name = "TOTAL_VALUE")
	private double totalValue;
	
	public ProductPayment() {
	}

	public ProductPayment(long id, Administrator productSeller, List<Product> productsList, double totalValue) {
		super();
		this.id = id;
		this.productsList = productsList;
		this.productSeller = productSeller;
		this.totalValue = totalValue;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}

	public Administrator getProductSeller() {
		return productSeller;
	}

	public void setProductSeller(Administrator productSeller) {
		this.productSeller = productSeller;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	
	

	
	
}
