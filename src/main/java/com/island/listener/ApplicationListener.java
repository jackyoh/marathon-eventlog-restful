package com.island.listener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.island.bean.StatusBean;
import com.island.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationListener implements ServletContextListener{
	private static Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.info("ApplicationListener context initialized");
		String marathonRestfulURL = System.getenv(Constants.MARATHON_RESTFUL_EVENTLOG_URL);
		LOGGER.info("Marathon Restful URL is:" + marathonRestfulURL);
		if(marathonRestfulURL == null || marathonRestfulURL.equals("")){
			throw new RuntimeException("Please set MARATHON_RESTFUL_EVENTLOG_URL environment variable.");
			//example:
			//export MARATHON_RESTFUL_EVENTLOG_URL=http://192.168.1.8:8080/v2/events
		}
		Map<String, StatusBean> statusMaps = new HashMap<String, StatusBean>();
		MarathonEventLogRestfulThread thread = new MarathonEventLogRestfulThread(
																    sce.getServletContext(),
				                                        marathonRestfulURL,
				                                        statusMaps);
		thread.run();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.removeAttribute(Constants.STATUSCACHE);
	}
}

class MarathonEventLogRestfulThread implements Runnable {
	private static Logger LOGGER = LoggerFactory.getLogger(MarathonEventLogRestfulThread.class);
	private ServletContext context;
	private String marathonRestfulURL;
	private Map<String, StatusBean> statusMaps;
	
	
	public MarathonEventLogRestfulThread(ServletContext context, 
			                               String marathonRestfulURL, 
			                               Map<String, StatusBean> statusMaps){
		this.context = context;
		this.marathonRestfulURL = marathonRestfulURL;
		this.statusMaps = statusMaps;
	}
	
	@Override
	public void run() {
		 try{
				HttpClient client = HttpClientBuilder.create().build();
				HttpGet request = new HttpGet(this.marathonRestfulURL);

				request.addHeader(Constants.ACCEPT, Constants.EVENTSTREAM);
				HttpResponse response = client.execute(request);

				BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));

				String line = "";
				while ((line = rd.readLine()) != null) {
					if(line.contains(Constants.TASKSTATUS)){
						line = line.replace(Constants.REPLACEJSONDATASTR, "");
						Gson gson = new Gson();
						StatusBean statusBean = gson.fromJson(line, StatusBean.class);
												
						statusMaps.put(statusBean.getAppId(), statusBean);
						this.context.setAttribute(Constants.STATUSCACHE, statusMaps);
					}
				}
			  
		  }catch(Exception e){
			  LOGGER.error("Please check your Marathon Framework Restful service!");
			  throw new RuntimeException(e);
		  }
		
	}
	
}