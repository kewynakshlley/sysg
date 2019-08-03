package com.gsys.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Administrator {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "login")
	private String login;
	@Column(name = "password")
	private String password;
	@OneToMany(mappedBy = "productSeller")
	@JsonManagedReference(value = "productPayment")
	private List<ProductPayment> productPayment;
	
	public Administrator() {}

	public Administrator(long id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ProductPayment> getProductPayment() {
		return productPayment;
	}

	public void setProductPayment(List<ProductPayment> productPayment) {
		this.productPayment = productPayment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((productPayment == null) ? 0 : productPayment.hashCode());
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
		Administrator other = (Administrator) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (productPayment == null) {
			if (other.productPayment != null)
				return false;
		} else if (!productPayment.equals(other.productPayment))
			return false;
		return true;
	}

	
	
	
	
	
	
	
	
}
