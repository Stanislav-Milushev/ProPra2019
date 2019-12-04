package propra.grpproj.logic;

////////////////////////////////////////////////////////////////////////////
// Only start one time!
// Initialize the admin and all the activeUser HashSets
// @author: Marius Discher
//
//



public class Start {
	
	public void start() { 
		
		ActiveUserStore a = new ActiveUserStore();
		String id = "1234";
		// Have to get the id from the DB!
		
		a.adminEntry(id);
		
		// For more then one admin build a loop
	}

}
