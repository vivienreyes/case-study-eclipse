package com.ibm.training.bootcamp.rest.sample01.domain;

public class Usersched {
	
	Long tripid;
	Long id;
	private String name;
	private String load;
	private String dtstart;
	private String dtend;
	private String status;
	
	public Usersched() {
		
	}
	
	public Usersched(String name, String load, String dtstart, String dtend, String status) {
		this(null, null, name, load, dtstart, dtend, status);
	}

	public Usersched(Long tripid, Long id, String name, String load, String dtstart, String dtend, String status) {
		this.tripid = tripid;
		this.id = id;
		this.name = name;
		this.load = load;
		this.dtstart = dtstart;
		this.dtend = dtend;
		this.status = status;
		
	}
	
	public Long getTripId() {
		return tripid;
	}

	public void setTripId(Long tripid) {
		this.tripid = tripid;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLoad() {
		return load;
	}
	
	public void setLoad(String load) {
		this.load = load;
	}
	
	public String getDtstart() {
		return dtstart;
	}
	
	public void setDtstart(String dtstart) {
		this.dtstart = dtstart;
	}
	
	public String getDtend() {
		return dtend;
	}
	
	public void setDtend(String dtend) {
		this.dtend = dtend;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
		

}
