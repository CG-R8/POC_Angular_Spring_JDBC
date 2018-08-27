/**
 * 
 */
package com.myApplication.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.myApplication.model.*;
import com.myApplication.service.EmployeeService;

/**
 * @author Chetan
 *
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

public class EmployeeController {

	private EmployeeService employeeService;

	Properties prop = new Properties();
	InputStream input = null;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "employee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployees() {
		return employeeService.findAll();
	}

	@RequestMapping(value = "employee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws URISyntaxException {
		try {
			Employee result = employeeService.save(employee);
			return ResponseEntity.created(new URI("/api/employee/" + result.getId())).body(result);
		} catch (Exception e) {
			return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
		}

	}

	@RequestMapping(value = "multipleEmployees", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> createEmployees(@RequestBody List<Employee> employees) throws IOException {

		input = new FileInputStream("config.properties");
		prop.load(input);
		String mode = prop.getProperty("mode");
		if (mode.compareTo("a") == 0) {
			for (Employee emp : employees) {
				try {
					employeeService.save(emp);
				} catch (DataIntegrityViolationException exe) {
					exe.getMessage();
				} catch (Exception e) {
					System.err.println("One or More record have error");
				}
			}
		} else {
			try {
				employeeService.saveAll(employees);
			} catch (DataIntegrityViolationException e) {
				e.getMessage();
			} catch (Exception e) {
				System.err.println("One or More record have error....Skipping all transactions...");
			}
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
}
