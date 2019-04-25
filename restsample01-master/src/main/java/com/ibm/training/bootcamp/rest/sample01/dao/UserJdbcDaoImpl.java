package com.ibm.training.bootcamp.rest.sample01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.jdbc.JDBCDataSource;

import com.ibm.training.bootcamp.rest.sample01.domain.User;

public class UserJdbcDaoImpl<Int> implements UserDao {

	@SuppressWarnings("rawtypes")
	private static UserJdbcDaoImpl INSTANCE;

	private JDBCDataSource dataSource;

	@SuppressWarnings("rawtypes")
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

	private void init() {
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:mem:USER");
		dataSource.setUser("username");
		dataSource.setPassword("password");

		createUserTable();
		insertInitUsers();

	}

	private void createUserTable() {
		String createSql = "CREATE TABLE USERS " + "(id INTEGER IDENTITY PRIMARY KEY, " + " model VARCHAR(255), "
				+ " licenseno VARCHAR(255), " + " weight VARCHAR(255), " + " capacity VARCHAR(255), " + "dateAcquired VARCHAR(255))" ;

		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(createSql);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void insertInitUsers() {
		
		add(new User());
		add(new User("Nissan","ZXC 1223", "90", "9000", "April 22 2019"));
		add(new User("Toyota","JHG 5432", "90", "9000", "April 23 2019"));
		add(new User("Toyota","GTH 9876", "90", "9000", "April 24 2019"));
		add(new User("Nissan","PYT 7639", "90", "9000", "April 25 2019"));
	}

	@Override
	public List<User> findAll() {

		return findByName(null, null, null, null, null);
	}

	@Override
	public User find(Long id) {

		User user = null;

		if (id != null) {
			String sql = "SELECT id, model, licenseno, weight, capacity, dateAcquired FROM USERS where id = ?";
			try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, id.intValue());
				ResultSet results = ps.executeQuery();

				if (results.next()) {
					user = new User(Long.valueOf(results.getInt("id")), results.getString("model"),
							results.getString("licenseno"), results.getString("weight"), results.getString("capacity"), results.getString("dateAcquired"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return user;
	}

	@Override
	public List<User> findByName(String model, String licenseno, String weight, String capacity, String dateAcquired) {
		List<User> users = new ArrayList<>();

		String sql = "SELECT id, model, licenseno, weight, capacity, dateAcquired FROM USERS WHERE model LIKE ? AND licenseno LIKE ? AND weight LIKE ? AND capacity LIKE ? AND dateAcquired LIKE ? ";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(model));
			ps.setString(2, createSearchValue(licenseno));
			ps.setString(3, createSearchValue(weight));
			ps.setString(4, createSearchValue(capacity));
			ps.setString(5, createSearchValue(dateAcquired));
			
			
			ResultSet results = ps.executeQuery();

			while (results.next()) {
				User user = new User(Long.valueOf(results.getInt("id")), results.getString("model"),
						results.getString("licenseno"), results.getString("weight"), results.getString("capacity"), results.getString("dateAcquired"));
				users.add(user);
			}

		} catch (SQLException e) {
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
		
		String insertSql = "INSERT INTO USERS (model, licenseno, weight, capacity, dateAcquired) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {

			ps.setString(1, user.getModel());
			ps.setString(2, user.getLicenseNo());
			ps.setString(3, user.getWeight());
			ps.setString(4, user.getCapacity());
			ps.setString(5, user.getDateAcquired());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(User user) {
		String updateSql = "UPDATE users SET model = ?, licenseno = ?, weight = ?, capacity = ?, dateAcquired = ? WHERE id = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			ps.setString(1, user.getModel());
			ps.setString(2, user.getLicenseNo());
			ps.setLong(3, user.getId());
			ps.setString(4, user.getWeight());
			ps.setString(5, user.getCapacity());
			ps.setString(6, user.getDateAcquired());
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
