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

	
	public void registerPub (String name, String address, int ownerid) throws SQLException {
		
		DatabaseManager db = new DatabaseManager();
		
		boolean success_reg = false;
		
		boolean approved = false;
		
		db.connection();
		
		success_reg = db.registerPub(name,address,approved,ownerid);
		
		db.closeconnection();
		RegisterPub regProg = new RegisterPub(name,address,ownerid);
		SocketServer.getInstance().sendObject(regProg, name);
		
	}
		
}
