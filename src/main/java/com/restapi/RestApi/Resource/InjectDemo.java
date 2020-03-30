package com.restapi.RestApi.Resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectDemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemo {

	//Metrix params in URL - /injectDemo/annotations;param=value
	//Header Parameters from client request 
	//Cookie param from client request
	@GET
	@Path("/annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String par,
											@HeaderParam("authSessionId") String customValue,
											@CookieParam("name") String name) {
		return "Matrix Annotations: "+par+" Header Param: "+customValue+" Cookie param: "+name;
	}
	
	//If dont know headr params names/cookie name etc then context can be 
	// used instead of specifc header param name/cookie name 
	@GET
	@Path("/context")
	public String getParamsUsingContext(@Context UriInfo uriInfo,
										@Context HttpHeaders header) {
		List<String> authSessionId = header.getRequestHeader("authSessionId");
		
		return "Path: "+uriInfo.getAbsolutePath()+" Header: "+authSessionId.toString()+header.getRequestHeaders().toString();
	}
}
