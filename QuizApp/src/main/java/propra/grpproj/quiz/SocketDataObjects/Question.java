package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class Question implements Serializable{
	private int ID;
	private String question;
	private String[] answers;
	
	public int getID() {
		return ID;
	}

	public String getQuestion() {
		return question;
	}

	public String[] getAnswers() {
		return answers;
	}
	public Question(int iD, String question, String[] answers) {
		this.ID = iD;
		this.question = question;
		this.answers = answers;
	}
}
