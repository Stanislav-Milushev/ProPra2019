package propra.grpproj.logic;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;



////////////////////////////////////////////////////////////////////////////
// User Storage to identify, if user is logged in or not
// additional is a HashSet for all active admin
// @author: Marius Discher
//
//
//
//


public class ActiveUserStore {

	// User storage for normal users (registered and temporary)
	HashSet<String> activeUser = new HashSet<String>();
	
	// User storage only for admin
	HashSet<String> activeAdmin = new HashSet<String>();
	
	
	
//////////////////////////////////////////// Methods
	
	// Add a newly logged in user
	public void userLogin (String id) {
		
		activeUser.add(id);
		
	}
	
	
	// Remove the user from the storage
	public void userLogout (String id) {
		
		activeUser.remove(id);
		
	}
	
	
	// add a new admin 
	public void adminEntry (String id) {
		
		activeAdmin.add(id);
	}
	
	
	// remove an admin
	public void adminOut (String id) {
		
		activeAdmin.remove(id);
	}
	
	
	
	// search the HashSet for a specific user (email,id,name)
	public boolean search(String id) {
		
		boolean check = false;
		
		check = activeUser.contains(id);
		
		return check;
	}
}