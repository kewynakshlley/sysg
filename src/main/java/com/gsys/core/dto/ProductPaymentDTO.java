package com.gsys.core.dto;

import java.util.List;

public class ProductPaymentDTO {
	private long id;
	private long sellerId;
	private List<ProductDTO> products;
	
	public ProductPaymentDTO() {
	}

	public ProductPaymentDTO(long id, long sellerId, List<ProductDTO> products) {
		super();
		this.id = id;
		this.sellerId = sellerId;
		this.products = products;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + (int) (sellerId ^ (sellerId >>> 32));
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
		ProductPaymentDTO other = (ProductPaymentDTO) obj;
		if (id != other.id)
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (sellerId != other.sellerId)
			return false;
		return true;
	}
	
	


	
	
	
}
