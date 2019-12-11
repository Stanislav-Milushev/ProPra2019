package propra.grpproj.quiz.dataholders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

/**
 * <p>
 * This class holds all information to one question.
 * <p>
 * This class can be referenced by {@link QuestionOfRound}.
 * <p>
 * This class is immutable.
 * 
 * @author Daniel
 *
 */
public class Question
{

    /**
     * Primary key
     */
    private final Long id;

    /**
     * The question
     */
    private final String questionText;

    /**
     * Always the correct answer
     */
    private final String answerA;

    /**
     * Wrong optional answer
     */
    private final String answerB;

    /**
     * Wrong optional answer
     */
    private final String answerC;

    /**
     * Wrong optional answer
     */
    private final String answerD;

    /**
     * Contains one of: ["A", "B", "C", "D"]
     */
    private final String correctAnswer;

    /**
     * Additional description to the question
     */
    private final String description;

    public Question(
            long id, String questionText, String answerA, String answerB, String answerC, String answerD,
            String correctAnswer, String description
    )
    {
        super();

        this.id = id;
        this.questionText = questionText;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
        this.description = description;
    }

    /**
     * Reads the entire CSV question file and parses it to a List of Questions.
     * 
     * @param filename the path to the file (e.g. "docs/ProPra-Fragen-final.csv")
     * @return collection containing all questions from CSV
     * @throws IOException
     * @throws CsvException
     * @throws FileNotFoundException
     */
    public static List<Question> readAllQuestionsFrom(String filename)
            throws IOException, CsvException, FileNotFoundException
    {
        List<Question> questions = new ArrayList<>();

        // @formatter:off
        try (
                Reader reader = new FileReader(filename);
                
                CSVReader csvReader = new CSVReaderBuilder(reader)
                                            .withCSVParser( new CSVParserBuilder()
                                                                .withSeparator(';')
                                                                .build() )
                                            .build();
            )
        {
            List<String[]> content = csvReader.readAll();
            content = content.subList(1, content.size());// skip the header row!!!
            for (String[] row : content)
            {
                questions.add( Question.csvRowToQuestion(row) );
            }
        }
        // @formatter:on
        return questions;
    }

    /**
     * <p>
     * Utility method to create a new question from CSV row
     * <p>
     * Format is: ID;Frage;Antwort A;Antwort B;Antwort C;Antwort D;Korrekte
     * Antwort;Erklärung
     * 
     * @param row containing question info in CSV fromat
     * @return the constructed question
     */
    private static Question csvRowToQuestion(String[] row)
    {
        long id = Long.valueOf(row[0]);
        String questionText = row[1];
        String answerA = row[2];
        String answerB = row[3];
        String answerC = row[4];
        String answerD = row[5];
        String correctAnswer = row[6];
        String description = row[7];
        return new Question(id, questionText, answerA, answerB, answerC, answerD, correctAnswer, description);
    }

    @Override
    public String toString()
    {
        return "Question [id=" + id + ", questionText=" + questionText + ", answerA=" + answerA + ", answerB=" + answerB
                + ", answerC=" + answerC + ", answerD=" + answerD + ", correctAnswer=" + correctAnswer
                + ", description=" + description + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public Long getId()
    { return id; }

    public String getQuestionText()
    { return questionText; }

    public String getAnswerA()
    { return answerA; }

    public String getAnswerB()
    { return answerB; }

    public String getAnswerC()
    { return answerC; }

    public String getAnswerD()
    { return answerD; }

    public String getCorrectAnswer()
    { return correctAnswer; }

    public String getDescription()
    { return description; }

}
