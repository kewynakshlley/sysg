package com.gsys.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Employee extends Person {
	@OneToMany(mappedBy = "employee")
	private List<EmployeeArrival> arrivalFrequency;
	@OneToMany(mappedBy = "employee")
	private List<EmployeeExit> exitFrequency;

	public Employee() {
	}

	public Employee(long id, String name, String address, String cpf, String photo, String dateOfBirth,
			String firstName, String lastName, List<EmployeeArrival> arrivalFrequency,
			List<EmployeeExit> exitFrequency) {
		super(id, name, address, cpf, photo, dateOfBirth, firstName, lastName);
		this.arrivalFrequency = arrivalFrequency;
		this.exitFrequency = exitFrequency;
	}

	public List<EmployeeArrival> getArrivalFrequency() {
		return arrivalFrequency;
	}

	public void setArrivalFrequency(List<EmployeeArrival> arrivalFrequency) {
		this.arrivalFrequency = arrivalFrequency;
	}

	public List<EmployeeExit> getExitFrequency() {
		return exitFrequency;
	}

	public void setExitFrequency(List<EmployeeExit> exitFrequency) {
		this.exitFrequency = exitFrequency;
	}

}
