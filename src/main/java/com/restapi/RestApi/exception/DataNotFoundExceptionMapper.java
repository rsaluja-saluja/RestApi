package com.restapi.RestApi.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.restapi.RestApi.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		// TODO Auto-generated method stub
		ErrorMessage errMessage = new ErrorMessage(404, exception.getMessage(), "Test Exception");
		return Response.status(Status.NOT_FOUND)
				.entity(errMessage)
				.build();
	}

	
}
