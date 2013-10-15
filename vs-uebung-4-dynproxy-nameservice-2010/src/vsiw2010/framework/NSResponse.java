package vsiw2010.framework;

import java.io.Serializable;

public class NSResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String hostname;
	private int port;
	private long objectId;
	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public long getObjectId() {
		return objectId;
	}
	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}
	
}
