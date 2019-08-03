package com.gsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsys.core.dto.EmployeeFrequencyDTO;
import com.gsys.exception.DataAlreadyExistsException;
import com.gsys.exception.DataNotFoundException;
import com.gsys.model.Employee;
import com.gsys.model.EmployeeFrequency;
import com.gsys.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping(path = "/employees")
	public List<Employee> getAllEmployees() {
		return this.employeeService.getAllEmployees();
	}

	@GetMapping(path = "/employees/{employeeId}")
	public Employee getEmployee(@PathVariable long employeeId) throws DataNotFoundException {
		return this.employeeService.getEmployee(employeeId);
	}

	@PostMapping(path = "/employees/new")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee)
			throws DataAlreadyExistsException {
		return this.employeeService.createEmployee(employee);
	}

	@DeleteMapping(path = "/employees/{employeeId}")
	public void deleteEmployee(@PathVariable long employeeId) {
		employeeService.deleteEmployee(employeeId);
	}
	
	@PostMapping(path = "/employees/check-in")
	public ResponseEntity<?> employeeCheckIn(@RequestBody EmployeeFrequencyDTO ef) throws DataNotFoundException{
		return employeeService.employeeCheckIn(ef);
	}	
	
	@GetMapping(path = "/employee/{employeeId}/frequency")
	public List<EmployeeFrequency> getEmployeeFrequency(@PathVariable long employeeId){
		return employeeService.getEmployeeFrequency(employeeId);
	}
	
	@PutMapping(path = "employee/edit-employee")
	public void editMember(@RequestBody Employee employee) {
		employeeService.editEmployee(employee);
	}
}