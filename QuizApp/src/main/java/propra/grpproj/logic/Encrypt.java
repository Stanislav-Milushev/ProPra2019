package propra.grpproj.logic;

import java.sql.SQLException;

////////////////////////////////////////////////////////////////////////////
// Encrypt functions and password check
// All the functions are private except for delete() and login()
// @author: Marius Discher
//
//
//




public class Encrypt {

	private final int prim = 234312413;
	
	
	
	// authentication of the password for account delete
	public boolean delete (String id, String password) {
	
		int i,ii;
		boolean password_check;
		
		i = encrypt(password);
		ii = getPasswordhash();
		
		if (i==ii) {
			password_check = true;
		} else {
			password_check = false;
		}
		
		return password_check;
		
	}
	
	
	
	// authentication of the password for user login
	public boolean login (String email, String password) throws SQLException {
		
		boolean check = false;
		
		int e_full,e_full_input;
		
		e_full_input = encrypt(email, password);
		
		e_full = encryptDatabase(email);
		
		
		
		if (e_full == e_full_input) {
			
			check = true;
			
		} 
		
		return check;
	}
	
	
	
	// encrypt function for email and password
	private int encrypt(String email, String password) {
		
		int i,ii,iii;
		
		i = email.hashCode();
		
		ii = password.hashCode();
		
		iii = prim*i*ii;
		
		return iii;
		
	}
	
	
	// encrypt function for password or email
	public int encrypt(String text) {
		
		int i; 
		i= text.hashCode();
		return i;
	}
	
	
	
	// function to get the email and encrypted password from the user
	private int encryptDatabase(String email) throws SQLException {
		
		
		DatabaseManager db = new DatabaseManager();
		
		db.connection();
		
		int passwd = db.getEncryptedPassword(email);
		
		db.closeconnection();
		
		int i,iii;
		
		i = email.hashCode();
		
		iii = prim*i*passwd;
		
		return iii;
	}
	
	
	// ????? Dunno for what, just ignore
	private int getPasswordhash() {
		int i;
		i = 1;
		return i;
	}
}
