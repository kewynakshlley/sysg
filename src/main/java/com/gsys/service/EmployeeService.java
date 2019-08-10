package com.gsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gsys.core.dto.EmployeeFrequencyDTO;
import com.gsys.core.util.NotificationUtil;
import com.gsys.exception.DataNotFoundException;
import com.gsys.model.Employee;
import com.gsys.model.EmployeeArrival;
import com.gsys.model.EmployeeExit;
import com.gsys.model.EmployeeFrequency;
import com.gsys.model.Notification;
import com.gsys.repository.EmployeeArrivalRepository;
import com.gsys.repository.EmployeeExitRepository;
import com.gsys.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeExitRepository employeeExitRepository;
	@Autowired
	private EmployeeArrivalRepository employeeArrivalRepository;
	@Autowired
	private NotificationService notificationService;

	public List<Employee> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	public Employee getEmployee(long employeeId) throws DataNotFoundException {
		Employee empTemp = this.employeeRepository.getOne(employeeId);
		if (empTemp == null)
			throw new DataNotFoundException("Employee not found");
		return empTemp;
	}

	public ResponseEntity<?> createEmployee(Employee employee) {
		Employee createdEmployee = this.employeeRepository.save(employee);
		return new ResponseEntity<Employee>(createdEmployee, HttpStatus.OK);
	}

	public void deleteEmployee(long employeeId) {
		this.employeeRepository.deleteById(employeeId);

	}

	public List<EmployeeFrequency> getEmployeeExitFrequency(long employeeId) {
		return employeeExitRepository.findByEmployee(employeeId);
	}

	public List<EmployeeFrequency> getEmployeeArrivalFrequency(long employeeId) {
		return employeeArrivalRepository.findByEmployee(employeeId);
	}

	public void editEmployee(Employee employee) {
		employeeRepository.save(employee);

	}

	public ResponseEntity<?> employeeArrivalCheckIn(EmployeeFrequencyDTO ef) throws DataNotFoundException {
		Employee emp = employeeRepository.getOne(ef.getEmployee());
		if (emp == null)
			throw new DataNotFoundException("employee not found");
		EmployeeArrival ee = new EmployeeArrival();
		ee.setHour(ee.getHour());
		ee.setDate(ee.getDate());
		ee.setEmployee(emp);
		emp.getArrivalFrequency().add(ee);
		ee.setEmployee(emp);
		EmployeeArrival check = this.employeeArrivalRepository.save(ee);
		employeeRepository.save(emp);
		checkInNotification(NotificationUtil.EMPLOYEE_CHECK_IN, "O funcionário "+emp.getFirstName()+" chegou", 
				NotificationUtil.EXIT_CATEGORY);
		return new ResponseEntity<EmployeeArrival>(check, HttpStatus.OK);
	}

	public ResponseEntity<?> employeeExitCheckIn(EmployeeFrequencyDTO ef) throws DataNotFoundException {
		Employee emp = employeeRepository.getOne(ef.getEmployee());
		if (emp == null)
			throw new DataNotFoundException("employee not found");
		EmployeeExit ee = new EmployeeExit();
		ee.setHour(ee.getHour());
		ee.setDate(ee.getDate());
		ee.setEmployee(emp);
		emp.getExitFrequency().add(ee);
		ee.setEmployee(emp);
		EmployeeExit check = this.employeeExitRepository.save(ee);
		employeeRepository.save(emp);
		checkInNotification(NotificationUtil.EMPLOYEE_CHECK_IN, "O funcionário "+emp.getFirstName()+" saiu", 
				NotificationUtil.EXIT_CATEGORY);
		return new ResponseEntity<EmployeeExit>(check, HttpStatus.OK);
	}
	
	private void checkInNotification(String title, String description, String category) {
		Notification nf = new Notification();
		nf.setTitle(title);
		nf.setDescription(description);
		nf.setCategory(category);
		notificationService.saveNotification(nf);
	}

}
