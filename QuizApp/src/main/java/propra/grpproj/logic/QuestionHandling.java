package propra.grpproj.logic;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import propra.grpproj.quiz.SocketDataObjects.Question;
import propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities;

public class QuestionHandling {  // Fragen aus der db in runden Paken , dann zu QuizHandling und Kneipenabend
	//List<Question> questions = new ArrayList<Question>();
	List<String> questions = new ArrayList<String>();
	List<List<String>> rounds = new ArrayList<List<String>>();
	
	public void loadQuestions(String Pool) throws SQLException {
		AdminHandling ad = new AdminHandling();
		questions = ad.getQuestionPool(Pool);
	
	}
	
	public void splitRounds(int Roundscount) {
		int av = questions.size() / Roundscount;
		int i;		
		for (int x=0; x < Roundscount; x++) {  
		    List<String> round = new ArrayList<>();
		    i = av*x;	
		    for( ;av>i ;i++) {
				round.add(questions.get(i));
			}
		    rounds.add(round);
		 }  
	}

	public List<String> getRounds(int roundNum) {
		return rounds.get(roundNum);
	}


	
	
	
}