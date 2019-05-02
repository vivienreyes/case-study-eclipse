package com.ibm.training.bootcamp.rest.sample01.dao;

import org.hsqldb.jdbc.JDBCDataSource;

public abstract class Schedule {
	
public JDBCDataSource dataSource;
	
	public void init() {
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:hsql://localhost/tripdb");
		dataSource.setUser("SA");
	}

}