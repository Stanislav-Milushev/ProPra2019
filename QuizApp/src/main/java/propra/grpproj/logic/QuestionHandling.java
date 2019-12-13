package propra.grpproj.logic;

import java.sql.Array;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import propra.grpproj.quiz.SocketDataObjects.Question;
import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.AddQuestion;
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
	ArrayList<propra.grpproj.quiz.dataholders.Question> questionsRaw = new ArrayList<propra.grpproj.quiz.dataholders.Question>();
	//List<List<String>> rounds = new ArrayList<List<String>>();
	List<List<Question>> rounds = new ArrayList<List<Question>>();
	
	public void loadQuestions(String Pool) throws SQLException {
		AdminHandling ad = new AdminHandling();
		questionsRaw = ad.getQuestionPool(Pool);
		for ( propra.grpproj.quiz.dataholders.Question qRaw: questionsRaw) {
			String qid = String.valueOf(qRaw.getId());
			String qquestion= String.valueOf(qRaw.getQuestionText());
			String[] qanswer = null ;
			qanswer[0]= qRaw.getAnswerA();
			qanswer[1]= qRaw.getAnswerB();
			qanswer[2]= qRaw.getAnswerC();
			qanswer[3]= qRaw.getAnswerD();
			String qexpl = qRaw.getDescription();
			
			Question Q = new Question(Integer.valueOf(qid),qquestion,qanswer,qexpl);
			Q.setRightAnswer(qRaw.getCorrectAnswer());
			
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
		List<AddQuestion> qList = list.getList();
		//TODO add as 1 set
		for(AddQuestion q : qList) {
			//Add to database
		}
	}

	/**
	 * Adds a questionset to the db
	 * @param list
	 * @author Yannick & Lisa
	 */
	public static void questionImport(AddQuestion question) {
		String questionTextToAdd = question.getQuestionText();
		String[] answersToAdd = question.getAnswers();
		String exToAdd = question.getExplanation();
		int set = question.getSet();
		//TODO add to db
	}
	
	/**
	 * Edits a single question in the db
	 * @param cq
	 * @author Yannick
	 */
	public static void editQuestion(ChangeQuestion cq) {
		int qid = cq.getQuestionID();
		String questionTextToEdit = cq.getQuestionText();
		String[] answersToEdit = cq.getAnswers();
		String exToEdit = cq.getExplanation();
		//TODO edit in db
	}
	
	/**
	 * Delets a single question from the db
	 * @param dq
	 */
	public static void deleteQuestion(DeleteQuestion dq) {
		int idToDelete = dq.getQuestionID();
		//TODO delte in db
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