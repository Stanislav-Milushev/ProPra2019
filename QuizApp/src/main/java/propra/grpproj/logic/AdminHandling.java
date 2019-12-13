package propra.grpproj.logic;

import java.sql.SQLException;
import java.util.ArrayList;

import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.AcceptPub;
import propra.grpproj.quiz.SocketDataObjects.PubList;
import propra.grpproj.quiz.SocketDataObjects.Question;
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
		
		AcceptPub accept = new AcceptPub(name, owner);
		
		SocketServer.getInstance().sendObject(accept, name);
		
		
	}
	
	// Get the whole question pool
	public ArrayList<propra.grpproj.quiz.dataholders.Question> getQuestionPool(String name) throws SQLException {  /// zu schreiben
		
		Iterable<propra.grpproj.quiz.dataholders.Question> questions_pool = new ArrayList<propra.grpproj.quiz.dataholders.Question>();
		
		DatabaseManager db = new DatabaseManager();
		
		return (ArrayList<propra.grpproj.quiz.dataholders.Question>) (questions_pool = db.getPool(name));
		
	}
}
