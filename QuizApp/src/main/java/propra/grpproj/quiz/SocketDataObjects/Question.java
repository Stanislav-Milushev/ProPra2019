package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class Question implements Serializable{
	private String question;
	private String[] answers;
	private int selectedAnswer;
	
	public int getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(int selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public String[] getAnswers() {
		return answers;
	}

	public Question(String question, String[] answers) {
		this.question = question;
		this.answers = answers;
	}
}
