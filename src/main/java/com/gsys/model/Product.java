package com.gsys.model;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PRODUCT")
public class Product {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "STOCK")
	private int stock;
	@Column(name = "EXPIRATION_DATE")
	private Date expirationDate;
	@Column(name = "PRODUCT_REFERENCE")
	private String productReference;
	@ManyToMany(mappedBy = "productsList")
	@JsonIgnore
	private List<ProductPayment> productsPayment;
	@Lob
	@Column(name = "PHOTO", length = Integer.MAX_VALUE)
	private String photo;
	@Column(name = "VALUE")
	private double value;
	@JsonIgnore
	@Column(name = "TOTAL_SOLD")
	private int totolSold;
	
	public Product () {}

	public Product(long id, String name, String description, int stock,Date expirationDate,String productReference, List<ProductPayment> productsPayment, String photo, double value, int totolSold) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.productsPayment = productsPayment;
		this.photo = photo;
		this.expirationDate = expirationDate;
		this.productReference = productReference;
		this.value = value;
		this.totolSold = totolSold;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<ProductPayment> getProductsPayment() {
		return productsPayment;
	}

	public void setProductsPayment(List<ProductPayment> productsPayment) {
		this.productsPayment = productsPayment;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getProductReference() {
		return productReference;
	}

	public void setProductReference(String productReference) {
		this.productReference = productReference;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getTotolSold() {
		return totolSold;
	}

	public void setTotolSold(int totolSold) {
		this.totolSold = totolSold;
	}
	
	
	
}
