package propra.grpproj.logic;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import propra.grpproj.quiz.repositories.sqlite.*;

public class DatabaseManager {
	
	Connection connection = null;
	
	
	Encrypt encr = new Encrypt();
	
	public void connection() throws SQLException {
		
		connection = SqliteCoreUtilities.connect();
		
	}
	
	public void closeconnection() throws SQLException {
		
		this.connection.close();
		
	}
	
	public boolean registerUser(String username, String email, String passwd) throws SQLException {
		
		boolean r_check = false;
		
		int password = encr.encrypt(passwd);
		
		String userid;
		
		String query = "";
		
		
		
		
		return r_check;
	}

	
	public boolean checkExist(String email) throws SQLException {
		
		// false means empty
		boolean e_check = true;
		
		
		connection();
		String query = "Select email From user Where email =" + email;
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		int columns = rs.getMetaData().getColumnCount();
		
		while (rs.next()) {
		
			for(int i = 1; i<=columns; i++) {
				e_check = isEmpty(rs.getString(i));
			}
		}
		
		rs.close();
		stmt.close();
		
		
		return e_check;
	}
	
	public boolean isEmpty(String value) {
		
		return value.length() == 0;
	}
	
	public boolean login(String email, String password) {
		
		boolean l_check = false;
		
		return l_check;
	}
	
	public String getLatestID() throws SQLException {
		
		String id = "";
		
		String query = "Select max userID from user";
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		return id;
	}
	
	
}
