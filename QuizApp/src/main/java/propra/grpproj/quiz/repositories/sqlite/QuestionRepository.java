package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.utilities.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.Question;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;


/**
 * 
 * @author Daniel & Stanislav Milushev
 *
 */
public class QuestionRepository extends CrudRepositoryAdapter<Question, Long>
{
	
	public QuestionRepository(){
		
	}
    /**
     * The table name managed by this repository
     */
    private static final String TABLE_NAME = "questions";

    /**
     * The SQL query to create this table
     */
    // @formatter:off
    private static final String SQL_TO_CREATE_TABLE = 
              "CREATE TABLE " + TABLE_NAME
                + " (id INTEGER PRIMARY KEY,"
                + " questionText STRING,"
                + " answerA STRING,"
                + " answerB STRING, "
                + " answerC STRING,"
                + " answerD STRING,"
                + " correctAnswer STRING,"
                + " description STRING)";
    // @formatter:on

    @Override
    public void createTable()
    {
        deleteAll();
    }

    @Override
    public Iterable<Question> findAll()
    {
        String sql = "SELECT * FROM " + TABLE_NAME;

        ArrayList<Question> resultList = new ArrayList<>();
        try (Connection conn = connect(); Statement statement = conn.createStatement();)
        {
            try (ResultSet rs = statement.executeQuery(sql);)
            {
                while (rs.next())
                {
                    Question entity = buildQuestionFromResultSet(rs);
                    resultList.add(entity);
                }
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    @Override
    public Optional<Question> findById(Long id)
    {
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME
                   + "  WHERE id=?";
        
        Question returnValue = null;
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    returnValue = buildQuestionFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(returnValue);
        // @formatter:on
    }

    // ========================================================================
    // below methods have identical implementation for all type of repositories
    // ========================================================================
    @Override
    public <S extends Question> S save(S entity)
    {
        if (existsById(entity.getId()))
        {
            return update(entity);
        } else
        {
            return insert(entity);
        }
    }

    @Override
    public void delete(Question entity)
    {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id)
    {
        // @formatter:off
        String sql = "DELETE FROM " + TABLE_NAME
                + " WHERE id=?";
        
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // @formatter:on
    }

    @Override
    public boolean existsById(Long id)
    {
        return findById(id).isPresent();
    }

    @Override
    public void deleteAll()
    {
        // @formatter:off
        try ( Connection conn = connect();
              Statement statement = conn.createStatement(); )
        {
            statement.executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME);
            statement.executeUpdate(SQL_TO_CREATE_TABLE);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on
    }

    // ========================================================================
    // helper methods
    // ========================================================================

    private Question buildQuestionFromResultSet(ResultSet rs) throws SQLException
    {
        Long id = rs.getLong("id");
        String questionText = rs.getString("questionText");
        String answerA = rs.getString("answerA");
        String answerB = rs.getString("answerB");
        String answerC = rs.getString("answerC");
        String answerD = rs.getString("answerD");
        String correctAnswer = rs.getString("correctAnswer");
        String description = rs.getString("description");
        return new Question(id, questionText, answerA, answerB, answerC, answerD, correctAnswer, description);
    }

    private <S extends Question> S insert(S entity)
    {
        // @formatter:off
        String sql = "INSERT INTO " + TABLE_NAME
                + "(id,questionText,answerA,answerB,answerC,answerD,correctAnswer,description)"
                + "  VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setLong(1, entity.getId());
            pstmt.setString(2, entity.getQuestionText());
            pstmt.setString(3, entity.getAnswerA());
            pstmt.setString(4, entity.getAnswerB());
            pstmt.setString(5, entity.getAnswerC());
            pstmt.setString(6, entity.getAnswerD());
            pstmt.setString(7, entity.getCorrectAnswer());
            pstmt.setString(8, entity.getDescription());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getId()).get();
        return s;
    }

    private <S extends Question> S update(S entity)
    {
        // @formatter:off
        String sql = "UPDATE " + TABLE_NAME
                + " SET questionText=?, answerA=?, answerB=?, answerC=?, answerD=?, correctAnswer=?, description=?"
                + " WHERE id=?";

        try (
                Connection conn = connect(); 
                PreparedStatement pstmt = conn.prepareStatement(sql);
            ) 
        {
            pstmt.setString(1, entity.getQuestionText());
            pstmt.setString(2, entity.getAnswerA());
            pstmt.setString(3, entity.getAnswerB());
            pstmt.setString(4, entity.getAnswerC());
            pstmt.setString(5, entity.getAnswerD());
            pstmt.setString(6, entity.getCorrectAnswer());
            pstmt.setString(7, entity.getDescription());
            pstmt.setLong(8, entity.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getId()).get();
        return s;
    }

}
