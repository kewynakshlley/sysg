package com.gsys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class WorkGoal {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "WORKING_TIME")
	private int workingTime;
	@Column(name = "PAID_GOAL")
	private int paidGoal;
	@JsonIgnore
	@OneToOne(mappedBy = "workGoal")
	private Employee employee;
	
	public WorkGoal() {}

	public WorkGoal(long id, int workingTime, int paidGoal, Employee employee) {
		super();
		this.id = id;
		this.workingTime = workingTime;
		this.paidGoal = paidGoal;
		this.employee = employee;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(int workingTime) {
		this.workingTime = workingTime;
	}

	public int getPaidGoal() {
		return paidGoal;
	}

	public void setPaidGoal(int paidGoal) {
		this.paidGoal = paidGoal;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
	
	

}
