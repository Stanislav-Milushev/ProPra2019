package propra.grpproj.quiz.Socket.SocketDataObjects;

public class CreateConnection {
	private String userName;
	private String pwd;
	private String mail;
	
	public CreateConnection(String userName, String password, String mail) {
		this.userName = userName;
		pwd = password;
		this.mail = mail;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return pwd;
	}
	
	public String getMail() {
		return mail;
	}
}
