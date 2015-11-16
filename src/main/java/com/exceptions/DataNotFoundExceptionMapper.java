package com.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{
	@Override
	public Response toResponse(DataNotFoundException exc) {
		System.out.println("DataNotFoundExceptionMapper.toResponse()");
		ErrorMessage errorMessage = new ErrorMessage(exc.getMessage(), 404, "http://javabrains");
		return Response.status(Status.NOT_FOUND).
				entity(errorMessage).
				build();
	}
}