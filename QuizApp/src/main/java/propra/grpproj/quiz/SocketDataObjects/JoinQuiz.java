package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class JoinQuiz implements Serializable{
	private String username;
	private int quizID;
	
	/**
	 * 
	 * @param username
	 * @param quizID
	 */
	
	public JoinQuiz(String username, int quizID) {
		this.username = username;
		this.quizID = quizID;
	}

	public String getUsername() {
		return username;
	}

	public int getQuizID() {
		return quizID;
	}
}
