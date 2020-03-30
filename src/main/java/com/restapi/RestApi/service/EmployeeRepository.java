package com.restapi.RestApi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.restapi.RestApi.exception.DataNotFoundException;
import com.restapi.RestApi.model.Employee;
import com.restapi.RestApi.model.ErrorMessage;

public class EmployeeRepository {

	List<Employee> listEmp;

	public EmployeeRepository() {
		listEmp = new ArrayList<Employee>();
		
		Employee emp1 = new Employee();
		emp1.setName("Emp1");
		emp1.setId(123);
		emp1.setRole("Architect");
		
		Employee emp2 = new Employee();
		emp2.setName("Emp2");
		emp2.setId(23);
		emp2.setRole("Software Engineer");
		
		Employee emp3 = new Employee();
		emp3.setName("Emp3");
		emp3.setId(33);
		emp3.setRole("Software Engineer");
		
		listEmp.add(emp1);
		listEmp.add(emp2);
		listEmp.add(emp3);
		
	}
	public EmployeeRepository(List<Employee> listEmp) {
		super();
		this.listEmp = listEmp;
	}
	
	public List<Employee> getEmployees() {
		return listEmp;
	}
	
	public List<Employee> getEmployeeWithRole(String role) {
		System.out.println("Getting employee with role: "+role);
		List<Employee> list = new ArrayList<Employee>();
		for(Employee e:listEmp) {
			if(e.getRole().equals(role))
				list.add(e);
		}
		return list;
	}
	
	public Employee getEmployeeWithName(String name) {
		System.out.println("Getting employee: "+name);
		for(Employee e:listEmp) {
			if(e.getName().equals(name))
				return e;
		}
		//return new Employee();
		throw new DataNotFoundException("The Employee with name "+name+" not found");
	}
	public void createEmp(Employee e) {
		// TODO Auto-generated method stub
		listEmp.add(e);
	}
	
	public Employee updateEmp(int id, Employee e) {
		// TODO Auto-generated method stub
		for(Employee emp:listEmp) {
			if(emp.getId() == id) {
				listEmp.remove(emp);
				Employee employee = new Employee();
				employee.setId(id);
				employee.setName(e.getName());
				employee.setRole(e.getRole());
				listEmp.add(employee);
				return employee;
			}
			
		}
		return new Employee();
	}
	
	public List<Employee> deleteEmp(int id) {
		for(Employee emp:listEmp) {
			if(emp.getId() == id) {
				listEmp.remove(emp);
				break;
			}
		}
		return listEmp;

	}
	public Employee getEmployeeWithId(int id) {
		// TODO Auto-generated method stub
		System.out.println("Getting employee with Id: "+id);
		for(Employee e:listEmp) {
			if(e.getId() == id)
				return e;
		}
		//return new Employee();
		ErrorMessage errMsg = new ErrorMessage(404, "Employee with id "+id+" not found", "Test WebApplicationException");
		Response res = Response.status(Status.NOT_FOUND)
								.entity(errMsg)
								.build();
		throw new WebApplicationException(res);
	}
	
}
