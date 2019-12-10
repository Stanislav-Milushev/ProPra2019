package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.PubEvening;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;

public class PubEveningRepository extends CrudRepositoryAdapter<PubEvening, Long>
{
    /**
     * The table name managed by this repository
     */
    private static final String TABLE_NAME = "pubEvening";

    /**
     * The SQL query to create this table
     */
    private static final String SQL_TO_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (pubEveningId INTEGER PRIMARY KEY, timeForAnswering INTEGER, pubId INTEGER, "
    		+ "FOREIGN KEY(pubId) REFERENCES pub (pubId))";

	@Override
	public void delete(PubEvening entity)
	{
        // ensure that delete does the very same as deleteById and vice versa
        deleteById(entity.getPubEveningId());
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
	public void deleteById(Long pubEveningId)
	{
        // @formatter:off
        String sql = "DELETE FROM " + TABLE_NAME +
                     "  WHERE pubEveningId=?";

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        // @formatter:on
        {
            pstmt.setLong(1, pubEveningId);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
	}

	@Override
	public boolean existsById(Long pubEveningId)
	{
        return findById(pubEveningId).isPresent();
	}

	@Override
	public Iterable<PubEvening> findAll()
	{
        ArrayList<PubEvening> resultList = new ArrayList<>();

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
                	PubEvening entity = buildPubEveningFromResultSet(rs);
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
	public Optional<PubEvening> findById(Long pubEveningId)
	{
        // define your result value for the Optional: may stays null!
		PubEvening returnValue = null;

        // set up your query with '?' for all variables
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME +
                     "  WHERE pubEveningId=?";

        // open connection and prepare statement using 'try-with-resource'
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        // @formatter:on
        {
            // insert variables into the '?' of our prepared query
            pstmt.setLong(1, pubEveningId);// 1st param is index of '?'

            // manage query-result with try-with-resources to use the auto-closable feature
            try (ResultSet rs = pstmt.executeQuery();)
            {
                // find first by id:
                if (rs.next())
                { returnValue = buildPubEveningFromResultSet(rs); }
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
	public <S extends PubEvening> S save(S entity)
	{
        if (existsById(entity.getPubEveningId()))
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
    
	private <S extends PubEvening> S insert(S entity)
	{
        // @formatter:off
        String sql =   "INSERT INTO " + TABLE_NAME + "(pubEveningId,timeForAnswering,pubId)"
                     + "  VALUES(?, ?, ?)"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setLong(1, entity.getPubEveningId());
            pstmt.setLong(2, entity.getPubId());
            pstmt.setInt(3, entity.getTimeForAnswering());
            
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getPubEveningId()).get();
        return s;
	}

	private <S extends PubEvening> S update(S entity)
	{
	     // @formatter:off
        String sql =   "UPDATE " + TABLE_NAME
                     + "  SET timeForAnswering=?,pubId=?"
                     + "  WHERE pubEveningId=?"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setInt(1, entity.getTimeForAnswering());
            pstmt.setLong(2, entity.getPubId());
            pstmt.setLong(3, entity.getPubEveningId());

            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getPubEveningId()).get();
        return s;
	}

	private PubEvening buildPubEveningFromResultSet(ResultSet rs) throws SQLException
	{
        // fetch each parameter from the query-result...
        Long foundID = rs.getLong("pubEveningId");
        int timeForAnswering = rs.getInt("timeForAnswering");
        Long pubId = rs.getLong("pubId");
        
        // ... and build a new Pub-Evening
        return new PubEvening(foundID, timeForAnswering, pubId);
	}

}
