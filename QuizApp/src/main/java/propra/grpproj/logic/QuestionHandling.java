package propra.grpproj.logic;

import java.sql.Array;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import propra.grpproj.quiz.SocketDataObjects.Question;
import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.AddQuestionSet;
import propra.grpproj.quiz.SocketDataObjects.ChangeQuestion;
import propra.grpproj.quiz.SocketDataObjects.DeleteQuestion;
import propra.grpproj.quiz.SocketDataObjects.GetQuestionSet;

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

	/**
	 * Adds a questionset to the db
	 * @param list
	 * @author Yannick
	 */
	public static void questionImport(AddQuestionSet list) {
		List<Question> qList = list.getList();
		//TODO add as 1 set
		for(Question q : qList) {
			//Add to database
		}
	}

	
	/**
	 * Edits a single question in the db
	 * @param cq
	 * @author Yannick
	 */
	public static void editQuestion(ChangeQuestion cq) {
		
	}
	
	/**
	 * Delets a single question from the db
	 * @param dq
	 */
	public static void deleteQuestion(DeleteQuestion dq) {
		
	}
	
	/**
	 * gets the questionset from the db
	 * @param set
	 * @param username
	 * @author Yannick
	 */
	public static void getQuestionSet(GetQuestionSet set, String username) {
		int setID = set.getSet();
		List<Question> qList = new ArrayList<Question>(); //TODO get from server
		//TODO add as 1 set
		set.setList(qList);
		SocketServer.getInstance().sendObject(set, username);
	}
	
}