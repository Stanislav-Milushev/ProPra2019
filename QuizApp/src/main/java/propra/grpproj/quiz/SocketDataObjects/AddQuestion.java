package propra.grpproj.quiz.SocketDataObjects;

public class AddQuestion {
	String questionText;
	String[] answers;
	String explanation;
	int set;
	
	/**
	 * 
	 * @param qT
	 * @param answers
	 * @param ex
	 * @param set
	 */

	public AddQuestion(String qT, String[] answers, String ex, int set) {
		this.questionText = qT;
		this.answers = answers;
		this.explanation = ex;
		this.set = set;
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

	public int getSet() {
		return set;
	}

}
