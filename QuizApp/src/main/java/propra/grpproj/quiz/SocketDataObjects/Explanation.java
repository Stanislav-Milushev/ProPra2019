package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class Explanation implements Serializable{
	private String explanation;
	
	/**
	 * 
	 * @param explanation
	 */

	public Explanation(String explanation) {
		this.explanation = explanation;
	}

	public String getExplanation() {
		return explanation;
	}
	
}
