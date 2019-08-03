package com.gsys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gsys.model.EmployeeFrequency;

@Repository
public interface EmployeeFrequencyRepository extends JpaRepository<EmployeeFrequency, Long>{
	@Query(value = "select * from employee_frequency where employee_id = :employeeId", nativeQuery = true)
	List<EmployeeFrequency> findByEmployee(@Param("employeeId") long employeeId);
	
}
