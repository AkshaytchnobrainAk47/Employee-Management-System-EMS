package com.springboot.EmployeeManagementSystem;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data //This generates getter setters method and some useful methods such as toString(), equals(), etc.
@AllArgsConstructor // This is used to generate a constructor with the arguments for all the fields in the class.
@NoArgsConstructor // This annotation is used to generate the constructor without any arguments
@Document(collection = "employee") //This indicates that this class is a MongoDB document and also specify the collection
//name where the instances of the class will be stored.

public class Employee 
{
	
	@Id
	private String id;
	private String emp_name;
	private Long emp_phone;
	private String emp_emailId;
	private String emp_gender;
	private String employeeSalary;
	private String employeeRole;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public Long getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(Long emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getEmp_emailId() {
		return emp_emailId;
	}
	public void setEmp_emailId(String emp_emailId) {
		this.emp_emailId = emp_emailId;
	}
	public String getEmp_gender() {
		return emp_gender;
	}
	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}
	public String getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	
}
