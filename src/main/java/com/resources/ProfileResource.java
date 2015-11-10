package com.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.model.Profile;
import com.service.ProfileService;

@Path("profileresource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	ProfileService ps = new ProfileService();
	
    @GET
    public List<Profile> getProfiles() {    	
        return ps.getAllProfiles();
    }
    
    @POST
    public Profile addProfiles(Profile p) {    	
        return ps.addProfile(p);
    }
    
    @DELETE
    @Path("{profileId}")
    public Profile removeProfile(@PathParam("profileId") String profileId) {    	
        Profile p = ps.getProfile(profileId);
    	return ps.removeProfile(p);
    }
    
    @PUT
    @Path("{profileId}")
    public Profile updateProfile(@PathParam("profileId") String profileId, Profile p){
    	p.setId(profileId);
    	return ps.updateProfile(p);
    }
    
    @GET
    @Path("{profileId}")
    public Profile getProfile(@PathParam("profileId") String profileId) {
		return ps.getProfile(profileId);
    }
}