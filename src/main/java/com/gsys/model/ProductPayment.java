package com.gsys.model;

import java.util.List;
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
	private List<Product> productsList;
	
	public ProductPayment() {
	}

	public ProductPayment(long id, Administrator productSeller, List<Product> productsList) {
		super();
		this.id = id;
		this.productsList = productsList;
		this.productSeller = productSeller;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((productSeller == null) ? 0 : productSeller.hashCode());
		result = prime * result + ((productsList == null) ? 0 : productsList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductPayment other = (ProductPayment) obj;
		if (id != other.id)
			return false;
		if (productSeller == null) {
			if (other.productSeller != null)
				return false;
		} else if (!productSeller.equals(other.productSeller))
			return false;
		if (productsList == null) {
			if (other.productsList != null)
				return false;
		} else if (!productsList.equals(other.productsList))
			return false;
		return true;
	}
	
	

	
	
}
