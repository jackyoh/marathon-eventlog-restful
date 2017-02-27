package com.island.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("v2")
public class EventLogResource {
	@GET
	@Path("apps")
	@Produces(MediaType.APPLICATION_JSON)
	public String lists(){
		List<String> list = new ArrayList<String>();
		list.add("aaaa");
		list.add("bbbb");
		Gson gson = new Gson();
		String json = gson.toJson(list, List.class);
		return json;
	}
	@GET
	@Path("apps/{appId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String appStatus(@PathParam("appId") String appId){
		
		return "Application is:" + appId;
	}

}
