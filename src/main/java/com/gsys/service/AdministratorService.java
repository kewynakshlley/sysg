package com.gsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gsys.exception.DataNotFoundException;
import com.gsys.model.Administrator;
import com.gsys.repository.AdministratorRepository;

@Service
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	public List<Administrator> getAllAdministrators() {
		return this.administratorRepository.findAll();
	}

	public Administrator getAdministrator(long admId) throws DataNotFoundException {
		Administrator administrator = this.administratorRepository.getOne(admId);
		if (administrator == null) {
			throw new DataNotFoundException("Administrator not found: " + admId);
		}
		return administrator;
	}

	public void deleteAdministrator(long admId) {
		this.administratorRepository.deleteById(admId);
		
	}

	public ResponseEntity<?> createAdministrator(Administrator administrator) {
		Administrator createdAdministrator = administratorRepository.save(administrator);
		return new ResponseEntity<Administrator>(createdAdministrator, HttpStatus.OK);
	}

}
