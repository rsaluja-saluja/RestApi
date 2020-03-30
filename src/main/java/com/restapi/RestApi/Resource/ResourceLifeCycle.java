package com.restapi.RestApi.Resource;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;

@Path("/lifeCycle")
@Singleton

//Singleton respice instance would be created only once and before the request received
//SIngleton resources are instantiated during the application startup.
//otherwise resouce is instantiated for every request

public class ResourceLifeCycle {

	//bydefault the count value would intialized to 0 always
	// as resource instance is created at every request and is destroyed after that
	// @Singleton annotation would create single instance and then resource life 
	// cycle would be maintained by JAX-RS 
	private int count;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testResourceLifeCycle() {
		count = count + 1;
		return "This method was called "+count+" time(s)"; //always prints 1 by default
	}
	
}
