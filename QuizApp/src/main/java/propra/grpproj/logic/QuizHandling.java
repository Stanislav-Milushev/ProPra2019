package propra.grpproj.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

////////////////////////////////////////////////////////////////////////////
// Class to create and manage a quiz
// 
// @author: Marius Discher
// 
//
//



public class QuizHandling {
	private HashMap<Integer, KneipenAbend> quizMap = new HashMap<Integer, KneipenAbend>();
	private HashMap<String, KneipenAbend> userMap = new HashMap<String, KneipenAbend>();
	
	public void createQuiz () {
		
	}
	
	public void continueQuiz () {
		
		
	}
	
	public void points () {
		
	}
	
	public void sendToAll () {
		
	}
	
	public void addQuiz(int id, KneipenAbend abend) {
		quizMap.put(id, abend);
	}
	
	public void joinQuiz(String user, KneipenAbend abend) {
		userMap.put(user, abend);
	}
	
	public void joinQuiz(String user, int ID) {
		userMap.put(user, quizMap.get(ID));
	}
	
	public void startQuiz(int ID) {
		quizMap.get(ID).start();
	}
}
