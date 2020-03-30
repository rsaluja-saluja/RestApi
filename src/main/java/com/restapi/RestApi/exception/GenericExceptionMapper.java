package com.restapi.RestApi.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.restapi.RestApi.model.ErrorMessage;

//Commented below to test WebApplicationException that Jersey already knows
//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		// TODO Auto-generated method stub
		ErrorMessage errMessage = new ErrorMessage(500, exception.getMessage(), "Test Exception");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errMessage)
				.build();
	}

}
