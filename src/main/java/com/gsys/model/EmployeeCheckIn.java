package com.gsys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class EmployeeCheckIn {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "DATE")
	private Date date;
	@Column(name = "HOUR")
	private String hour;
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Employee employee;
	
	public EmployeeCheckIn() {}

	public EmployeeCheckIn(long id, Date date, String hour, Employee employee) {
		super();
		this.id = id;
		this.date = date;
		this.hour = hour;
		this.employee = employee;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

}
