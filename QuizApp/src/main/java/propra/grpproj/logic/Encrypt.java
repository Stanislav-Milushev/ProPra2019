package propra.grpproj.logic;

////////////////////////////////////////////////////////////////////////////
// Encrypt functions and password check
// All the functions are private except for delete() and login()
// @author: Marius Discher
//
//
//




public class Encrypt {

	private final int prim = 234312413;
	
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
	
	public boolean login (String email, String password) {
		boolean check = false;
		int e_full,e_full_input;
		
		e_full_input = encrypt(email, password);
		e_full = encryptDatabase(email);
		
		
		
		if (e_full == e_full_input) {
			check = true;
		} else {
			check = false;
		}
		
		return check;
	}
	
	
	private int encrypt(String email, String password) {
		
		int i,ii,iii;
		i = email.hashCode();
		ii = password.hashCode();
		iii = prim*i*ii;
		System.out.println(iii);
		return iii;
		
	}
	
	public int encrypt(String text) {
		
		int i; 
		i= text.hashCode();
		return i;
	}
	
	
	private int encryptDatabase(String email) {
		
		int i,ii,iii;
		String password = "Hallo";
		
		// Get Password from Database! 
		// If the email exist, gets checked before this function get called
		// password is saved encrypted in the DB
		
		i = email.hashCode();
		ii = password.hashCode();
		iii = prim*i*ii;
		System.out.println(iii);
		
		return iii;
	}
	
	private int getPasswordhash() {
		int i;
		i = 1;
		return i;
	}
}
