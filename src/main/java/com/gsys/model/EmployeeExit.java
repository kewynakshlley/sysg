package com.gsys.model;

import java.util.Date;

import javax.persistence.Entity;
@Entity
public class EmployeeExit extends EmployeeCheckIn {

	public EmployeeExit() {
	}

	public EmployeeExit(long id, Date date, Employee employee) {
		super(id, date, employee);
	}

}
