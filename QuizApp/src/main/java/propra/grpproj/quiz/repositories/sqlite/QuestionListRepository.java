package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.QuestionList;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;

/**
 * 
 * @author Daniel
 *
 */
public class QuestionListRepository extends CrudRepositoryAdapter<QuestionList, Long>
{

	/**
	 * The table name managed by this repository
	 */
	private static final String TABLE_NAME = "questionList";

	/**
	 * The SQL query to create this table
	 */
	private static final String SQL_TO_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
			+ " (questionListId INTEGER PRIMARY KEY, question STRING, answerTrue STRING, answerFalse1 STRING"
			+ ", answerFalse2 STRING, answerFalse3 STRING, description STRING)";

	@Override
	public void delete(QuestionList entity)
	{
		deleteById(entity.getQuestionListId());
	}

	@Override
	public void deleteAll()
	{
		// @formatter:off
		try (Connection conn = connect(); Statement statement = conn.createStatement();)
		// @formatter:on
		{
			statement.executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME);
			statement.executeUpdate(SQL_TO_CREATE_TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteById(Long questionListId)
	{
		// @formatter:off
		String sql = "DELETE FROM " + TABLE_NAME + "  WHERE questionAnswersId=?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
		// @formatter:on
		{
			pstmt.setLong(1, questionListId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean existsById(Long questionListId)
	{
		return findById(questionListId).isPresent();
	}

	@Override
	public Iterable<QuestionList> findAll()
	{
		ArrayList<QuestionList> resultList = new ArrayList<>();

		String sql = "SELECT * FROM " + TABLE_NAME;

		// @formatter:off
		try (Connection conn = connect(); Statement statement = conn.createStatement();)
		// @formatter:on
		{
			try (ResultSet rs = statement.executeQuery(sql);) {
				while (rs.next()) {
					QuestionList entity = buildQuestionListFromResultSet(rs);
					resultList.add(entity);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultList;
	}

	@Override
	public Optional<QuestionList> findById(Long questionListId)
	{
		// define your result value for the Optional: may stays null!
		QuestionList returnValue = null;

		// set up your query with '?' for all variables
		// @formatter:off
		String sql = "SELECT * FROM " + TABLE_NAME + "  WHERE questionListId=?";

		// open connection and prepare statement using 'try-with-resource'
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
		// @formatter:on
		{
			// insert variables into the '?' of our prepared query
			pstmt.setLong(1, questionListId);// 1st param is index of '?'

			// manage query-result with try-with-resources to use the auto-closable feature
			try (ResultSet rs = pstmt.executeQuery();) {
				// find first by id:
				if (rs.next()) {
					returnValue = buildQuestionListFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			// re-throw exception wrapped in a new runtime-exception, which is a uncheck
			// exception
			throw new RuntimeException(e);
		}

		// Wrap the result in the optional (may be null)
		return Optional.ofNullable(returnValue);
	}

	@Override
	public <S extends QuestionList> S save(S entity)
	{
		if (existsById(entity.getQuestionListId())) {
			return update(entity);
		} else {
			return insert(entity);
		}
	}

	// ========================================================================
	// helpers
	// ========================================================================

	private QuestionList buildQuestionListFromResultSet(ResultSet rs) throws SQLException
	{
		// fetch each parameter from the query-result...
		Long foundID = rs.getLong("questionListId");
		String question = rs.getString("question");
		String answerTrue = rs.getString("answerTrue");
		String answerFalse1 = rs.getString("answerFalse1");
		String answerFalse2 = rs.getString("answerFalse2");
		String answerFalse3 = rs.getString("answerFalse3");
		String description = rs.getString("description");

		// ... and build a new QuestionsAndAnswers-Entry
		return new QuestionList(foundID, question, answerTrue, answerFalse1, answerFalse2, answerFalse3,
				description);
	}

	private <S extends QuestionList> S insert(S entity)
	{
		// @formatter:off
		String sql = "INSERT INTO " + TABLE_NAME
				+ "(questionListId,question,answerTrue,answerFalse1,answerFalse2,answerFalse3,description)"
				+ "  VALUES(?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setLong(1, entity.getQuestionListId());
			pstmt.setString(2, entity.getQuestion());
			pstmt.setString(3, entity.getAnswerTrue());
			pstmt.setString(4, entity.getAnswerFalse1());
			pstmt.setString(5, entity.getAnswerFalse2());
			pstmt.setString(6, entity.getAnswerFalse3());
			pstmt.setString(7, entity.getDescription());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// @formatter:on

		@SuppressWarnings("unchecked")
		S s = (S) findById(entity.getQuestionListId()).get();
		return s;
	}

	private <S extends QuestionList> S update(S entity)
	{
		// @formatter:off
		String sql = "UPDATE " + TABLE_NAME
				+ "  SET question=?, answerTrue=?, answerFalse1=?, answerFalse2=?, answerFalse3=?, description=?"
				+ "  WHERE questionListId=?";

		try (
				Connection conn = connect(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) 
		{
			pstmt.setString(1, entity.getQuestion());
			pstmt.setString(2, entity.getAnswerTrue());
			pstmt.setString(3, entity.getAnswerFalse1());
			pstmt.setString(4, entity.getAnswerFalse2());
			pstmt.setString(5, entity.getAnswerFalse3());
			pstmt.setString(6, entity.getDescription());
			pstmt.setLong(7, entity.getQuestionListId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// @formatter:on

		@SuppressWarnings("unchecked")
		S s = (S) findById(entity.getQuestionListId()).get();
		return s;
	}
}
