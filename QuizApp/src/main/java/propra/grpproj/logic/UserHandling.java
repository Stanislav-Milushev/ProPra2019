package propra.grpproj.logic;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import propra.grpproj.quiz.SocketDataObjects.*;



////////////////////////////////////////////////////////////////////////////
// Handle all the user related actions (login, logout, delete, register)
// 
// @author: Marius Discher
//
//
//


public class UserHandling 
{
	
	
	// Method to register a user in the database
	// Calls the function in the DatabaseManager
	public void user_register(String username, String email, String passwd) throws SQLException 
	{
		
		DatabaseManager db = new DatabaseManager();
		
		boolean success_reg = false;
		
		UserType usertype;
		
		db.connection();
		
		usertype = db.getUserType(email);
		
		success_reg = db.registerUser(username, email, passwd);
		
		db.closeconnection();
		
		if ( success_reg == true ) {
			
			user_login(email,passwd,usertype);
			
		} else {
			
			// Send Message to GUI: Failed to register the User
		}
		
		// Send success to GUI back
		
	}
	
	

	// Method to log a user in the quiz system
	// Calls the function inside the DatabaseManager
	public void user_login(String email, String passwd, UserType usertype) throws SQLException 
	{
		
		DatabaseManager db = new DatabaseManager();
		
		ActiveUserStore aus = new ActiveUserStore();
		
		boolean success_login; 
		
		db.connection();
		
		success_login = db.login(email,passwd);
		
		db.closeconnection();
		
		aus.userLogin(email);
		
	
		// Send object to GUI 
		// Case 1: login successfully
		// Case 2: password incorrect 
		// Case 3: Email wrong
		// For 1 boolean = true, 2 = false and 3 = false

	}
	
	
	// Method to create a temp user
	public void createTempUser () throws SQLException 
	{

		DatabaseManager db = new DatabaseManager();
		
		db.connection();
		
		int id = db.getLatestID();
		
		String email = "temp" + id + "@krombacher_quiz.de";
		
		String name = "temp" + id;
		
		String passwd = "temporaer";
		
		db.registerUser(name, email, passwd);
		
		db.closeconnection();
		
		// Dont need to give a return value to the gui
		// The register function will do this
		
		
		
		
	}
	
	
	// log the user out
	public void logout (String email) {
		
		boolean success_logout = false;
		
		ActiveUserStore aus = new ActiveUserStore();
		
		aus.userLogout(email);
		
		success_logout = aus.search(email);
		
		
	}
	
	
	// delete the user after successfully authenticated
	public boolean deleteUser (String id, String passwd) 
	{
		
		// Check the password
		
		Encrypt e = new Encrypt();
		boolean success; 
		success = e.delete(id,passwd);
		return success;
		
		
		
		
		
	}
	
	

}
