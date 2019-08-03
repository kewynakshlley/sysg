package com.gsys.core.dto;

public class ProductDTO {
	private long productId;
	private int amount;

	public ProductDTO() {
	}

	public ProductDTO(long productId, int amount) {
		super();
		this.productId = productId;
		this.amount = amount;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (amount ^ (amount >>> 32));
		result = prime * result + (int) (productId ^ (productId >>> 32));
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
		ProductDTO other = (ProductDTO) obj;
		if (amount != other.amount)
			return false;
		if (productId != other.productId)
			return false;
		return true;
	}
	
	

}
