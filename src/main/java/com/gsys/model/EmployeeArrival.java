package com.gsys.model;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class EmployeeArrival extends EmployeeCheckIn {

	public EmployeeArrival() {
	}

	public EmployeeArrival(long id, Date date, Employee employee) {
		super(id, date, employee);
	}

}
