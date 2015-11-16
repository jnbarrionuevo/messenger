package com.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ext) {
		System.out.println("GenericExceptionMapper.toResponse()");
		ErrorMessage errorMessage = new ErrorMessage(ext.getMessage(), 500, "http://javabrains");
		return Response.status(Status.INTERNAL_SERVER_ERROR).
				entity(errorMessage).
				build();
	}
}
