package com.gsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gsys.core.dto.EmployeeFrequencyDTO;
import com.gsys.exception.DataNotFoundException;
import com.gsys.model.Employee;
import com.gsys.model.EmployeeFrequency;
import com.gsys.repository.EmployeeFrequencyRepository;
import com.gsys.repository.EmployeeRepository;
@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeFrequencyRepository employeeFrequencyRepository;

	public List<Employee> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	public Employee getEmployee(long employeeId) throws DataNotFoundException {
		Employee empTemp = this.employeeRepository.getOne(employeeId);
		if(empTemp == null) throw new DataNotFoundException("Employee not found");
		return empTemp;
	}

	public ResponseEntity<?> createEmployee(Employee employee) {
		Employee createdEmployee = this.employeeRepository.save(employee);
		return new ResponseEntity<Employee>(createdEmployee, HttpStatus.OK);
	}

	public void deleteEmployee(long employeeId) {
		this.employeeRepository.deleteById(employeeId);
		
	}

	public ResponseEntity<?> employeeCheckIn(EmployeeFrequencyDTO efDTO) throws DataNotFoundException {
		Employee emp = employeeRepository.getOne(efDTO.getEmployee());
		if(emp== null) throw new DataNotFoundException("employee not found");
		EmployeeFrequency ef = new EmployeeFrequency();
		ef.setArrivalHour(efDTO.getArrivalHour());
		ef.setDate(efDTO.getDate());
		ef.setEmployee(emp);
		ef.setExitHour(efDTO.getExitHour());
		emp.getEmployeeFrequencyList().add(ef);
		ef.setEmployee(emp);
		EmployeeFrequency check = this.employeeFrequencyRepository.save(ef);
		employeeRepository.save(emp);
		return new ResponseEntity<EmployeeFrequency>(check, HttpStatus.OK);
		
	}

	public List<EmployeeFrequency> getEmployeeFrequency(long employeeId) {
		return employeeFrequencyRepository.findByEmployee(employeeId);
	}

	public void editEmployee(Employee employee) {
		employeeRepository.save(employee);
		
	}

}
