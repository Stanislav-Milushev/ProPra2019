package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionList implements Serializable{
	public ArrayList<Question> list = new ArrayList<Question>();
	
	public ArrayList<Question> getList () {
		ArrayList<Question> qlist  = this.list;
		return qlist;
	}
}
