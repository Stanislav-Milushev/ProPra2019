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


public class QuestionHandling {  // Fragen aus der db in runden Paken , dann zu QuizHandling und Kneipenabend
	
	public static List<Question> loadQuestions(String Pool) throws SQLException {
		ArrayList<Question> result = new ArrayList<Question>();
		ArrayList<propra.grpproj.quiz.dataholders.Question> questionsRaw = new AdminHandling().getQuestionPool(Pool);
		
		for ( propra.grpproj.quiz.dataholders.Question qRaw: questionsRaw) {
			String qid = String.valueOf(qRaw.getId());
			String qquestion= String.valueOf(qRaw.getQuestionText());
			String[] qanswer = { qRaw.getAnswerA() ,  qRaw.getAnswerB(),  qRaw.getAnswerC(),  qRaw.getAnswerD()} ;
			String qexpl = qRaw.getDescription();
			
			Question Q = new Question(Integer.valueOf(qid),qquestion,qanswer,qexpl);
			Q.setRightAnswer(qRaw.getCorrectAnswer());
			
			result.add(Q); 
			
		}
		
		return result;
	}
	
	public static ArrayList<ArrayList<Question>> splitRounds(int Roundscount, List<Question> questions) {
		int qPerRound = questions.size() / Roundscount;
		int remain = questions.size() - qPerRound;
		
		ArrayList<ArrayList<Question>> result = new ArrayList<ArrayList<Question>>();
		ArrayList<Question> list = new ArrayList<Question>();
		for(int i = 0, round = 1; i < questions.size(); i++) {
			list.add(questions.get(i));
			
			if(i >= qPerRound * round) {
				if(remain != 0) {
					list.add(questions.get(++i));
					remain--;
				}
				round++;
			}
		}
		
		return result;
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