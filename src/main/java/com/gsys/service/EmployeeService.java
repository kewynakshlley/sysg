package com.gsys.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
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

	public List<Employee> employeesWhoDidnotHitTheGoal(){
		List<Employee> empList = employeeRepository.findAll();
		List<Employee> whoDidntHitTheGoal = new ArrayList<>();
		for(Employee e: empList) {
			int totalWorkingHours = 0;
			int totalWorkingMinutes = 0;
			List<EmployeeArrival> arrList = employeeArrivalRepository.findBiweeklyArrival(e.getId());
			List<EmployeeExit> exiList = employeeExitRepository.findBiweeklyExit(e.getId());
			
			for(int k = 0; k < exiList.size(); k++) {
				Instant start = null, end = null;
				start = arrList.get(k).getDateTime().toInstant();
				end = exiList.get(k).getDateTime().toInstant();
				Duration dur = Duration.between(end, start);
				totalWorkingHours += dur.toHours();
				totalWorkingMinutes += dur.toMinutes();
				
			}
			
			int notPaidGoal = e.getWorkGoal().getNotPaidGoal();
			int paidGoal = e.getWorkGoal().getPaidGoal();
			int workingTime = e.getWorkGoal().getWorkingTime() * 60;
			if(paidGoal < workingTime) {
				notPaidGoal += workingTime - paidGoal;
			}else {
				if(notPaidGoal > paidGoal - workingTime) 
					notPaidGoal -= paidGoal - workingTime;
				else 
					notPaidGoal = 0;
			}
			e.getWorkGoal().setNotPaidGoal(notPaidGoal);
			e.getWorkGoal().setPaidGoal(paidGoal);
			employeeRepository.save(e);
			if(totalWorkingMinutes < workingTime) {	
				whoDidntHitTheGoal.add(e);
			}
			
		}
		return whoDidntHitTheGoal;
	}
	
	public List<Employee> employeesWhoHitTheGoal(){
		List<Employee> aux = employeeRepository.findAll();
		aux.removeAll(employeesWhoDidnotHitTheGoal());
		return aux;
	}

}
