package com.gsys.model;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EmployeeCheckIn {
	@Id
	@GeneratedValue
	private long id;
	@JsonIgnore
	@Column(name = "DATE_TIME")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@CreatedDate
	private Calendar dateTime;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID")
	@JsonIgnore
	private Employee employee = new Employee();
	
	public EmployeeCheckIn() {}

	public EmployeeCheckIn(long id, Calendar dateTime, Employee employee) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.employee = employee;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
	}


	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

}
