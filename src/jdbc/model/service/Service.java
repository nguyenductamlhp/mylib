package jdbc.model.service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.util.*;

public class Service {
	
	public List<T> getAllCLASS()  {
		List<T> list = new ArrayList<>();
		
		try {
			ConnectionPool connectionPool = new ConnectionPool();
			Connection connection = connectionPool.getConnection();
			String query = "select * from TABLENAME";
			
			Statement statement;
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				CLASS class = new CLASS();
				
				class.setID(resultSet.getString("phone"));

				list.add(CLASS);
			}
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	
		return list;
	}
	
	public boolean isExist(String phone) {
		List<CLASS> listCustom = getAllCLASS();
		for (CLASS CLASS : listCustom) {
			if (CLASS.getPhone().equals(phone)) {
				return true;
			}
		}
		return false;
	}
	
	public List<CLASS> findCLASS(String phone) {
		List<CLASS> CLASSList = getAllCLASS();
		List<CLASS> reList = new ArrayList<>();
		
		for (CLASS CLASS : CLASSList) {
			if (CLASS.getPhone().contains(phone.trim())) {
				reList.add(CLASS);
			}
		
		}
		return reList;
	}
	
	public int getCLASS(String phone) throws SQLException {
		List<CLASS> CLASSList = getAllCLASS();
		int i = 0;
		for (CLASS CLASS : CLASSList) {
			if (CLASS.getPhone().equals(phone)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public boolean updateCLASS(CLASS c) {
		ConnectionPool connectionPool = new ConnectionPool();
		Connection connection;
		try {
			if (isExist(c.getPhone())) {
				return false;
			} else {
				connection = connectionPool.getConnection();
				
				String updateCLASS = "update custom set phone='" + c.getPhone() + "' , address='";
				updateCLASS +=  c.getName() + "', address='";
				updateCLASS +=  c.getAddress() + "', note='";
				updateCLASS +=  c.getNote() + "' where phone='" + c.getPhone() + "'";
				
				Statement statement;
				statement = connection.createStatement();
				statement.executeUpdate(updateCLASS);
				
				connection.close();
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean addCLASS(CLASS c) {
		ConnectionPool connectionPool = new ConnectionPool();
		Connection connection;
		try {
			if (isExist(c.getID())) {
				return false;
			} else {
				connection = connectionPool.getConnection();
				String insertCLASS = "insert into TABLE values ('" + c.getID() + "', '";
				insertCLASS += c.getName() + "', '";
				
				Statement statement;
				statement = connection.createStatement();
				statement.executeUpdate(insertCLASS);
				connection.close();
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean deleteCLASS(String phone) {
		ConnectionPool connectionPool = new ConnectionPool();
		Connection connection;
		try {
			connection = connectionPool.getConnection();
			String insertCLASS = "delete from custom where phone='" + phone + "'";
			
			Statement statement;
			statement = connection.createStatement();
			statement.executeUpdate(insertCLASS);
			connection.close();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}
}
