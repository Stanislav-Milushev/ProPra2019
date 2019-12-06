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

	
	public void getAllUsers () {
		
	}
	
	public void approvePub() {
		
		
	}
	
	public ArrayList<String> getAllPubs() throws SQLException{
		
		ArrayList <String> pubs = new ArrayList<String>();
		
		DatabaseManager db = new DatabaseManager();
		db.connection();
		
		db.getPubs();
		
		db.closeconnection();
		return pubs;
		
	}
	
	
}
