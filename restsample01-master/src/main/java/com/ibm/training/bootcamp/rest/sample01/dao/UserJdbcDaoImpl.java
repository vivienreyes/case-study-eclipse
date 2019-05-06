package com.ibm.training.bootcamp.rest.sample01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


import com.ibm.training.bootcamp.rest.sample01.domain.User;

public class UserJdbcDaoImpl extends Trucking implements UserDao {


	private static UserJdbcDaoImpl INSTANCE;

	


	static public UserJdbcDaoImpl getInstance() {

		UserJdbcDaoImpl instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new UserJdbcDaoImpl();
			INSTANCE = instance;
		}

		return instance;
	}

	private UserJdbcDaoImpl() {
		init();
	}

	

//	private void createUserTable() {
//		String createSql = "CREATE TABLE USERS " + "(id INTEGER IDENTITY PRIMARY KEY, " + " model VARCHAR(255), "
//				+ " licenseno VARCHAR(255), " + " weight VARCHAR(255), " + " capacity VARCHAR(255)," + "date VARCHAR(255))";
//
//		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
//
//			stmt.executeUpdate(createSql);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//
//	private void insertInitUsers() {
//		
//		add(new User());
//		add(new User("Nissan","ZXC 1223", "90", "9000", "04/22/2019"));
//		add(new User("Toyota","JHG 5432", "90", "9000", "04/23/2019"));
//		add(new User("Toyota","GTH 9876", "90", "9000", "04/24/2019"));
//		add(new User("Nissan","PYT 7639", "90", "9000", "04/25/2019"));
//	}

	@Override
	public List<User> findAll() {

		return findByName(null, null, null, null, null);
	}

	@Override
	public User find(Long id) {

		User user = null;

		if (id != null) {
			String sql = "SELECT id, model, licenseno, weight, capacity, date FROM USERS where id = ?";
			try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, id.intValue());
				ResultSet results = ps.executeQuery();

				if (results.next()) {
					user = new User(Long.valueOf(results.getInt("id")), results.getString("model"),
							results.getString("licenseno"), results.getString("weight"), results.getString("capacity"), results.getString("date"));
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return user;
	}

	@Override
	public List<User> findByName(String model, String licenseno, String weight, String capacity, String date) {
		List<User> users = new ArrayList<>();

		String sql = "SELECT id, model, licenseno, weight, capacity, date FROM USERS WHERE model LIKE ? AND licenseno LIKE ? AND weight LIKE ? AND capacity LIKE ? AND date LIKE ? ";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(model));
			ps.setString(2, createSearchValue(licenseno));
			ps.setString(3, createSearchValue(weight));
			ps.setString(4, createSearchValue(capacity));
			ps.setString(5, createSearchValue(date));
			ResultSet results = ps.executeQuery();

			while (results.next()) {
				User user = new User(Long.valueOf(results.getInt("id")), results.getString("model"),
						results.getString("licenseno"), results.getString("weight"), results.getString("capacity"), results.getString("date"));
				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return users;
	}

//	private String createSearchValue1(String capacity) {
		// TODO Auto-generated method stub
//		return 0;
//	}

	//private int createSearchValue(int date) {
		// TODO Auto-generated method stub
		//return 0;
	//}

	
	private String createSearchValue(String string) {
		
		String value;
		
		if (StringUtils.isBlank(string)) {
			value = "%";
		} else {
			value = string;
		}
		
		return value;
	}
	
	@Override
	public void add(User user) {
		
		String insertSql = "INSERT INTO USERS (model, licenseno, weight, capacity, date) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {

			ps.setString(1, user.getModel());
			ps.setString(2, user.getLicenseNo());
			ps.setString(3, user.getWeight());
			ps.setString(4, user.getCapacity());
			ps.setString(5, user.getDate());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(User user) {
		String updateSql = "UPDATE users SET model = ?, licenseno = ?, weight = ?, capacity = ?, date = ?WHERE id = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			ps.setString(1, user.getModel());
			ps.setString(2, user.getLicenseNo());
			ps.setLong(3, user.getId());
			ps.setString(4, user.getWeight());
			ps.setString(5, user.getCapacity());
			ps.setString(6, user.getDate());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Long id) {
		String updateSql = "DELETE FROM users WHERE id = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			ps.setLong(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
