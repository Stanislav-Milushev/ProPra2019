package propra.grpproj.logic;

import java.sql.Array;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import propra.grpproj.quiz.SocketDataObjects.Question;
import propra.grpproj.quiz.SocketDataObjects.QuestionList;
import propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities;

////////////////////////////////////////////////////////////////////////////
// Class to load and manage a questions
//
// @author: Stanislav Milushev
//
//
//


public class QuestionHandling 
{  // Fragen aus der db in runden Paken , dann zu QuizHandling und Kneipenabend
	List<Question> questions = new ArrayList<Question>();
	List<String> questionsRaw = new ArrayList<String>();
	//List<List<String>> rounds = new ArrayList<List<String>>();
	List<List<Question>> rounds = new ArrayList<List<Question>>();
	
	public void loadQuestions(String Pool) throws SQLException {
		AdminHandling ad = new AdminHandling();
		questionsRaw = ad.getQuestionPool(Pool);
		for(String qRaw: questionsRaw) {
			String[] qSplit = qRaw.split(";");
			int qid= Integer.valueOf(qSplit[0]);
			String qquestion= qSplit[1];
			// Antwort array Format?
			String[] qanswer=null;//
			String qexpl= null;//
			
			Question Q = new Question(qid,qquestion,qanswer,qexpl);
			questions.add(Q); 
		}
	}
	
	public void splitRounds(int Roundscount) {
		int av = questions.size() / Roundscount;
		int i;		
		for (int x=0; x < Roundscount; x++) {  
		    List<Question> round = new ArrayList<>();
		    i = av*x;	
		    for( ;av>i ;i++) {
				round.add(questions.get(i));
			}
		    rounds.add(round);
		 }  
	}

	public List<Question> getRounds(int roundNum) {
		return rounds.get(roundNum);
	}

	public static void questionImport(QuestionList list) {
		List<Question> qList = list.getList();
		
		for(Question q : qList) {
			//Add to database
		}
	}
	
}