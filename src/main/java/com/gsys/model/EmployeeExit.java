package com.gsys.model;

import java.util.Calendar;

import javax.persistence.Entity;
@Entity
public class EmployeeExit extends EmployeeCheckIn {

	public EmployeeExit() {
	}

	public EmployeeExit(long id, Calendar date, Employee employee) {
		super(id, date, employee);
	}

}
