package com.ibm.training.bootcamp.rest.sample01.domain;

public class User {

	Long id;
	private String model;
	private String licenseno;
	private String weight;
	private String capacity;
	private String date;
	@SuppressWarnings("unused")
	private String name;
	
	public User() {
		
	}
	
	public User(String model, String licenseno, String weight, String capacity, String date) {
		this(null, model, licenseno, weight, capacity, date);
	}

	public User(Long id, String model, String licenseno, String weight, String capacity, String date) {
		this.id = id;
		this.model = model;
		this.licenseno = licenseno;
		this.weight = weight;
		this.capacity = capacity;
		this.date = date;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getLicenseNo() {
		return licenseno;
	}
	
	public void setLicenseNo(String licenseno) {
		this.licenseno = licenseno;
	}
	
	public String getWeight() {
		return weight;
	}
	
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public String getCapacity() {
		return capacity;
	}
	
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

		
}
