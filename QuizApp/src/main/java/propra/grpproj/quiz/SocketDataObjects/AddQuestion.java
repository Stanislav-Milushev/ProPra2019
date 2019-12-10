package propra.grpproj.quiz.SocketDataObjects;

public class AddQuestion {
	private String questionText;
	private String[] answers;
	private String explanation;
	
	public AddQuestion(String questionText, String[] answers, String explanation) {
		this.questionText = questionText;
		this.answers = answers;
		this.explanation = explanation;
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
