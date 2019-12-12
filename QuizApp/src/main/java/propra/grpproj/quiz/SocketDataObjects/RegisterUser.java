package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;


public class RegisterUser implements Serializable{
	private String username;
	private String password;
	private String mail;
	private UserType usertype;
	private boolean registerProg;
	
	public RegisterUser(String username, String password, String mail,UserType usertype2) {
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.usertype= usertype2;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getMail() {
		return mail;
	}

	public boolean isRegisterProg() {
		return registerProg;
	}

	public void setRegisterProg(boolean registerProg) {
		this.registerProg = registerProg;
	}

	public UserType getUsertype() {
		return usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}
	
}
