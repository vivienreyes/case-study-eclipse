package com.ibm.training.bootcamp.rest.sample01.service;

import java.util.List;

import com.ibm.training.bootcamp.rest.sample01.domain.Usersched;

public abstract class UserschedService {
	
public abstract List<Usersched> findAll();
	
	public abstract Usersched find(Long id);
	
	public abstract Usersched find1(Long tripid);
	
	public abstract List<Usersched> findByName(String name, String load, String dtstart, String dtend, String status);
	
	public abstract void add(Usersched usersched);
	
	public abstract void upsert(Usersched usersched);
	
	public abstract void delete(Long id);
	
	public abstract void delete1(Long tripid);

}
