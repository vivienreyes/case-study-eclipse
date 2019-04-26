package com.ibm.training.bootcamp.rest.sample01.dao;

import java.util.List;
import com.ibm.training.bootcamp.rest.sample01.domain.User;

public interface UserDao {
	
	public List<User> findAll();
	
	public User find(Long id);
	
	public List<User> findByName(String model, String licenseno, String weight, String capacity, String date);
	
	public void add(User user);
	
	public void update(User user);
	
	public void delete(Long id);

}
