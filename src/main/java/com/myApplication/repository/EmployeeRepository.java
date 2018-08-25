/**
 * 
 */
package com.myApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myApplication.model.Employee;

/**
 * @author Chetan
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByName(String name);
}
