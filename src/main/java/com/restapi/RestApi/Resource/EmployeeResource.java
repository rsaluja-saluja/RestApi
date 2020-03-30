package com.restapi.RestApi.Resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.restapi.RestApi.model.Employee;
import com.restapi.RestApi.service.EmployeeRepository;

import javax.ws.rs.core.UriInfo;

@Path("employees")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class EmployeeResource {

	EmployeeRepository empRepo = new EmployeeRepository();
	
	// /employees
	// /employees?id=12
	@GET
	//@Produces(MediaType.APPLICATION_XML)
	//@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Employee> getEmployees(@QueryParam("role") String role,
										@QueryParam("id") int id,
										@QueryParam("name") String name) {
		System.out.println("getEmployee called with role: "+ role);
		if(role != null)
			return empRepo.getEmployeeWithRole(role);
		if(name !=null) {
			List<Employee> e= new ArrayList<Employee>();
			e.add(empRepo.getEmployeeWithName(name));
			return e;
		}			
		if(id != 0) {
			List<Employee> e= new ArrayList<Employee>();
			e.add(empRepo.getEmployeeWithId(id));
			return e;		
			}
			
		return empRepo.getEmployees();
	}
	
	@GET
	@Path("employee/{name}")
	//@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Employee getEmployee(@PathParam("name") String empName,
								@Context UriInfo uriInfo) {
		Employee emp = empRepo.getEmployeeWithName(empName);
		
		String uri = getUriForSelf(empName, uriInfo);
		emp.addLink(uri, "self");
		emp.addLink(getUriForInjectDemo(empName, uriInfo), "InjectDemo");
		emp.addLink(getUriForJobProfile(empName, uriInfo), "JobProfile");
		return emp;
	}

	private String getUriForSelf(String empName, UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder()
							.path(EmployeeResource.class)
							.path("employee/"+empName)
							.build()
							.toString();
		return uri;
	}
	
	private String getUriForInjectDemo(String empName, UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder()
							.path(InjectDemo.class)
							.build()
							.toString();
		return uri;
	}
	
	//.resolveTemplate can be used to replace variables in resource with actual values
	// but this method is not showing here
	// might be present in Jersey 2
	private String getUriForJobProfile(String empName, UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder()
							.path(EmployeeResource.class)
							.path(EmployeeResource.class,"getJobProfile")
							.path(JobProfileResource.class)
							.path(JobProfileResource.class, "getEmployeeWithIdAndRole")
							.build()
							.toString();
		return uri;
	}
	
	@POST
	@Path("employee")
	//@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	//@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//	public List<Employee> createEmp(Employee e) {
//		System.out.println("createEmp called");
//		empRepo.createEmp(e);
//		return empRepo.getEmployees();
//	}
	
	//If want to return status code instead of 200(OK default) or any other 
	//changes in default header then use Reponse Builder as return value 
	// instead of directly the message back.
	
	public Response createEmp(Employee e, @Context UriInfo uriInfo) {
		System.out.println("createEmp reurning Response called");
		empRepo.createEmp(e);
		//List<Employee> list = empRepo.getEmployees();
//		return Response.status(Status.CREATED)
//						.entity(empRepo.getEmployeeWithId(e.getId()))
//						.header("Location", uriInfo.getRequestUri())
//						.build();
		
		//Response.created() will set the status to 201 and add location in header
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(e.getId())).build();
		return Response.created(uri)
						.entity(empRepo.getEmployeeWithId(e.getId()))
						.build();
	}
	
	
	@PUT
	@Path("employee/{id}")
	public Employee updateEmp(@PathParam("id") int id, Employee e) {
		System.out.println("updateEmp called");
		return empRepo.updateEmp(id,e);
		
	}
	
	@DELETE
	@Path("employee/{id}")
	public List<Employee> deleteEmp(@PathParam("id") int id) {
		System.out.println("deleteEmp called");
		return empRepo.deleteEmp(id);
	}
	
	//sub resource
	//passing the request to subResource by returning the object of that
	//All GET/PUT/DELETE/POST request would be passed to subResource
	@Path("employee/{id}/jobProfile")
	public JobProfileResource getJobProfile() {
		return new JobProfileResource();
	}
}
