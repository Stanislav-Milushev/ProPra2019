package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class DeleteUser implements Serializable{
	private String username;
	private boolean deleteProgress;
	
	/**
	 * 
	 * @param username
	 * @param deleteProgress
	 */

	public DeleteUser(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public boolean isDeleteProgress() {
		return deleteProgress;
	}

	public void setDeleteProgress(boolean deleteProgress) {
		this.deleteProgress = deleteProgress;
	}
	
}
