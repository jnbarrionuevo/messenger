package com.resources;

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

import com.service.MessageService;

@Path("injectdemoresource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InjectDemoResource {
		
		MessageService ms = new MessageService();
	    
		@GET
		@Path("annotations")
	    public String getParamsUsingAnnotations(@MatrixParam ("param") String matrixParam,
	    								 @HeaderParam ("authSessionID") String headerParam, 
	    								 @CookieParam("name") String cookie) {    	
			return "MatrixParam: " + matrixParam + "- Header param: " + headerParam + "- Cookie param: " + cookie; 
		}
		
		@GET
		@Path("context")
		public String getParamsUsingContext(@Context UriInfo uriInfo,
											@Context HttpHeaders headers){    	
			
			String path = uriInfo.getAbsolutePath().toString();
			String cookies= headers.getCookies().toString();
			return "Path: " + path + "- cookies: " + cookies; 
		}
}
