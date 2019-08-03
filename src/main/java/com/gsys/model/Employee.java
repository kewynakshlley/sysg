package com.gsys.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Employee extends Person {
	@OneToMany(mappedBy = "employee")
	private List<EmployeeFrequency> employeeFrequencyList;

	public Employee() {
	}

	public Employee(long id, String name, String address, String cpf, String photo, String dateOfBirth, String gender,
			List<EmployeeFrequency> employeeFrequencyList) {
		super(id, name, address, cpf, photo, dateOfBirth, gender);
		this.employeeFrequencyList = employeeFrequencyList;
	}

	public List<EmployeeFrequency> getEmployeeFrequencyList() {
		return employeeFrequencyList;
	}

	public void setEmployeeFrequencyList(List<EmployeeFrequency> employeeFrequencyList) {
		this.employeeFrequencyList = employeeFrequencyList;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((employeeFrequencyList == null) ? 0 : employeeFrequencyList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeFrequencyList == null) {
			if (other.employeeFrequencyList != null)
				return false;
		} else if (!employeeFrequencyList.equals(other.employeeFrequencyList))
			return false;
		return true;
	}
	
	

}
