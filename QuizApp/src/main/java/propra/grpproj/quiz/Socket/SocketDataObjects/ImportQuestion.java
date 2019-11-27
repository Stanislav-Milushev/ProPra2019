package propra.grpproj.quiz.Socket.SocketDataObjects;

public class ImportQuestion {
	private String question;
	private String[] answers;
	private int correctAnswer;
	
	public ImportQuestion(String question, String[] answers, int correctAnswer) {
		this.question = question;
		this.answers = answers;
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
}
