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
	private List<ProductPayment> productsPayment;
	@Lob
	@Column(name = "PHOTO")
	private String photo;
	@Column(name = "VALUE")
	private String value;
	
	public Product () {}

	public Product(long id, String name, String description, int stock,Date expirationDate,String productReference, List<ProductPayment> productsPayment, String photo, String value) {
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((productReference == null) ? 0 : productReference.hashCode());
		result = prime * result + ((productsPayment == null) ? 0 : productsPayment.hashCode());
		result = prime * result + stock;
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
		Product other = (Product) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (productReference == null) {
			if (other.productReference != null)
				return false;
		} else if (!productReference.equals(other.productReference))
			return false;
		if (productsPayment == null) {
			if (other.productsPayment != null)
				return false;
		} else if (!productsPayment.equals(other.productsPayment))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

	
}
