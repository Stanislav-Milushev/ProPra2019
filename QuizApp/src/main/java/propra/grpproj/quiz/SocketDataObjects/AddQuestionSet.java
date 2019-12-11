package propra.grpproj.quiz.SocketDataObjects;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionSet {
	private List<Question> list = new ArrayList<Question>();
	
	public AddQuestionSet( ArrayList<Question> list) {
		this.list = list;
	}
	
	public List<Question> getList () {
		return list;
	}

}
