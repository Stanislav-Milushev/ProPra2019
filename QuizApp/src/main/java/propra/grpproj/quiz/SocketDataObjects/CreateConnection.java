package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class CreateConnection implements Serializable{
	private String userName;
	
	/**
	 * 
	 * @param userName
	 */
	
	public CreateConnection(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
}
