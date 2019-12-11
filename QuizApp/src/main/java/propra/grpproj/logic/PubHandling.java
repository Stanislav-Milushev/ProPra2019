package propra.grpproj.logic;

import java.sql.SQLException;

import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.RegisterPub;

////////////////////////////////////////////////////////////////////////////
// Class to register pubs
// 
// @author: Marius Discher
//
//
//



public class PubHandling { 
	
	public void registerPub (RegisterPub pub, String username) throws SQLException {
		
		DatabaseManager db = new DatabaseManager();
		
		boolean success_reg = false;
		
		boolean approved = false;
		
		db.connection();
		
		success_reg = db.registerPub(pub.getName(),pub.getAddress(),approved,pub.getOwnerID());
		
		db.closeconnection();
		
		pub.setSuccess(success_reg);
		
		SocketServer.getInstance().sendObject(pub, username);
		
	}
		
}
