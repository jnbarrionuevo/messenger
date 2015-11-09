package com.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.model.Message;
import com.service.MessageService;

@Path("messageresource")
public class MessageResource {
	
	MessageService ms = new MessageService();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessages() {    	
        return ms.getAllMessages();
    }
    
    @GET
    @Path("{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message getMessage(@PathParam("messageId") Long messageId) {
		return ms.getMessage(messageId);
    }
}
