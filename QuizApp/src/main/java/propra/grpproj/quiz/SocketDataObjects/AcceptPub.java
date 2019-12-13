package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class AcceptPub implements Serializable{
	private String name;
	private String owner;
	private boolean acc;
	
	public AcceptPub() {
		
	}

	public String getName() {
		return name;
	}
	
	public String getOwner() {
		return owner;
	}
	
}
