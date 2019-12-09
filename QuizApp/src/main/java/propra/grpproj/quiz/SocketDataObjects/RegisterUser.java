package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class RegisterUser implements Serializable{
	private String username;
	private String password;
	private String mail;
	
	public RegisterUser(String username, String password, String mail) {
		this.username = username;
		this.password = password;
		this.mail = mail;
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
	
}
