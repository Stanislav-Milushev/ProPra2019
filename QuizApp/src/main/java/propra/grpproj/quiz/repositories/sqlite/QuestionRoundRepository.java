package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.QuestionRound;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;

public class QuestionRoundRepository extends CrudRepositoryAdapter<QuestionRound, Long>
{

    /**
     * The table name managed by this repository
     */
    private static final String TABLE_NAME = "questionRound";

    /**
     * The SQL query to create this table
     */
    private static final String SQL_TO_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (roundId INTEGER PRIMARY KEY, pause INTEGER, "
    		+ "FOREIGN KEY(pubEveningId) REFERENCES pubevening (pubEveningId))";

	@Override
	public long count()
	{
        int returnValue = 0;

        String sql = "SELECT * FROM " + TABLE_NAME;

        // @formatter:off
          try (
                  Connection conn = connect();
                  Statement statement = conn.createStatement();
              )
        // @formatter:on
        {
            try (ResultSet rs = statement.executeQuery(sql);)
            {
                while (rs.next())
                {
                    ++returnValue;
                }
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return returnValue;
	}

	@Override
	public void delete(QuestionRound entity)
	{
        // ensure that delete does the very same as deleteById and vice versa
        deleteById(entity.getRoundId());
	}

	@Override
	public void deleteAll()
	{
        // @formatter:off
        try (
                Connection conn = connect();
                Statement statement = conn.createStatement();
            )
        // @formatter:on
        {
            statement.executeUpdate("DROP TABLE IF EXISTS " + TABLE_NAME);
            statement.executeUpdate(SQL_TO_CREATE_TABLE);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void deleteById(Long roundId)
	{
        // @formatter:off
        String sql = "DELETE FROM " + TABLE_NAME +
                     "  WHERE roundId=?";

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        // @formatter:on
        {
            pstmt.setLong(1, roundId);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
	}

	@Override
	public boolean existsById(Long roundId)
	{
        return findById(roundId).isPresent();
	}

	@Override
	public Iterable<QuestionRound> findAll()
	{
        ArrayList<QuestionRound> resultList = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        // @formatter:off
        try (
                Connection conn = connect();
                Statement statement = conn.createStatement();
            )
        // @formatter:on
        {
            try (ResultSet rs = statement.executeQuery(sql);)
            {
                while (rs.next())
                {
                	QuestionRound entity = buildQuestionRoundFromResultSet(rs);
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
	public Optional<QuestionRound> findById(Long roundId)
	{
        // define your result value for the Optional: may stays null!
		QuestionRound returnValue = null;

        // set up your query with '?' for all variables
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME +
                     "  WHERE roundId=?";

        // open connection and prepare statement using 'try-with-resource'
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        // @formatter:on
        {
            // insert variables into the '?' of our prepared query
            pstmt.setLong(1, roundId);// 1st param is index of '?'

            // manage query-result with try-with-resources to use the auto-closable feature
            try (ResultSet rs = pstmt.executeQuery();)
            {
                // find first by id:
                if (rs.next())
                { returnValue = buildQuestionRoundFromResultSet(rs); }
            }
        } catch (SQLException e)
        {
            // re-throw exception wrapped in a new runtime-exception, which is a uncheck
            // exception
            throw new RuntimeException(e);
        }

        // Wrap the result in the optional (may be null)
        return Optional.ofNullable(returnValue);
	}

	@Override
	public <S extends QuestionRound> S save(S entity)
	{
        if (existsById(entity.getRoundId()))
        {
            return update(entity);
        } else
        {
            return insert(entity);
        }
	}

	private <S extends QuestionRound> S insert(S entity)
	{
        // @formatter:off
        String sql =   "INSERT INTO " + TABLE_NAME + "(roundId,pause)"
                     + "  VALUES(?, ?)"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setLong(1, entity.getRoundId());
            pstmt.setInt(2, entity.getPause());
            
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getRoundId()).get();
        return s;
	}

	private <S extends QuestionRound> S update(S entity)
	{
	     // @formatter:off
        String sql =   "UPDATE " + TABLE_NAME
                     + "  SET round=?"
                     + "  WHERE roundId=?"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setInt(1, entity.getPause());
            pstmt.setLong(2, entity.getRoundId());

            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getRoundId()).get();
        return s;
	}

	private QuestionRound buildQuestionRoundFromResultSet(ResultSet rs) throws SQLException
	{
        // fetch each parameter from the query-result...
        Long foundID = rs.getLong("roundId");
        int pause = rs.getInt("pause");
        
        // ... and build a new QuestionRound
        return new QuestionRound(foundID, pause);
	}
}
