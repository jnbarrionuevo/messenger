package com.service;

import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.database.Database;
import com.exceptions.DataNotFoundException;
import com.model.ErrorMessage;
import com.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = Database.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1L, new Date(), "hello!", "Jorge"));
		messages.put(2L, new Message(2L, new Date(),"bye!", "Juan"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getMessagesByYear(int year){
		List<Message> messagesByYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		
		for (Message m : messages.values()) {
			cal.setTime(m.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				messagesByYear.add(m);
			}
		}
		return messagesByYear;
	}
	
	public List<Message> getMessagesPaginated(int start, int size){
		List<Message> messagesPaginated = new ArrayList<>();
		Iterator<Message> it = messages.values().iterator();
		int i=1;
		while (it.hasNext()) {
			Message message = (Message) it.next();
			if(i>=start && i<=(start + size) && messagesPaginated.size()<size){
				messagesPaginated.add(message);
			}
			i++;
		}
		return messagesPaginated;
	}
	
	public Message getMessage(Long id){
		Message m = messages.get(id);    	
		if(m == null){
			System.out.println("------Null message");
			//throw new DataNotFoundException("Message with ID: " + id.toString() + " not found");
			
			ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "http://javabrains");
			Response response = Response.status(Status.INTERNAL_SERVER_ERROR).
							entity(errorMessage).
							build();
			throw new WebApplicationException(response);
		}
		return m;
	}
	
	public Message addMessage (Message m){
		m.setId(messages.size()+1);
		this.messages.put(m.getId(), m);
		return m;
	}
	
	public Message updateMessage(Message m){
		if(m.getId()<=0){
			return null;
		}
		this.messages.put(m.getId(), m);
		return m;
	}
	
	public Message removeMessage(Message m){
		if(m.getId()<=0 && messages.containsKey(m.getId())==false){
			return null;
		}
		this.messages.remove(m.getId());
		return m;
	}
}
