package propra.grpproj.quiz.SocketDataObjects;

public class Login {
	private String userName;
	private String password;
	private UserType type = null;
	
	/**
	 * 
	 * @param userName user input
	 * @param password user input
	 * @author Yannick
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
}
