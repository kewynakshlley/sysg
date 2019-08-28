package com.gsys.service;

import java.time.Duration;
import java.time.Instant;
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
		int m = (employee.getWorkGoal().getWorkingTime() * 60) * 15;
		employee.getWorkGoal().setWorkingTime(m);
		Employee createdEmployee = this.employeeRepository.save(employee);
		return new ResponseEntity<Employee>(createdEmployee, HttpStatus.OK);
	}

	public void deleteEmployee(long employeeId) {
		this.employeeRepository.deleteById(employeeId);

	}

	public List<EmployeeExit> getEmployeeExitFrequency(long employeeId) {
		return employeeExitRepository.findByEmployee(employeeId);
	}

	public List<EmployeeArrival> getEmployeeArrivalFrequency(long employeeId) {
		return employeeArrivalRepository.findByEmployee(employeeId);
	}

	public void editEmployee(Employee employee) {
		int m = (employee.getWorkGoal().getWorkingTime() * 60) * 15;
		employee.getWorkGoal().setWorkingTime(m);
		employeeRepository.save(employee);

	}

	public ResponseEntity<?> employeeArrivalCheckIn(EmployeeFrequencyDTO ef) throws DataNotFoundException {
		Employee emp = employeeRepository.getOne(ef.getEmployee());
		if (emp == null)
			throw new DataNotFoundException("employee not found");
		EmployeeArrival ee = new EmployeeArrival();	
		ee.setEmployee(emp);
		emp.getArrivalFrequency().add(ee);
		ee.setEmployee(emp);
		EmployeeArrival check = this.employeeArrivalRepository.save(ee);
		employeeRepository.save(emp);
		checkInNotification(NotificationUtil.EMPLOYEE_CHECK_IN, "O funcionário "+emp.getFirstName()+" chegou", 
				NotificationUtil.ENTRY_CATEGORY);
		return new ResponseEntity<EmployeeArrival>(check, HttpStatus.OK);
	}

	public ResponseEntity<?> employeeExitCheckIn(EmployeeFrequencyDTO ef) throws DataNotFoundException {
		Employee emp = employeeRepository.getOne(ef.getEmployee());
		if (emp == null)
			throw new DataNotFoundException("employee not found");
		EmployeeExit ee = new EmployeeExit();
		ee.setEmployee(emp);
		emp.getExitFrequency().add(ee);
		ee.setEmployee(emp);
		EmployeeExit check = this.employeeExitRepository.save(ee);
		addWorkGoal(emp);
		
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
	
	private void addWorkGoal(Employee emp) {
		int totalWorkingHours = 0;
		int totalWorkingMinutes = 0;
		EmployeeArrival arr = employeeArrivalRepository.findLastArrival(emp.getId());
		EmployeeExit exi = employeeExitRepository.findLastExit(emp.getId());
		
		Instant start = null, end = null;
		start = arr.getDateTime().toInstant();
		end = exi.getDateTime().toInstant();
		Duration dur = Duration.between(start, end);
		totalWorkingHours += dur.toHours();
		totalWorkingMinutes += dur.toMinutes();
		totalWorkingMinutes = totalWorkingMinutes + (totalWorkingHours * 60);
		
		int workGoal = emp.getWorkGoal().getPaidGoal();
		emp.getWorkGoal().setPaidGoal(workGoal + totalWorkingMinutes);  
		
		employeeRepository.save(emp);
	}

	public List<Employee> employeesWhoDontReachedTheGoal(){
		return employeeRepository.findWhoDontReachedTheGoal();
	}
	
	public List<Employee> employeesWhoReachedTheGoal(){
		return employeeRepository.findWhoReachedTheGoal();
	}

	public void employeePayment(long employeeId) {
		Employee aux = employeeRepository.getOne(employeeId);
		aux.getWorkGoal().setPaidGoal(0);
		employeeRepository.save(aux);
		
	}

}
