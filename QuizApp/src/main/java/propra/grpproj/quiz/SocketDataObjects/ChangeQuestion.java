package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class ChangeQuestion implements Serializable{
	private  int questionID;
	private String questionText;
	private String[] answers;
	private String explanation;
	
	public ChangeQuestion(int questionID, String questionText, String[] answers, String explanation) {
		this.questionID = questionID;
		this.questionText = questionText;
		this.answers = answers;
		this.explanation = explanation;
	}

	public int getQuestionID() {
		return questionID;
	}

	public String getQuestionText() {
		return questionText;
	}

	public String[] getAnswers() {
		return answers;
	}

	public String getExplanation() {
		return explanation;
	}
	
	
}
