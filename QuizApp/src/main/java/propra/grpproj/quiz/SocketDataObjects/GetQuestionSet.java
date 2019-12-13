package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetQuestionSet implements Serializable{
	private List<Question> list = new ArrayList<Question>();
	private int set;
	
	
	/**
	 * 
	 * @param set
	 */
	
	public GetQuestionSet(int set) {
		this.set = set;
	}

	public void setList(List<Question> qList) {
		this.list = qList;
	}

	public int getSet() {
		return set;
	}
	
	public List<Question> getList () {
		return list;
	}
}
