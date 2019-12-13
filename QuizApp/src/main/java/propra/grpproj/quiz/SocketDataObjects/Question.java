package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class Question implements Serializable{
	private int ID;
	private String question;
	private String[] answers;
	private String explanation;
	private String rightAnswer;
	
	/**
	 * 
	 * @param ID
	 * @param question
	 * @param answers
	 * @param explanation
	 */
	
	public int getID() {
		return ID;
	}

	public String getQuestion() {
		return question;
	}

	public String[] getAnswers() {
		return answers;
	}
	
	public String getExplanation() {
		return explanation;
	}
	
	public Question(int iD, String question, String[] answers, String explanation) {
		this.ID = iD;
		this.question = question;
		this.answers = answers;
		this.explanation = explanation;
	}
	
	public Question(int iD, String question, String[] answers) {
		this.ID = iD;
		this.question = question;
		this.answers = answers;
		this.explanation = "";
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
}
