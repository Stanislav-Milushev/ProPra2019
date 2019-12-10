package propra.grpproj.logic;

import java.sql.SQLException;
import propra.grpproj.quiz.SocketDataObjects.*;

////////////////////////////////////////////////////////////////////////////
// Handle all the user related actions (login, logout, delete, register)
// 
// @author: Marius Discher
//
//
//
//


public class UserHandling 
{
	
	
	/**
	 * Method to register a user in the database
	 * Calls the function in the DatabaseManager
	 * 
	 */ 
	public void user_register(String username, String email, String passwd, UserType usertype) throws SQLException 
	{
		
		DatabaseManager db = new DatabaseManager();
		
		boolean success_reg = false;
		
		db.connection();
		
		success_reg = db.registerUser(username, email, passwd);
		
		db.closeconnection();
		
		if ( success_reg == true ) {
			
			user_login(email,passwd);
			
		} else {
			
			// Send Message to GUI: Failed to register the User
		}
		
		// Send success to GUI back
		
	}
	
	

	/**   
	 *  Method to log a user in the quiz system
	 *  Calls the function inside the DatabaseManager
	 *  
	 */
	public void user_login(String email, String passwd) throws SQLException 
	{
		
		DatabaseManager db = new DatabaseManager();
		
		ActiveUserStore aus = new ActiveUserStore();
		
		boolean success_login; 
		
		db.connection();
		
		success_login = db.login(email,passwd);
		
		UserType usertype = db.getUserType(email);
		
		db.closeconnection();
		
		aus.userLogin(email);
		
		// Switch case for usertype
		
		if (success_login == true) 
		{
			switch (usertype) 
			{
			
				case DEFAULT: 
				{
					// Int 2 = Menu user
					break;
				}
				case ADMIN:
				{
					// Int 3 = Admin menu
					break;
				}
				case PUBOWNER: 
				{
					// Int 4 = Pubowner menu
					break;
				}
				case ADMINPUBOWNER: 
				{
					// Int 3 or 4 You can switch between both
					break;
				
				}
				default: 
				{
					// Int 5 = Error message: type is not clear 
				
				}
			}
		} else {
			
			// Int 6 = Error login
		}
	
		// Send object to GUI 
		// Case 1: login successfully
		// Case 2: password incorrect 
		// Case 3: Email wrong
		// For 1 boolean = true, 2 = false and 3 = false

	}
	
	
	// Method to create a temp user
	public void createTempUser () throws SQLException 
	{
		boolean success = false;
		
		DatabaseManager db = new DatabaseManager();
		
		db.connection();
		
		int id = db.getLatestID();
		
		String email = "temp" + id + "@krombacher_quiz.de";
		
		String name = "temp" + id;
		
		String passwd = "temporaer";
		
		success = db.registerUser(name, email, passwd);
		
		if ( success == true) {
			
			db.login(email, passwd);
			
		} else {
			
			// Int 7 = Cannot register the temp User
		}
		
		db.closeconnection();
		
		
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
