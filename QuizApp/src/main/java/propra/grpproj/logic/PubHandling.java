package propra.grpproj.logic;

import java.sql.SQLException;
import java.util.ArrayList;

import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.Pub;
import propra.grpproj.quiz.SocketDataObjects.PubList;
import propra.grpproj.quiz.SocketDataObjects.RegisterPub;

////////////////////////////////////////////////////////////////////////////
// Class to register pubs
// 
// @author: Marius Discher
//
//
//



public class PubHandling { 
	
	/**
	 * Tries to register the pub in the database, sends client success
	 * @param pub
	 * @param username
	 * @throws SQLException
	 * @author Yannick, Marius Discher
	 */
	public static void registerPub (RegisterPub pub, String username) throws SQLException {
		
		DatabaseManager db = new DatabaseManager();
		
		boolean success_reg = false;
		
		boolean approved = false;
		
		db.connection();
		
		success_reg = db.registerPub(pub.getName(),pub.getAddress(),approved,pub.getOwnerID());
		
		db.closeconnection();
		
		pub.setSuccess(success_reg);
		
		SocketServer.getInstance().sendObject(pub, username);
		
	}
	
	/**
	 * Returns a copy of the database pubs
	 * @param pub
	 * @param username
	 * @throws SQLException
	 * @author Yannick
	 */
	public static void getPubs (PubList pub, String username) throws SQLException {
		ArrayList<Pub> pubs = new ArrayList<Pub>();
		//TODO Füllen
		
		pub.setList(pubs);
		
		SocketServer.getInstance().sendObject(pub, username);
	}
		
}
