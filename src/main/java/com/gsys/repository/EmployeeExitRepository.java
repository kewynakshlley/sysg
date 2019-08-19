package com.gsys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gsys.model.EmployeeExit;

public interface EmployeeExitRepository extends JpaRepository<EmployeeExit, Long>{
	@Query(value = "select * from employee_exit where employee_id = :employeeId", nativeQuery = true)
	List<EmployeeExit> findByEmployee(@Param("employeeId") long employeeId);
	@Query(value = " select * from employee_exit where employee_id = :employeeId order by id desc limit 15", nativeQuery = true)
	List<EmployeeExit> findBiweeklyExit(@Param("employeeId") long employeeId);
}