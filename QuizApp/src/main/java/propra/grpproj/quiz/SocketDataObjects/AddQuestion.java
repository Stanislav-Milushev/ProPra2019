package propra.grpproj.quiz.SocketDataObjects;

public class AddQuestion {
	Question q;
	int set;
	
	public AddQuestion(Question add, int set) {
		q = add;
		this.set = set;
	}

	public Question getQ() {
		return q;
	}

	public int getSet() {
		return set;
	}
}
