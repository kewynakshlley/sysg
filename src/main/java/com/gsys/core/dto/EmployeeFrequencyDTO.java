package com.gsys.core.dto;

import java.util.Date;

public class EmployeeFrequencyDTO {
	private long id;
	private Date date;
	private String hour;
	private long employee;
	
	public EmployeeFrequencyDTO() {
	}
	
	public EmployeeFrequencyDTO(long id, Date date, String hour, long employee) {
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
	
	public long getEmployee() {
		return employee;
	}
	public void setEmployee(long employee) {
		this.employee = employee;
	}	

}
