package com.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.database.Database;
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
				System.out.println("Year is: " + cal.get(Calendar.YEAR));
				messagesByYear.add(m);
			}
		}
		
		return messagesByYear;
	}
	
	public Message getMessage(Long id){
		return this.messages.get(id);
	}
	
	public Message addMessage (Message m){
		m.setId(messages.size()+1);
		this.messages.put(m.getId(), m);
		return m;
	}
	
	public Message updateMessage(Message m){
		System.out.println("updateMessage id:" + m.getId() + " author: " + m.getAuthor());
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
