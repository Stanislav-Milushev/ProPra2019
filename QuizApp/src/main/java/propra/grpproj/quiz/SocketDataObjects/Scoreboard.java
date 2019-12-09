package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class Scoreboard implements Serializable{
	private String user;
	private float score;
	
	public Scoreboard(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public float getScore() {
		return score;
	}
	
	public void setScore(float score) {
		this.score = score;
	}	
}
