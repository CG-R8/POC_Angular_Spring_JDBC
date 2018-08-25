/**
 * 
 */
package com.myApplication.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.myApplication.model.*;
import com.myApplication.service.EmployeeService;

/**
 * @author Chetan
 *
 */

@RestController
@RequestMapping("/api")
public class EmployeeResource {

	private EmployeeService employeeService;

	public EmployeeResource(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "employee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployees() {
		return employeeService.findAll();
	}

	@RequestMapping(value = "employee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws URISyntaxException {
		try {
			 
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
}
