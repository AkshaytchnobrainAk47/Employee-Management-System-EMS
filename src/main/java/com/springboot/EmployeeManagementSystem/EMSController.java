package com.springboot.EmployeeManagementSystem;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class EMSController {
	
	@Autowired(required = true)
	EmployeeRepo employeeRepo;
	
	//Controller for normal home page. i.e. Display Logic.
	@GetMapping("/display")
	public String getHomePage(Model model)
	{
		System.out.println("Home page has been called...!!");
		List<Employee> employeeList = employeeRepo.findAll();
		model.addAttribute("employees", employeeList);
		model.addAttribute("employee", new Employee());
		model.addAttribute("confirmationForm", new ConfirmationForm());
		
		return "index";
	}
	
	//Insert Data. 
	@PostMapping("/create")
	public String newEmployee(Employee employee, Model model)
	{
		model.addAttribute("employee", new Employee());
		
		//creating dynamic Employee Id
		String empId = "EMP";
		Random random = new Random();
		long randomNumber = 1000 + random.nextInt(9000);
		empId = empId + randomNumber;
		employee.setId(empId);
		// Save the Employee
		employeeRepo.save(employee);
		
		model.addAttribute("success", "Employee with the Employee Id "+employee.getId()+"has been added successfully.");
		return "redirect:/display";
	}
	
	//Update Data.
	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee employee, Model model)
	{
		model.addAttribute("employee", new Employee());
		Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());
		if(existingEmployee.isPresent())
		{
			employeeRepo.save(employee);
			model.addAttribute("success", "Employee with the ID "+employee.getId()+"is found");
		}
		else
		{
			model.addAttribute("errorMessage", "Employee with the ID "+employee.getId()+"not found");
		}
		
		//After successfully updating the employee details, redirect to the Index Page and the data is visible
		//in the table.
		//Post mapping is used means Posting the data that's why using this mapping. it is possible using 
		//Thymeleaf in HTML Page.
		
		return "redirect:/display";
	}
	
	//For Deleting the employee
	//For deleting the employee, we only need Employee Id only then the Employee will be deleted or it won't
	//be possible to delete. After deleting the employee, result will be updated in the Employee Table for this
	//used Thymeleaf for handling the employee pojo class
	
	@PostMapping("/remove")
	public String removeEmployee(Employee employee, Model model)
	{
		model.addAttribute("employee", new Employee());
		Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());
		if(existingEmployee.isPresent())
		{
			employeeRepo.deleteById(employee.getId());
		}
		
		return "redirect:/display";
	}
	
	//DeleteAllEmployees
	//DeleteAll employees logic is based on confirmation of the user before deleting all the data.
	//From HTML page side the Thymeleaf performed this by holding ConfirmationForm pojo. This confirmation is
	//in the String type.
	
	@PostMapping("/remove/all")
	public String removeAll(@ModelAttribute ConfirmationForm confirmationForm , Model model)
	{
		String confimation = confirmationForm.getConfirmation();
		if(confimation.equalsIgnoreCase("Yes"))
		{
			employeeRepo.deleteAll();
		}
		else
			return "redirect:/display";
		
		return "redirect:/display";
	}
}
