package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class DeleteUser implements Serializable{
	private String username;

	public DeleteUser(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
}
