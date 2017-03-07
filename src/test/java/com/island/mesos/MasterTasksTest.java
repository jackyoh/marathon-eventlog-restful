package com.island.mesos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.google.gson.Gson;
import com.island.bean.MesosMasterTaskInfo;
import com.island.bean.MesosMasterTaskInfoList;

public class MasterTasksTest {
	public static String mesosMasterTaskURL = "http://192.168.1.223:5050/master/tasks?order=desc";
	
	@Test
	public void testJSONtoOBJECT() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(mesosMasterTaskURL);
		
		HttpResponse response = client.execute(request);
		
		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));
		String line = rd.readLine();
		
		Gson gson = new Gson();
		
		MesosMasterTaskInfoList result = gson.fromJson(line, MesosMasterTaskInfoList.class);
		
		for(MesosMasterTaskInfo task : result.getTasks()){
			System.out.println(task.getName() + "  " + task.getState());
		}
	}
}
