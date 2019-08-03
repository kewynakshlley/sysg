package com.gsys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="EMPLOYEE_FREQUENCY")
public class EmployeeFrequency {
	@Id
	@GeneratedValue
	private long id;
	@Temporal(TemporalType.DATE)
	@Column(name = "FREQUENCY_DATE")
	private Date date;
	@Column(name = "ARRIVAL_HOUR")
	private String arrivalHour;
	@Column(name = "EXIT_HOUR")
	private String exitHour;
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Employee employee;
	
	public EmployeeFrequency() {
		
	}

	public EmployeeFrequency(long id, Date date, String arrivalHour, String exitHour, Employee employee) {
		super();
		this.id = id;
		this.date = date;
		this.arrivalHour = arrivalHour;
		this.exitHour = exitHour;
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

	public String getArrivalHour() {
		return arrivalHour;
	}

	public void setArrivalHour(String arrivalHour) {
		this.arrivalHour = arrivalHour;
	}

	public String getExitHour() {
		return exitHour;
	}

	public void setExitHour(String exitHour) {
		this.exitHour = exitHour;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalHour == null) ? 0 : arrivalHour.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((exitHour == null) ? 0 : exitHour.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeFrequency other = (EmployeeFrequency) obj;
		if (arrivalHour == null) {
			if (other.arrivalHour != null)
				return false;
		} else if (!arrivalHour.equals(other.arrivalHour))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (exitHour == null) {
			if (other.exitHour != null)
				return false;
		} else if (!exitHour.equals(other.exitHour))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
