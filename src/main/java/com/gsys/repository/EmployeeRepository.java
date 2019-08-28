package com.gsys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gsys.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	@Query(value = "select * from employee where next_payment_date = CURDATE()", nativeQuery = true)
	List<Employee> findEmployeeForPayment();
	@Query(value = "select * from employee e join work_goal wg on e.work_goal_id = wg.id "
			+ "where wg.working_time < wg.paid_goal OR wg.working_time = wg.paid_goal", nativeQuery = true)
	List<Employee> findWhoReachedTheGoal();
	
	@Query(value = "select * from employee e join work_goal wg on e.work_goal_id = wg.id "
			+ "where wg.working_time > wg.paid_goal", nativeQuery = true)
	List<Employee> findWhoDontReachedTheGoal();
	
	
}
