package propra.grpproj.logic;

import java.sql.SQLException;
import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////
// Admin functions
// 
// @author: Marius Discher
//
//
//




public class AdminHandling {

	
	// Function to get all registered user
	public void getAllUsers () throws SQLException {
		
		DatabaseManager db = new DatabaseManager();
		
		db.connection();
		
		db.getAllUser();
		
		db.closeconnection();
		
		
	}
	
	
	// Function to approve a pub
	public void approvePub(String name) throws SQLException {
		
		DatabaseManager db = new DatabaseManager();
		db.connection();
		db.approvePub(name);
		db.closeconnection();
		
		
	}
	
	
	
	// Function to give a list off all registered pubs to the administrative GUI
	public ArrayList<String> getAllPubs() throws SQLException{
		
		ArrayList <String> pubs = new ArrayList<String>();
		
		DatabaseManager db = new DatabaseManager();
		db.connection();
		
		db.getPubs();
		
		db.closeconnection();
		return pubs;
		
	}
}
