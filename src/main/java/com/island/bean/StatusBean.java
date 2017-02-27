package com.island.bean;

public class StatusBean {
	private String slaveId;
	private String taskStatus;
	private String message;
	private String appId;
	private String host;
	private Long[] ports;
	private String version;
	private String eventType;
	private String timestamp;
	
	public String getSlaveId() {
		return slaveId;
	}
	public void setSlaveId(String slaveId) {
		this.slaveId = slaveId;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}

	public Long[] getPorts() {
		return ports;
	}
	public void setPorts(Long[] ports) {
		this.ports = ports;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
