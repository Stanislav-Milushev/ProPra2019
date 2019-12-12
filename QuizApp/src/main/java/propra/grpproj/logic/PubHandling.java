package propra.grpproj.logic;

import java.sql.SQLException;
import java.util.List;

import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.ChangePub;
import propra.grpproj.quiz.SocketDataObjects.Pub;
import propra.grpproj.quiz.SocketDataObjects.PubList;
import propra.grpproj.quiz.SocketDataObjects.RegisterPub;

////////////////////////////////////////////////////////////////////////////
// Class to register pubs
// 
// @author: Marius Discher & Stanislav Milushev
//
//
//



public class PubHandling { 

	
	public void registerPub (String name, String address, int ownerid) throws SQLException {
		
		DatabaseManager db = new DatabaseManager();
		
		boolean success_reg = false;
		
		boolean approved = false;
		
		db.connection();
		
		success_reg = db.registerPub(name,address,approved,ownerid);
		
		db.closeconnection();
		
		RegisterPub regProg = new RegisterPub(name,address,ownerid);
		
		regProg.setRegisterProg(true);
		
		SocketServer.getInstance().sendObject(regProg, name);
		
	}
	
	/**
	 * Edits a pub in the database
	 * @param cp
	 * @author Yannick
	 */
	public static void editPub(ChangePub cp) {
		
	}
	
	/**
	 * Sends the user a copy of all pubs
	 * @param list
	 * @param username
	 * @author Yannick
	 */
	public static void sendPubListToUser(PubList list, String username) {
		List<Pub> pList = null; //Fill from db
		
		list.setList(pList);
		SocketServer.getInstance().sendObject(list, username);
	}
		
}
