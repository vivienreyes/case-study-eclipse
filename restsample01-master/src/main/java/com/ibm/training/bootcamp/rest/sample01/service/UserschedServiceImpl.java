package com.ibm.training.bootcamp.rest.sample01.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.rest.sample01.dao.UserschedDao;
import com.ibm.training.bootcamp.rest.sample01.dao.UserschedJdbcDaoImpl;
import com.ibm.training.bootcamp.rest.sample01.domain.User;
import com.ibm.training.bootcamp.rest.sample01.domain.Usersched;

@SuppressWarnings("unused")
public class UserschedServiceImpl {
	
	UserschedDao userschedDao;

//	public UserschedServiceImpl() {
//		this.userschedDao = UserschedJdbcDaoImpl.getInstance();
//		//this.userDao = UserHashMapDaoImpl.getInstance();
//	}
//	
	public List<Usersched> findAll() {
		return userschedDao.findAll();
	}

	public Usersched find1(Long id) {
		return userschedDao.find(id);
	}
	
	public Usersched find(Long tripid) {
		return userschedDao.find(tripid);
	}


	public List<Usersched> findByName(String name, String load, String dtstart, String dtend, String status) {
		return userschedDao.findByName(name, load, dtstart, dtend, status);
	}

	public void add(Usersched user) {
		if (validate(user)) {
			userschedDao.add(user);
		} else {
			throw new IllegalArgumentException("Fields cannot be blank.");
		}
	}

	public void upsert(Usersched usersched) {
		if (validate(usersched)) {
			if(usersched.getId() != null && usersched.getId() >= 0) {
				userschedDao.update(usersched);
			} else {
				userschedDao.add(usersched);
			}
		} else {
			throw new IllegalArgumentException("Fields cannot be blank.");
		}
	}
	
	public void upsert1(Usersched usersched) {
		if (validate(usersched)) {
			if(usersched.getTripId() != null && usersched.getTripId() >= 0) {
				userschedDao.update(usersched);
			} else {
				userschedDao.add(usersched);
			}
		} else {
			throw new IllegalArgumentException("Fields cannot be blank.");
		}
	}

	public void delete(Long id) {
		userschedDao.delete(id);
	}
	
	public void delete1(Long tripid) {
		userschedDao.delete(tripid);
	}
	
	private boolean validate(Usersched usersched) {
		return !StringUtils.isAnyBlank(usersched.getName(), usersched.getLoad());
	}


}
