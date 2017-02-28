package com.island.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.island.bean.TaskInfoBean;
import com.island.util.Constants;

@Path("v2")
public class EventLogResource {
	
	@Context
	private ServletContext context;
	   
	@GET
	@Path("apps")
	@Produces(MediaType.APPLICATION_JSON)
	public String lists(){
		Map<String, TaskInfoBean> statusCache = 
                (Map<String, TaskInfoBean>) context.getAttribute(Constants.STATUSCACHE);
		Gson gson = new Gson();
		if(statusCache == null){
			return "";
		}
		return gson.toJson(statusCache, Map.class);
	}
	
	@GET
	@Path("apps/{appId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String appStatus(@PathParam("appId") String appId){
		Map<String, TaskInfoBean> statusCache = 
                (Map<String, TaskInfoBean>) context.getAttribute(Constants.STATUSCACHE);
		
		Gson gson = new Gson();
		
		if(statusCache == null){
			return "";
		}
		TaskInfoBean taskStatus = statusCache.get("/" + appId);
		if(taskStatus == null){
			return "";
		}
		return gson.toJson(taskStatus, TaskInfoBean.class);
	}
	
	@GET
	@Path("taskstatus")
	@Produces(MediaType.APPLICATION_JSON)
	public String tasksStatus(){
		Map<String, TaskInfoBean> statusCache = 
                (Map<String, TaskInfoBean>) context.getAttribute(Constants.STATUSCACHE);
		
		Gson gson = new Gson();
		
		if(statusCache == null){
			return "";
		}
		Map<String, String> maps = new HashMap<String, String>();
		for(Entry<String, TaskInfoBean> entry : statusCache.entrySet()){
			maps.put(entry.getKey(), entry.getValue().getTaskStatus());
		}
		return gson.toJson(maps, Map.class);
	}
	
	@GET
	@Path("taskstatus/{appId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String taskStatus(@PathParam("appId") String appId){
		Map<String, TaskInfoBean> statusCache = 
                (Map<String, TaskInfoBean>) context.getAttribute(Constants.STATUSCACHE);
		
		Gson gson = new Gson();
		
		if(statusCache == null){
			return "";
		}
		
		TaskInfoBean taskStatus = statusCache.get("/" + appId);
		if(taskStatus == null){
			return "";
		}
		return gson.toJson(taskStatus.getTaskStatus(), String.class);
	}
}
