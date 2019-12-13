package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;
import java.util.Date;

public class RepeatPubevening implements Serializable{
	private String name;
	private Date start;

	
	/**
	 * 
	 * @param name
	 * @param start
	 */
	
	public RepeatPubevening(String name, Date start) {
		this.name = name;
		this.start = start;
	}

	public String getName() {
		return name;
	}

	public Date getStart() {
		return start;
	}

	
}
