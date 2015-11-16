package com.resources;

import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.model.Link;
import com.model.Message;
import com.resources.beans.MessageFilterBean;
import com.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	MessageService ms = new MessageService();
    
	@GET
	@Produces(value={MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	@Consumes(MediaType.TEXT_XML)
	public List<Message> getJasonMessages(@BeanParam MessageFilterBean filterBean) {  
		
		System.out.println("----getJasonMessages");
		
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
    
	@GET
	@Produces(MediaType.TEXT_XML)
    public List<Message> getXmlMessages(@BeanParam MessageFilterBean filterBean) {  
		
		System.out.println("----getXmlMessages");
		
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
    public Response addMessages(Message m, @Context UriInfo uriInfo) {    	
    	System.out.println("URI info:" + uriInfo.toString());
    	Message newMessage = ms.addMessage(m);
    	String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        String path = uriInfo.getAbsolutePath().toString();
    
        System.out.println("URI:" + uri.toString());
        
        return Response.created(uri).
    			entity(newMessage).
    			build();   	
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
    public Message getMessage(@PathParam("messageId") Long messageId, @Context UriInfo uriInfo) {
    	Message m = ms.getMessage(messageId);
    	String uri = getUriForSelf(uriInfo, m);
    	String uri1 = getUriForProfile(uriInfo, m);
    	String uri2 = getUriForComment(uriInfo, m);
    	
    	m.addLink(uri, "self");
    	m.addLink(uri1, "profile");
    	m.addLink(uri2, "comment");
    	
    	
    	return m;
    }
    
	private String getUriForComment(UriInfo uriInfo, Message m) {
		String uri = uriInfo.getBaseUriBuilder().
    				path(MessageResource.class).
    				path(MessageResource.class, "getCommentResource").
    				path(CommentResource.class).
    				resolveTemplate("messageId", m.getId()).
    				build().
    				toString();
		return uri;
	}
    
	private String getUriForSelf(UriInfo uriInfo, Message m) {
		String uri = uriInfo.getBaseUriBuilder().
    				path(MessageResource.class).
    				path(Long.toString(m.getId())).
    				build().
    				toString();
		return uri;
	}
    
    @Path("{messageId}/comments")
    public CommentResource getCommentResource() {
		return new CommentResource();
    }
    		
    private String getUriForProfile(UriInfo uriInfo, Message m) {
		String uri = uriInfo.getBaseUriBuilder().
    				path(ProfileResource.class).
    				path(m.getAuthor()).
    				build().
    				toString();
		return uri;
	}
    
}
