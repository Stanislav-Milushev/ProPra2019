package propra.grpproj.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.Question;

public class KneipenAbend {

	private List<Question> questions = new ArrayList<Question>();
	private int KneipenAbendID;
	private int timePerQuestion = 20;
	private int counter = 0;
	private int runde = 0;
	
	public KneipenAbend(List<Question> questions, int ID) {
		this.questions = questions;
		KneipenAbendID = ID;
	}
	
	public KneipenAbend(List<Question> questions, int secondsPerQuestion, int ID) {
		this.questions = questions;
		timePerQuestion = secondsPerQuestion;
		KneipenAbendID = ID;
	}
	
	/**
	 * 
	 * @return question list overflow
	 * if true a new questionset needs to be loaded
	 * @author Yannick
	 */
	private void nextQuestion() {
		//Gib alle user in kneipenabend
		ArrayList<String> users = new ArrayList<String>();
		
		for(String user : users) {
			SocketServer.getInstance().sendObject(questions.get(counter), user);
		}
	}
	
	/**
	 * 
	 * @return points for selected answer
	 * @author Yannick
	 */
	public double getAnswerPoints() {
		double points = 0;
		//TODO bonus points for time
		
		points += 100;
		
		return points;
	}
	
	/**
	 * starts the pubevening
	 */
	public void start() {
		Timertask task = new Timertask();
		task.run();
	}
	
	/**
	 * Starts the next question every x seconds
	 * @author Yannick
	 *
	 */
	class Timertask extends TimerTask{

		@Override
		public void run() {
			counter++;
			
			if(counter == questions.size()) {
				counter = 0;
				runde++;
				//Lade neue Fragen wenn es sie gibt
				if(true) { //Es gibt neue fragen
					//Fragen laden
					nextQuestion();
					Timer timer = new Timer(true);
					Timertask task = new Timertask();
					timer.schedule(task, timePerQuestion * 1000);
				} else {
					//Keine neuen fragen
					
						//Sende an alle leute das ergebnis
					
					QuizHandling.getInstance().removeKneipenAbend(KneipenAbendID);
				}
			} else {
				nextQuestion();
				Timer timer = new Timer(true);
				Timertask task = new Timertask();
				timer.schedule(task, timePerQuestion * 1000);
			}
		}
		
	}
}
