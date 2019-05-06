package com.ibm.training.bootcamp.rest.sample01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.rest.sample01.domain.Usersched;

public  class UserschedJdbcDaoImpl extends Trucking implements UserschedDao {

	private static UserschedJdbcDaoImpl INSTANCE;

	private UserschedJdbcDaoImpl() {
		init();
	}

	static public UserschedJdbcDaoImpl getInstance() {

		UserschedJdbcDaoImpl instance = null;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new UserschedJdbcDaoImpl();
			UserJdbcDaoImpl.getInstance();
			INSTANCE = instance;
		}

		return instance;
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
	public List<Usersched> findAll() {
		System.out.println("userschedjdbc findall");
		return findByName(null, null, null, null, null);
	}

	@Override
	public Usersched find(Long id) {

		Usersched user = null;

		if (id != null) {
			String sql = "SELECT id, name, load, dtstart, dtend, status FROM USERS where id = ?";
			try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, id.intValue());
				ResultSet results = ps.executeQuery();

				if (results.next()) {
					user = new Usersched(Long.valueOf(results.getInt("id")), results.getString("name"),
							results.getString("load"), results.getString("dtstart"), results.getString("dtend"),
							results.getString("status"));
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return user;
	}

	@Override
	public List<Usersched> findByName(String name, String load, String dtstart, String dtend, String status) {
		List<Usersched> users = new ArrayList<>();

		String sql = "SELECT id, name, load, dtstart, dtend, status FROM USERSCHED WHERE name LIKE ? AND load LIKE ? AND dtstart LIKE ? AND dtend LIKE ? AND status LIKE ? ";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, createSearchValue(name));
			ps.setString(2, createSearchValue(load));
			ps.setString(3, createSearchValue(dtstart));
			ps.setString(4, createSearchValue(dtend));
			ps.setString(5, createSearchValue(status));
			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Usersched usersched = new Usersched(Long.valueOf(results.getInt("id")), results.getString("name"),
						results.getString("load"), results.getString("dtstart"), results.getString("dtend"),
						results.getString("status"));
				users.add(usersched);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return users;
	}

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
	public void add(Usersched usersched) {

		String insertSql = "INSERT INTO USERSCHED (name, load, dtstart, dtend, status) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {

			ps.setString(1, usersched.getName());
			ps.setString(2, usersched.getLoad());
			ps.setString(3, usersched.getDtstart());
			ps.setString(4, usersched.getDtend());
			ps.setString(5, usersched.getStatus());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Usersched usersched) {
		String updateSql = "UPDATE usersched SET name = ?, load = ?, dtstart = ?, dtend = ?, status = ? WHERE id = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			ps.setString(1, usersched.getName());
			ps.setString(2, usersched.getLoad());
			ps.setLong(3, usersched.getId());
			ps.setString(4, usersched.getDtstart());
			ps.setString(5, usersched.getDtend());
			ps.setString(6, usersched.getStatus());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Long id) {
		String updateSql = "DELETE FROM usersched WHERE id = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {

			ps.setLong(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

//	@Override
//	public void delete1(Long tripid) {
//		String updateSql = "DELETE FROM users WHERE tripid = ?";
//
//		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(updateSql)) {
//
//			ps.setLong(1, tripid);
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}

}
