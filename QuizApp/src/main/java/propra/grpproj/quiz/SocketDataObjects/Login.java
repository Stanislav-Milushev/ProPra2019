package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;


public class Login implements Serializable{
	private String userName;
	private String password;
	private UserType type = UserType.DEFAULT;
	private boolean logged = false;
	
	/**
	 * 
	 * @param userName user input
	 * @param password user input
	 */
	
	public Login(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}	
	
	public UserType getType() {
		return type;
	}

	/**
	 * Set by server and sent to client
	 * @param type usertype
	 */
	
	public void setType(UserType type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	
}
