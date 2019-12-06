package propra.grpproj.logic;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import propra.grpproj.quiz.repositories.sqlite.*;

public class DatabaseManager {
	
	
	private ArrayList<String> pubs = new ArrayList<String>();
	
	
	Connection connection = null;
	
	
	Encrypt encr = new Encrypt();
	
	
	
	// Create connection to the database
	public void connection() throws SQLException {
		
		connection = SqliteCoreUtilities.connect();
		
	}
	
	
	
	// Close the connection 
	public void closeconnection() throws SQLException {
		
		this.connection.close();
		
	}
	
	
	
	// Function to register a user
	// @returntype = boolean
	public boolean registerUser(String username, String email, String passwd) throws SQLException {
		
		boolean r_check = false;
		
		int password = encr.encrypt(passwd);
		
		String userid;
		
		String query = "";
		
		
		
		
		return r_check;
	}

	
	
	// Function to check for an email of a user
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
	
	
	// Function to check, if result of a query is empty
	public boolean isEmpty(String value) {
		
		return value.length() == 0;
	}
	
	
	
	// Function to authenticate login of a user
	public boolean login(String email, String password) {
		
		boolean l_check = false;
		
		return l_check;
	}
	
	
	// Get the latest and unused userID from the Database
	public String getLatestID() throws SQLException {
		
		String id = "";
		
		String query = "Select max userID from user";
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
		
		return id;
	}
	
	
	
	// Get all registered pubs
	public ArrayList<String> getPubs() throws SQLException {
		
		String query ="Select name, owner, id from pub";
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
		
		return pubs;
		
		// Maybe create Pub Objects and send them to the gui
		
	}
	
	
	
	// Function to handle the approve of a pub
	public void approvePub(String name) throws SQLException {
		
		String query = "Update pub on approved value true where name =" + name;
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
		
	}
	
	
	
	// Function to get all registered users
	public void getAllUser() throws SQLException {
		
		String query = "Select name, email from user";
				
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
		
	}
	
	
	
	// Function to get the encrypted password from the database
	public int getEncryptedPassword (String email) throws SQLException {
		
		int i = 0;
		
		String query = "Select password from user Where email =" + email;
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
		
		return i;
	}
	
	
	
	// Write the score after a completed quiz to the user db
	public void writePoints (String id, int points) throws SQLException {
		
		String query = "Update user on score value " + points;
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
		
		
		
	}
	
	
	
	// Get the score from a user
	public int getScore(String id) throws SQLException {
		
		int i = 0;
		
		String query = "Select score from user Where id =" + id;
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
		
		return i;
	}
	
	
	
	// Function to reset the score from all users
	public void resetPoints() throws SQLException {
		
		String query = "Update user on score value = 0";
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
		
	}
	
	
}
