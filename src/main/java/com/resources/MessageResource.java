package com.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.model.Message;
import com.service.MessageService;

@Path("messageresource")
public class MessageResource {
	
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Message> getMessages() {
    	MessageService ms = new MessageService();
        return ms.getAllMessages();
    }
}
