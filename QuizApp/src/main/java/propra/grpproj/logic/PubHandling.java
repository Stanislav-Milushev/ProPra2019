package propra.grpproj.logic;

import java.sql.SQLException;

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
		
		// Send success to GUI back
		
	}
		
}
