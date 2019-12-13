package propra.grpproj.quiz.SocketDataObjects;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionSet {
	private List<AddQuestion> list;
	
	/**
	 * 
	 * @param list
	 */
	
	public AddQuestionSet(List<AddQuestion> list) {
		this.list = list;
	}
	
	public List<AddQuestion> getList () {
		return list;
	}

}
