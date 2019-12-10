package propra.grpproj.logic;

import java.util.HashMap;
import java.util.List;

import propra.grpproj.quiz.SocketDataObjects.IntegerMap;
import propra.grpproj.quiz.SocketDataObjects.Question;

////////////////////////////////////////////////////////////////////////////
// Class to create and manage a quiz
// 
// @author: Marius Discher, Yannick Lapp
// 
//
//



public class QuizHandling {
	private HashMap<Integer, KneipenAbend> quizMap = new HashMap<Integer, KneipenAbend>();
	private HashMap<String, Integer> userMap = new HashMap<String, Integer>();
	
	private static QuizHandling instance = null;
	
	public static QuizHandling getInstance() {
		if(instance == null) {
			instance = new QuizHandling();
		}
		return instance;
	}
	
	/**
	 * creates a quiz with the given parameters
	 * @param QuizID
	 * @param questions
	 * @author Yannick
	 */
	public void createQuiz (int QuizID, List<Question> questions) {
		KneipenAbend abend = new KneipenAbend(questions, QuizID);
		quizMap.put(QuizID, abend);
	}
	
	/**
	 * creates a quiz with the given parameters
	 * @param QuizID
	 * @param questions
	 * @param secondsPerQuestion
	 * @author Yannick 
	 */
	public void createQuiz (int QuizID, List<Question> questions, int secondsPerQuestion) {
		KneipenAbend abend = new KneipenAbend(questions, secondsPerQuestion, QuizID);
		quizMap.put(QuizID, abend);
	}
	
	/**
	 * Adds user to a quiz
	 * @param user
	 * @param abendID
	 * @author Yannick
	 */
	public void joinQuiz(String user, int abendID) {
		userMap.put(user, abendID);
	}
	
	/**
	 * gets called to start a quiz
	 * @param ID
	 * @author Yannick
	 */
	public void startQuiz(int ID) {
		quizMap.get(ID).start();
	}
	
	/**
	 * Gets called from Socket server
	 * @param user
	 * @param map
	 * @author Yannick
	 */
	public void answer(String user, IntegerMap map) {
		if(map.getNum1() == 1) {
			if(map.getNum2() == 1) {//1 ist immer richtig
				double points = quizMap.get(userMap.get(user)).getAnswerPoints();
				//In datenbank punkte eintragen
			}
		}
	}
	
	/**
	 * Removes the KneipenAbend from the HashMaps
	 * @param QuizID ID of quiz
	 * @author Yannick
	 */
	public void removeKneipenAbend(int QuizID) {
		quizMap.remove(QuizID);
		for(String s : userMap.keySet()) {
			if(userMap.get(s) == QuizID) {
				userMap.remove(s);
			}
		}
	}
}
