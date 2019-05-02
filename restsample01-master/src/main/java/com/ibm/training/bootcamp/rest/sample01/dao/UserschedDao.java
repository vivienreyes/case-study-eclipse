package com.ibm.training.bootcamp.rest.sample01.dao;

import java.util.List;
import com.ibm.training.bootcamp.rest.sample01.domain.Usersched;

public interface UserschedDao {
	
	public List<Usersched> findAll();
	
	public Usersched find(Long id);
	
	public Usersched find1(Long tripid);
	
	public List<Usersched> findByName(String name, String load, String dtstart, String dtend, String status);
	
	public void add(Usersched user);
	
	public void update(Usersched user);
	
	public void delete(Long id);

	public void delete1(Long tripid);


}