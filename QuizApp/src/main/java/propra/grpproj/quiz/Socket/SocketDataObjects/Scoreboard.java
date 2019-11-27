package propra.grpproj.quiz.Socket.SocketDataObjects;

public class Scoreboard {
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
