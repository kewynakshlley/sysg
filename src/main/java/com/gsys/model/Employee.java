package com.gsys.model;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Employee extends Person {
	@OneToMany(mappedBy = "employee")
	private List<EmployeeArrival> arrivalFrequency;
	@OneToMany(mappedBy = "employee")
	private List<EmployeeExit> exitFrequency;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_goal_id", referencedColumnName = "id")
	private WorkGoal workGoal;
	@Column(name = "NEXT_PAYMENT_DATE")
	private Date nextPaymentDate;
	public Employee() {
	}

	public Employee(long id, String name, String address, String cpf, String photo, String dateOfBirth,
			String firstName, String lastName, List<EmployeeArrival> arrivalFrequency,
			List<EmployeeExit> exitFrequency, WorkGoal workGoal, Date nextPaymentDate) {
		super(id, name, address, cpf, photo, dateOfBirth, firstName, lastName);
		this.arrivalFrequency = arrivalFrequency;
		this.exitFrequency = exitFrequency;
		this.workGoal = workGoal;
		this.nextPaymentDate = nextPaymentDate;
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

	public WorkGoal getWorkGoal() {
		return workGoal;
	}

	public void setWorkGoal(WorkGoal workGoal) {
		this.workGoal = workGoal;
	}

	public Date getNextPaymentDate() {
		return nextPaymentDate;
	}

	public void setNextPaymentDate(Date nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	
	
	

}
