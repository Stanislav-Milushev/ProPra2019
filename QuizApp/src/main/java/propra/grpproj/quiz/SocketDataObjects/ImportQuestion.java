package propra.grpproj.quiz.SocketDataObjects;

public class ImportQuestion {
	private String question;
	private String[] answers;
	private String explanation;

	private int correctAnswer;
	
	public ImportQuestion(String question, String[] answers, String explanation, int correctAnswer) {
		this.question = question;
		this.answers = answers;
		this.explanation = explanation;
		this.correctAnswer = correctAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public String[] getAnswers() {
		return answers;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public String getExplanation() {
		return explanation;
	}
}
