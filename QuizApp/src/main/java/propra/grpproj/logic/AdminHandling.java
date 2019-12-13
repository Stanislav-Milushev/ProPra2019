package propra.grpproj.logic;

import java.sql.SQLException;
import java.util.ArrayList;

import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.AcceptPub;
import propra.grpproj.quiz.SocketDataObjects.GetQuestionSet;
import propra.grpproj.quiz.SocketDataObjects.PubList;
import propra.grpproj.quiz.SocketDataObjects.Question;
////////////////////////////////////////////////////////////////////////////
// Admin functions
// 
// @author: Marius Discher & Stanislav Milushev
//
//
//
//
import propra.grpproj.quiz.dataholders.Pub;



public class AdminHandling {
	
	// Function to approve a pub
	public void approvePub(String name, String owner) throws SQLException {
		
		DatabaseManager db = new DatabaseManager();
		
		db.approvePub(name,owner);
		
		AcceptPub accept = new AcceptPub(name, owner);
		
		SocketServer.getInstance().sendObject(accept, name);
		
		
	}
	
	// Get the whole question pool
	public ArrayList<propra.grpproj.quiz.dataholders.Question> getQuestionPool(String name) throws SQLException {  /// zu schreiben
		
		Iterable<propra.grpproj.quiz.dataholders.Question> questions_pool = new ArrayList<propra.grpproj.quiz.dataholders.Question>();
		
		DatabaseManager db = new DatabaseManager();
		
		return (ArrayList<propra.grpproj.quiz.dataholders.Question>) (questions_pool = db.getPool(name));
		
	}
	public void getQuestions(String name) throws SQLException {  /// zu schreiben
		DatabaseManager db = new DatabaseManager();
		ArrayList<propra.grpproj.quiz.dataholders.Question> questionsRaw = (ArrayList<propra.grpproj.quiz.dataholders.Question>) db.getPool(name);
		GetQuestionSet questions_pool = new GetQuestionSet(2);
		ArrayList<Question> questions = new ArrayList<Question>();
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
		questions_pool.setList(questions);

		SocketServer.getInstance().sendObject(questions_pool, name);
		
	}
}
