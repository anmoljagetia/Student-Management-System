package com.example.sms;

public class individual {
	private String ID;
	private String name;
	private String classID;

	public individual(){

	}

	public individual(String i, String n, String c){
		this.ID = i;
		this.name = n;
		this.classID = c;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getclassID() {
		return classID;
	}

	public void setclassID(String classID) {
		this.classID = classID;
	}

}
