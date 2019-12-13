package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class DeleteQuestion implements Serializable{
	private int questionID;
	
	
	/**
	 * 
	 * @param questionID
	 */
	
	public DeleteQuestion(int questionID) {
		this.questionID = questionID;
	}
	
	public int getQuestionID() {
		return questionID;
	}
}
