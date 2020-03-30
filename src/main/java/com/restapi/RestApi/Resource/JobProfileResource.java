package com.restapi.RestApi.Resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

//Sub Resource
@Path("/")
public class JobProfileResource {

	@GET
	public String test() {
		return "Get sub Resource";
	}
	
	// /employees/employee/{id}/jobProfile/{role}
	// It can access the parent resource like id here
	@GET
	@Path("{role}")
	public String getEmployeeWithIdAndRole(@PathParam("id") int id,
									@PathParam("role") String role) {
		return "Get subResource by id: "+id + " role: "+role;
	}
	
	@DELETE
	public String testDelete() {
		return "Delete sub Resource";
	}
	
}
