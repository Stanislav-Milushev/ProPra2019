package propra.grpproj.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import propra.grpproj.quiz.SocketDataObjects.CreatePubevening;
import propra.grpproj.quiz.SocketDataObjects.IntegerMap;
import propra.grpproj.quiz.SocketDataObjects.Question;
import propra.grpproj.quiz.SocketDataObjects.RepeatPubevening;

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
	 * Creates and starts the evening
	 * @param e CreatePubevening object from SocketServer
	 * @author Yannick
	 */
	public void createQuiz(CreatePubevening e) {
		List<Question> questions = new ArrayList<Question>(); //Runde 1 befüllen
		int ID = 0; //ID holen
		
		KneipenAbend abend = new KneipenAbend(questions, e.getSecPerQuestion(), ID);
		quizMap.put(ID, abend);
		
		Timer t = new Timer(true);
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				startQuiz(ID);				
			}
			
		}, e.getStart());
	}
	
	/**
	 * Repeats and starts the evening
	 * @param e RepeatPubevening object from SocketServer
	 * @author Yannick
	 */
	public void createQuiz(RepeatPubevening e) {
		int sec = 0; //Von db setzen lassen
		int set = 1; //Von db setzen lassen
		CreatePubevening create = new CreatePubevening(sec, e.getStart(), set, e.getName()); //Name ist nicht identifizierend, aber alle mit dem geleichen namen haben gleiche einstellungen
		createQuiz(create);
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
	private void startQuiz(int ID) {
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
