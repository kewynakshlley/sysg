package com.gsys.model;

import java.util.Calendar;
import javax.persistence.Entity;

@Entity
public class EmployeeArrival extends EmployeeCheckIn {

	public EmployeeArrival() {
	}

	public EmployeeArrival(long id, Calendar date, Employee employee) {
		super(id, date, employee);
	}

}
