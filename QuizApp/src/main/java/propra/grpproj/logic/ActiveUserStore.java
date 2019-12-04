package propra.grpproj.logic;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;



////////////////////////////////////////////////////////////////////////////
// User Storage to identify, if user is logged in
// additional a HashSet for all active admin
// @author: Marius Discher
//
//
//


public class ActiveUserStore {

	HashSet<String> activeUser = new HashSet<String>();
	HashSet<String> activeAdmin = new HashSet<String>();
	
	
	public void userLogin (String id) {
		
		activeUser.add(id);
		
	}
	
	public void userLogout (String id) {
		
		activeUser.remove(id);
		
	}
	
	public void adminEntry (String id) {
		
		activeAdmin.add(id);
	}
	
	public void adminOut (String id) {
		
		activeAdmin.remove(id);
	}
	
	
}