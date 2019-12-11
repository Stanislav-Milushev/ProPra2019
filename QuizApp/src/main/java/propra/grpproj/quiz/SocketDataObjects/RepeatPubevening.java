package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class RepeatPubevening implements Serializable{
	private String name;

	public RepeatPubevening(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
