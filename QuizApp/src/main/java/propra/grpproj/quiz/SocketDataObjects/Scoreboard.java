package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class Scoreboard implements Serializable{
	private String user;
	private String KneipenabendID;
	private float score;
	
	/**
	 * 
	 * @param user
	 * @param KneipenabendID
	 * @param score
	 */
	
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

	public String getKneipenabendID() {
		return KneipenabendID;
	}

	public void setKneipenabendID(String kneipenabendID) {
		KneipenabendID = kneipenabendID;
	}	
}
