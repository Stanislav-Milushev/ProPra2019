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
	private int KneipenAbendID;
	private int counter = 0;
	private int runde = 0;
	
	public KneipenAbend(int ID, List<Question> questions) {
		this.questions = questions;
		KneipenAbendID = ID;
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
