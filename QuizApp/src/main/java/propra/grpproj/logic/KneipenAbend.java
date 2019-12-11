package propra.grpproj.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.Explanation;
import propra.grpproj.quiz.SocketDataObjects.Question;

public class KneipenAbend {

	private List<Question> questions = new ArrayList<Question>();
	private int KneipenAbendID;
	private int timePerQuestion = 20;
	private int counter = 0;
	private int runde = 0;
	private int pauseInSeconds = 60;
	private long startTime;
	private int poolID;
	
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
	 * Sends the current Question to all participants
	 * @author Yannick
	 */
	private void sendQuestion() {
		//Gib alle user in kneipenabend
		ArrayList<String> users = new ArrayList<String>();
		
		for(String user : users) {
			SocketServer.getInstance().sendObject(questions.get(counter), user);
		}
		startTime = System.nanoTime();
	}
	
	private void sendAnswer() {
		//Gib alle user in kneipenabend
		ArrayList<String> users = new ArrayList<String>();
		String explanation = ""; //Get explanation from database
		
		Explanation exp = new Explanation(explanation);
		for(String user : users) {
			SocketServer.getInstance().sendObject(exp, user);
		}
	}
	
	/**
	 * Calculates the points for a question
	 * @return points for selected answer
	 * @author Yannick
	 */
	public double getAnswerPoints() {
		double points = 0;
		
		points += 100;
		
		long currentTime = System.nanoTime();
		long elapsed = startTime - currentTime;
		
		long elapsedMilli = elapsed / 1000;
		points += (20000 - elapsedMilli) / 200.0;
		
		return points;
	}
	
	/**
	 * starts the pubevening
	 */
	public void start() {
		WaitQuestion task = new WaitQuestion();
		task.run();
	}
	
	/**
	 * Starts the next question every x seconds
	 * @author Yannick
	 *
	 */
	class WaitQuestion extends TimerTask{

		@Override
		public void run() {
			
			sendQuestion();
			Timer timer = new Timer(true);
			WaitExplanation task = new WaitExplanation();
			timer.schedule(task, timePerQuestion * 1000);
			
		}
		
	}
	
	class WaitExplanation extends TimerTask{

		@Override
		public void run() {
			sendAnswer();
			
			counter++;
			
			if(counter == questions.size()) {
				counter = 0;
				runde++;
				//TODO Lade neue Fragen wenn es sie gibt
				if(true) { //Es gibt weiteren fragen

					Timer timer = new Timer(true);
					WaitQuestion task = new WaitQuestion();
					timer.schedule(task, pauseInSeconds * 1000);
				} else {
					//Keine neuen fragen
					
					//TODO Sende an alle leute das ergebnis
					
					QuizHandling.getInstance().removeKneipenAbend(KneipenAbendID);
				}
			}
		}
		
	}
}
