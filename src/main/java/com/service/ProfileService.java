package com.service;

import java.util.ArrayList;
import java.util.Map;
import com.database.Database;
import com.model.Profile;

public class ProfileService {
	
private Map<String, Profile> profiles = Database.getProfiles();
	
	public ProfileService() {
		profiles.put("1", new Profile("1", "user", "Juan", "Perez"));
		profiles.put("2", new Profile("2", "admin", "Peter", "Raymond"));
	}
	
	public ArrayList<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String id){
		return this.profiles.get(id);
	}
	
	public Profile addProfile(Profile p){
		p.setId(Integer.toString(profiles.size()+1));
		this.profiles.put(p.getId(), p);
		return p;
	}
	
	public Profile updateProfile(Profile p){
		if(p.getId()!=""){
			return null;
		}
		this.profiles.put(p.getId(), p);
		return p;
	}
	
	public Profile removeProfile(Profile p){
		if(p.getId()!="" && profiles.containsKey(p.getId())==false){
			return null;
		}
		this.profiles.remove(p.getId());
		return p;
	}
}