package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.Question;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;

public class QuestionRepository extends CrudRepositoryAdapter<Question, Long>
{

	/**
	 * The table name managed by this repository
	 */
	private static final String TABLE_NAME = "question";

	/**
	 * The SQL query to create this table
	 */
	private static final String SQL_TO_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
			+ " (questionId INTEGER PRIMARY KEY, userId INTEGER, roundId INTEGER," 
			+ "FOREIGN KEY(userId) REFERENCES user (userId), "
			+ "FOREIGN KEY(roundId) REFERENCES questionRound (roundId))";

	@Override
	public long count()
	{
		int returnValue = 0;

		String sql = "SELECT * FROM " + TABLE_NAME;

		// @formatter:off
		try (Connection conn = connect(); Statement statement = conn.createStatement();)
		// @formatter:on
		{
			try (ResultSet rs = statement.executeQuery(sql);) {
				while (rs.next()) {
					++returnValue;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return returnValue;
	}

	@Override
	public void delete(Question entity)
	{
		// ensure that delete does the very same as deleteById and vice versa
		deleteById(entity.getQuestionId());
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
	public void deleteById(Long questionId)
	{
		// @formatter:off
		String sql = "DELETE FROM " + TABLE_NAME + "  WHERE questionId=?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
		// @formatter:on
		{
			pstmt.setLong(1, questionId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Iterable<Question> findAll()
	{
		ArrayList<Question> resultList = new ArrayList<>();

		String sql = "SELECT * FROM " + TABLE_NAME;

		// @formatter:off
		try (Connection conn = connect(); Statement statement = conn.createStatement();)
		// @formatter:on
		{
			try (ResultSet rs = statement.executeQuery(sql);) {
				while (rs.next()) {
					Question entity = buildQuestionFromResultSet(rs);
					resultList.add(entity);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultList;
	}

	@Override
	public Optional<Question> findById(Long questionId)
	{
		// define your result value for the Optional: may stays null!
		Question returnValue = null;

		// set up your query with '?' for all variables
		// @formatter:off
		String sql = "SELECT * FROM " + TABLE_NAME + "  WHERE questionId=?";

		// open connection and prepare statement using 'try-with-resource'
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
		// @formatter:on
		{
			// insert variables into the '?' of our prepared query
			pstmt.setLong(1, questionId);// 1st param is index of '?'

			// manage query-result with try-with-resources to use the auto-closable feature
			try (ResultSet rs = pstmt.executeQuery();) {
				// find first by id:
				if (rs.next()) {
					returnValue = buildQuestionFromResultSet(rs);
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
	public <S extends Question> S save(S entity)
	{
        if (existsById(entity.getQuestionId()))
        {
            return update(entity);
        } else
        {
            return insert(entity);
        }
	}

	// ========================================================================
	// helpers
	// ========================================================================

	private <S extends Question> S update(S entity)
	{
	     // @formatter:off
        String sql =   "UPDATE " + TABLE_NAME
                     + "  SET userId=?,roundId=?"
                     + "  WHERE questionId=?"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setLong(1, entity.getUserId());
            pstmt.setLong(2, entity.getRoundId());
            pstmt.setLong(3, entity.getQuestionId());

            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getQuestionId()).get();
        return s;
	}

	private <S extends Question> S insert(S entity)
	{
		// @formatter:off
		String sql = "INSERT INTO " + TABLE_NAME + "(questionId,userId,roundId)" 
		+ "  VALUES(?, ?, ?)";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setLong(1, entity.getQuestionId());
			pstmt.setLong(2, entity.getUserId());
			pstmt.setLong(3, entity.getRoundId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// @formatter:on

		@SuppressWarnings("unchecked")
		S s = (S) findById(entity.getQuestionId()).get();
		return s;
	}

	private Question buildQuestionFromResultSet(ResultSet rs) throws SQLException
	{
		// fetch each parameter from the query-result...
		Long foundID = rs.getLong("questionId");
		Long userId = rs.getLong("userId");
		Long roundId = rs.getLong("roundId");

		// ... and build a new Question
		return new Question(foundID, userId, roundId);
	}

}
