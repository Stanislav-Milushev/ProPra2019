package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.QuestionsPool;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;

public class QuestionsPoolRepository extends CrudRepositoryAdapter<QuestionsPool, Long>
{
	/**
	 * The table name managed by this repository
	 */
	private static final String TABLE_NAME = "scoreboard";

	/**
	 * The SQL query to create this table
	 */
	private static final String SQL_TO_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (poolId INTEGER PRIMARY KEY, "
			+ "FOREIGN KEY(questionAnswersId) REFERENCES questionsandanswers (questionAnswersId)";

	@Override
	public void delete(QuestionsPool entity)
	{
		// ensure that delete does the very same as deleteById and vice versa
		deleteById(entity.getPoolId());
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
	public void deleteById(Long poolId)
	{
		// @formatter:off
		String sql = "DELETE FROM " + TABLE_NAME + "  WHERE poolId=?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
		// @formatter:on
		{
			pstmt.setLong(1, poolId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Iterable<QuestionsPool> findAll()
	{
		ArrayList<QuestionsPool> resultList = new ArrayList<>();

		String sql = "SELECT * FROM " + TABLE_NAME;

		// @formatter:off
		try (Connection conn = connect(); Statement statement = conn.createStatement();)
		// @formatter:on
		{
			try (ResultSet rs = statement.executeQuery(sql);) {
				while (rs.next()) {
					QuestionsPool entity = buildQuestionsPoolFromResultSet(rs);
					resultList.add(entity);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultList;
	}

	@Override
	public Optional<QuestionsPool> findById(Long poolId)
	{
		// define your result value for the Optional: may stays null!
		QuestionsPool returnValue = null;

		// set up your query with '?' for all variables
		// @formatter:off
		String sql = "SELECT * FROM " + TABLE_NAME + "  WHERE poolId=?";

		// open connection and prepare statement using 'try-with-resource'
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
		// @formatter:on
		{
			// insert variables into the '?' of our prepared query
			pstmt.setLong(1, poolId);// 1st param is index of '?'

			// manage query-result with try-with-resources to use the auto-closable feature
			try (ResultSet rs = pstmt.executeQuery();) {
				// find first by id:
				if (rs.next()) {
					returnValue = buildQuestionsPoolFromResultSet(rs);
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
	public <S extends QuestionsPool> S save(S entity)
	{
			return insert(entity);
	}

	// ========================================================================
	// helpers
	// ========================================================================

	private QuestionsPool buildQuestionsPoolFromResultSet(ResultSet rs) throws SQLException
	{
		// fetch each parameter from the query-result...
		Long foundID = rs.getLong("poolId");

		// ... and build a new QuestionsPool-Entry
		return new QuestionsPool(foundID);
	}

	private <S extends QuestionsPool> S insert(S entity)
	{
        // @formatter:off
        String sql =   "INSERT INTO " + TABLE_NAME + "(poolId)"
                     + "  VALUES(?)"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setLong(1, entity.getPoolId());
            
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getPoolId()).get();
        return s;
	}

}
