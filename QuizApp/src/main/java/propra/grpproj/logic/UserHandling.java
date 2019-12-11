package propra.grpproj.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.*;

////////////////////////////////////////////////////////////////////////////
// Handle all the user related actions (login, logout, delete, register)
// 
// @author: Marius Discher & Stanislav Miushev
//
//
//
//


public class UserHandling 
{
	
	
	/**
	 * Method to register a user in the database
	 * Calls the function user_register in the DatabaseManager
	 * 
	 */ 
	public void user_register(String username, String email, String passwd, UserType usertype) throws SQLException 
	{
		
		DatabaseManager db = new DatabaseManager();
		
		boolean success_reg = false;
		
		db.connection();
		
		success_reg = db.registerUser(username, email, passwd, usertype);
		
		db.closeconnection();
		
		if ( success_reg == true ) {
			
			user_login(new Login(username, passwd));
			
		} else if(success_reg == false) {
			RegisterUser registerfail = new RegisterUser(username,email,passwd,usertype);
			registerfail.setRegisterProg(success_reg);
			SocketServer.getInstance().sendObject(registerfail, username);
<<<<<<< HEAD
		}
		
=======
		}		
>>>>>>> 60c80a411a38272f5cf435c63a5306642fd50f13
	}
	
	

	/**   
	 *  Method to log a user in the quiz system
	 *  Calls the function inside the DatabaseManager
	 *  
	 */
	public void user_login(Login login) throws SQLException 
	{
		String username = login.getUserName();
		String passwd = login.getPassword();
		
		DatabaseManager db = new DatabaseManager();
		
		ActiveUserStore aus = new ActiveUserStore();
		
		boolean success_login; 
		
		db.connection();
		
		success_login = db.login(username,passwd); //funktioniert das immernoch mit username statt email?
		
		UserType usertype = db.getUserType(username);
		
		db.closeconnection();
		
		aus.userLogin(username);
		
		if (!success_login == true) {
			usertype = UserType.ERROR;
		}
		
		login.setType(usertype);
		
		SocketServer.getInstance().sendObject(login, username);
	
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
		
		UserType usertype= UserType.DEFAULT; 
		
		success = db.registerUser(name, email, passwd,usertype);
		
		if ( success == true) {
			
			db.login(email, passwd);
			
		} else {
			
			// Int 7 = Cannot register the temp User
		}
		
		db.closeconnection();
		
		
	}
	
	
	// log the user out
	public void logout (String username) {
		
		boolean success_logout = false;
		
		
	}
	
	
	// delete the user after successfully authenticated
	// checks if user exist then deletes from Database
	// sends DeleteUser back to Gui
	public boolean deleteUser (String username, String passwd) throws SQLException 
	{
		
		boolean del_check = true;
		
		DatabaseManager db = new DatabaseManager();
		
		db.connection();
		
		String query = "Select password From user Where id =" + username;
		
		Statement stmt = db.connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
	
		int columns = rs.getMetaData().getColumnCount();
		
		while (rs.next()) {
			
			for(int i = 1; i<=columns; i++) {
				del_check = db.isEmpty(rs.getString(i));
			}
		}
		
		rs.close();
		stmt.close();
		
		if (del_check) {
			String queryD = "Delete From user Where id =" + username;
			Statement stmtD = db.connection.createStatement();
			ResultSet rsD = stmt.executeQuery(query);
			rsD.close();
			stmtD.close();
		}
		db.closeconnection();
		
		return del_check;
		
		
		
	}
}
