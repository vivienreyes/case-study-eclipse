package com.ibm.training.bootcamp.rest.sample01.dao;

import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.training.bootcamp.rest.sample01.domain.Usersched;

public abstract class Schedule {
	
public JDBCDataSource dataSource;
	
	public void init() {
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:hsql://localhost/tripdb");
		dataSource.setUser("SA");
	}

	public void update(Usersched usersched) {
		// TODO Auto-generated method stub
		
	}
}