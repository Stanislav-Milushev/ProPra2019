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
		
		db.approvePub(name,owner);
		
		AcceptPub accept = new AcceptPub();
		
		SocketServer.getInstance().sendObject(accept, name);
		
		
	}
	
	public ArrayList<String> getQuestionPool(String name) throws SQLException {  /// zu schreiben
		
		ArrayList <String> questions_pool = new ArrayList<String>();
		
		DatabaseManager db = new DatabaseManager();
		
		db.getPool(name);
		
		return questions_pool;
		
	}
}
