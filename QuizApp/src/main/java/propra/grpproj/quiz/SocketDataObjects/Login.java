package propra.grpproj.quiz.Socket.SocketDataObjects;

public class Login {
	private String userName;
	
	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getMail() {
		return mail;
	}

	private String password;
	private String mail;
	private UserType type = null;
	
	public Login(String userName, String password, String mail) {
		this.userName = userName;
		this.password = password;
		this.mail = mail;
	}
	
}
