package com.gsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsys.exception.DataAlreadyExistsException;
import com.gsys.exception.DataNotFoundException;
import com.gsys.model.Administrator;
import com.gsys.service.AdministratorService;

@RestController
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;

	@GetMapping(path = "/administrators")
	public List<Administrator> getAllAdministrators() {
		return this.administratorService.getAllAdministrators();
	}

	@GetMapping(path = "/administrators/{admId}")
	public Administrator getAdministrator(@PathVariable long admId) throws DataNotFoundException {
		return this.administratorService.getAdministrator(admId);
	}

	@PostMapping(path = "/administrators/new")
	public ResponseEntity<?> createAdministrator(@RequestBody Administrator administrator)
			throws DataAlreadyExistsException {
		return this.administratorService.createAdministrator(administrator);
	}

	@DeleteMapping(path = "/administrators/{admId}")
	public void deleteAdministrator(@PathVariable long admId) {
		administratorService.deleteAdministrator(admId);
	}

}
