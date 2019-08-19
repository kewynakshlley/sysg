package com.gsys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gsys.model.EmployeeArrival;

@Repository
public interface EmployeeArrivalRepository extends JpaRepository<EmployeeArrival, Long>{
	@Query(value = "select * from employee_arrival where employee_id = :employeeId", nativeQuery = true)
	List<EmployeeArrival> findByEmployee(@Param("employeeId") long employeeId);
	@Query(value = " select * from employee_arrival where employee_id = :employeeId order by id desc limit 15", nativeQuery = true)
	List<EmployeeArrival> findBiweeklyArrival(@Param("employeeId") long employeeId);

}
