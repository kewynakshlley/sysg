package com.gsys.model;

import java.util.Date;

import javax.persistence.Entity;
@Entity
public class EmployeeExit extends EmployeeCheckIn {

	public EmployeeExit() {
	}

	public EmployeeExit(long id, Date date, String hour, Employee employee) {
		super(id, date, hour, employee);
	}

}
