package test;

import java.io.Serializable;

public class USER implements Serializable{
	private static final long serialVersionUID = 1L;
	private String message;
	private String name = "조훈희";
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return this.message;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return "Message: " + this.message + " Name : " + this.name;
	}
}
