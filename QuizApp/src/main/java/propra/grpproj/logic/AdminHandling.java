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
	public void approvePub(String name, String owner) throws SQLException {
		
		DatabaseManager db = new DatabaseManager();
		
		db.connection();
		
		db.approvePub(name,owner);
		
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
	
	
	// Get all the questions from the db
	public ArrayList<String> getAllQuestions() throws SQLException {
		
		ArrayList <String> questions = new ArrayList<String>();
		
		DatabaseManager db = new DatabaseManager();
		
		db.connection();
		
		db.getAllQuestions();
		
		db.closeconnection();
		
		return questions;
		
		
	}
	
	public ArrayList<String> getQuestionPool(String name) throws SQLException {
		
		ArrayList <String> questions_pool = new ArrayList<String>();
		
		DatabaseManager db = new DatabaseManager();
		
		db.connection();
		
		db.getPool(name);
		
		db.closeconnection();
		
		return questions_pool;
		
	}
}
