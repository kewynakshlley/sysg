package com.gsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsys.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
	Administrator findByLoginAndPassword(String login, String password);
}
