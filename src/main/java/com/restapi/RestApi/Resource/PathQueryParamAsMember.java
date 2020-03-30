package com.restapi.RestApi.Resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;

@Path("/{pathParam}/test")
// @Singleton
// With Singleton it would give error because singleton instance is created at 
//application startup time and pathParam and queryParam values are not available
// at that time as these values would be passed at run time when request is received.

//SEVERE: Missing dependency for field: private java.lang.String com.restapi.RestApi.Resource.PathQueryParamAsMember.pathParamValue
//SEVERE: Missing dependency for field: private java.lang.String com.restapi.RestApi.Resource.PathQueryParamAsMember.queryParamValu


public class PathQueryParamAsMember {

	@PathParam("pathParam") private String pathParamValue;
	@QueryParam("query") private String queryParamValue;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getPathQueryParamAsMember() {
		return "The pathParam is "+pathParamValue+" and queryParam is "+queryParamValue;
	}
	
}
