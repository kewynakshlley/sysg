package com.gsys.core.dto;

import java.util.Date;

public class EmployeeFrequencyDTO {
	private long id;
	private Date date;
	private String arrivalHour;
	private String exitHour;
	private long employee;
	
	public EmployeeFrequencyDTO() {
	}
	
	public EmployeeFrequencyDTO(long id, Date date, String arrivalHour, String exitHour, long employee) {
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
	public long getEmployee() {
		return employee;
	}
	public void setEmployee(long employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalHour == null) ? 0 : arrivalHour.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (employee ^ (employee >>> 32));
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
		EmployeeFrequencyDTO other = (EmployeeFrequencyDTO) obj;
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
		if (employee != other.employee)
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
