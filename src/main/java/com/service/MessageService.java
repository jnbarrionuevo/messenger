package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.database.Database;
import com.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = Database.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1L, "hello!", "Jorge"));
		messages.put(2L, new Message(1L, "bye!", "Juan"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(Long id){
		return this.messages.get(id);
	}
	
	public Message addMessage(Message m){
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
