package propra.grpproj.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.Question;

public class KneipenAbend {

	private List<Question> questions = new ArrayList<Question>();
	private int timePerQuestion = 20;
	private int counter = 0;
	private int runde = 0;
	
	public KneipenAbend(List<Question> questions) {
		this.questions = questions;
	}
	
	public KneipenAbend(List<Question> questions, int secondsPerQuestion) {
		this.questions = questions;
		timePerQuestion = secondsPerQuestion;
	}
	
	private boolean nextQuestion() {//Boolean overflow
		counter++;
		if(counter == questions.size()) {
			counter = 0;
			runde++;
			//Lade neue Fragen
			return true;
		} else {
			//Gib alle user in kneipenabend
			ArrayList<String> users = new ArrayList<String>();
			
			for(String user : users) {
				SocketServer.getInstance().sendObject(questions.get(counter), user);
			}
			return false;
		}
	}
	
	public double getAnswerPoints() {
		double points = 0;
		//TODO bonus points for time
		
		points += 100;
		
		return points;
	}
	
	public void start() {
		Timer timer = new Timer(true);
		Timertask task = new Timertask();
		timer.schedule(task, timePerQuestion * 1000);
	}
	
	class Timertask extends TimerTask{

		@Override
		public void run() {
			if(!nextQuestion()) {
				Timer timer = new Timer(true);
				Timertask task = new Timertask();
				timer.schedule(task, timePerQuestion * 1000);
			} else {
				//Schau ob neue runde existiert, wenn ja
				
				if(true) {
					Timer timer = new Timer(true);
					Timertask task = new Timertask();
					timer.schedule(task, timePerQuestion * 1000);
				} else {
					//Sende an alle leute das ergebnis
				}
			}
		}
		
	}
}
