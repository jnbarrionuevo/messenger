package com.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.model.Message;
import com.resources.beans.MessageFilterBean;
import com.service.MessageService;

@Path("messageresource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService ms = new MessageService();
    
	@GET
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {  
		
		System.out.println("Using Bean: ");
		System.out.println("			 Year: "+filterBean.getYear());
		System.out.println("			 Size: "+filterBean.getSize());
		System.out.println("			 Start: "+filterBean.getStart());
		
        if(filterBean.getYear()>0){
        	return getMessagesByYear(filterBean.getYear());
        }
        else if(filterBean.getStart()>0 && filterBean.getSize()>0){
        	return getMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }
		return ms.getAllMessages();
    }
    
    public List<Message> getMessagesByYear(int year) {    	
        return ms.getMessagesByYear(year);
    }
	
    public List<Message> getMessagesPaginated(int start, int size) {    	
        return ms.getMessagesPaginated(start, size);
    }
    
    @POST
    public Message addMessages(Message m) {    	
        return ms.addMessage(m);
    }
    
    @DELETE
    @Path("{messageId}")
    public Message removeMessage(@PathParam("messageId") Long messageId) {    	
        Message m = ms.getMessage(messageId);
    	return ms.removeMessage(m);
    }
    
    @PUT
    @Path("{messageId}")
    public Message updateMessage(@PathParam("messageId") Long messageId, Message m){
    	m.setId(messageId);
    	return ms.updateMessage(m);
    }
    
    @GET
    @Path("{messageId}")
    public Message getMessage(@PathParam("messageId") Long messageId) {
		return ms.getMessage(messageId);
    }
}
