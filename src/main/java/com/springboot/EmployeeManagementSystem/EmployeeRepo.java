package com.springboot.EmployeeManagementSystem;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.EmployeeManagementSystem.Employee;
@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String>
{

}