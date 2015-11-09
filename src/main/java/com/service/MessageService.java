package com.service;

import java.util.ArrayList;
import java.util.List;

import com.model.Message;

public class MessageService {
	
	public List<Message> getAllMessages(){
		Message m1 = new Message(1L,"Hola","Jorge");
		Message m2 = new Message(2L,"Chau","Jorge");
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		
		return list;
	}
}
