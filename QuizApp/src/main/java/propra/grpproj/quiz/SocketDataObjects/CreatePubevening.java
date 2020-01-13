package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;
import java.util.Date;

public class CreatePubevening implements Serializable{
	private int secPerQuestion = 20;
	private Date start;
	private int questionSets;
	private String name;
	private int numRounds;
	
	/**
	 * 
	 * @param secPerQuestion
	 * @param start time when the game starts
	 * @param questionSets sets that hold the questions
	 */
	
	
	public CreatePubevening(int secPerQuestion, Date start, int questionSets, int numRoundsm, String name) {
		this.secPerQuestion = secPerQuestion;
		this.start = start;
		this.questionSets = questionSets;
		this.name = name;
		this.numRounds = numRoundsm;
	}

	public int getSecPerQuestion() {
		return secPerQuestion;
	}

	public String getName() {
		return name;
	}

	public Date getStart() {
		return start;
	}

	public int getQuestionSets() {
		return questionSets;
	}
	
	public int getRounds() {
		return numRounds;
	}
}
