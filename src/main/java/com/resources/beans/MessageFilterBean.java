package com.resources.beans;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {
	@QueryParam("year")int year;
	@QueryParam("start")int start;
	@QueryParam("size")int size;
	
	public int getYear() {
		return year;
	}
	public int getStart() {
		return start;
	}
	public int getSize() {
		return size;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
