package com.ibm.training.bootcamp.rest.sample01.domain;

public class User {

	Long id;
	private String model;
	private String licenseno;
	private int weight;
	private int capacity;
	
	public User() {
		
	}
	
	public User(String model, String licenseno, int weight, int capacity) {
		this(null, model, licenseno, weight, capacity);
	}

	public User(Long id, String model, String licenseno, int weight, int capacity) {
		this.id = id;
		this.model = model;
		this.licenseno = licenseno;
		this.weight = weight;
		this.capacity = capacity;
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
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
