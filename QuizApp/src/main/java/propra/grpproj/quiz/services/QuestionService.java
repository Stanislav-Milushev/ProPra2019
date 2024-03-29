package propra.grpproj.quiz.services;

import java.util.ArrayList;

import propra.grpproj.quiz.dataholders.Question;
import propra.grpproj.quiz.repositories.sqlite.QuestionRepository;


/**
 * 
 * @author Daniel & Stanislav Milushev
 *
 */
public class QuestionService
{

    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository)
    {
        super();
        this.questionRepository = questionRepository;
    }

    public void createNewQuestion(
            long id, String questionText, String answerA, String answerB, String answerC, String answerD,
            String correctAnswer, String description
    )
    {
        questionRepository
                .save(new Question(id, questionText, answerA, answerB, answerC, answerD, correctAnswer, description));
    }
    public Iterable<propra.grpproj.quiz.dataholders.Question> loadQuestions() {
    	return questionRepository.findAll();
    	
    }
}
