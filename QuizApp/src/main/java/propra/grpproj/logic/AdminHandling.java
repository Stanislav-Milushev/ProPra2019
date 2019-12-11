package propra.grpproj.logic;

import java.sql.SQLException;
import java.util.ArrayList;

import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.AcceptPub;
import propra.grpproj.quiz.SocketDataObjects.PubList;
////////////////////////////////////////////////////////////////////////////
// Admin functions
// 
// @author: Marius Discher
//
//
//
//
import propra.grpproj.quiz.dataholders.Pub;



public class AdminHandling {
	
	// Function to approve a pub
	public void approvePub(String name, String owner) throws SQLException {
		
		DatabaseManager db = new DatabaseManager();
		
		db.connection();
		
		db.approvePub(name,owner);
		
		db.closeconnection();
		
		
		AcceptPub accept = new AcceptPub(name, owner);
		SocketServer.getInstance().sendObject(accept, name);
		
		
	}
	
	
	
	// Function to give a list off all registered pubs to the administrative GUI
	public ArrayList<Pub> getAllPubs() throws SQLException{
		
		ArrayList<Pub> pubs = new ArrayList<Pub>();
		
		DatabaseManager db = new DatabaseManager();
		db.connection();
		
		pubs = db.getPubs();
		
		db.closeconnection();
		return pubs;
		
		//PubList publist = new PubList(pubs);
		//SocketServer.getInstance().sendObject(publist,name );
		
		
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
