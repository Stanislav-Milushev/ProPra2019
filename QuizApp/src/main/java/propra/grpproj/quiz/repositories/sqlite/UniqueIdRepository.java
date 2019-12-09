package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.UniqueId;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;

public class UniqueIdRepository extends CrudRepositoryAdapter<UniqueId, Long>
{
	   /**
     * The table name managed by this repository
     */
    private static final String TABLE_NAME = "uniqueId";

    /**
     * The SQL query to create this table
     */
    private static final String SQL_TO_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (uniqueId INTEGER PRIMARY KEY, code STRING, "
    		+ "FOREIGN KEY(userId) REFERENCES user (userId), "
    		+ "FOREIGN KEY(pubEveningId) REFERENCES pubEvening (pubEveningId))";

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
	public void delete(UniqueId entity)
	{
        // ensure that delete does the very same as deleteById and vice versa
        deleteById(entity.getUniqueId());
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
	public void deleteById(Long uniqueId)
	{
        // @formatter:off
        String sql = "DELETE FROM " + TABLE_NAME +
                     "  WHERE uniqueId=?";

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        // @formatter:on
        {
            pstmt.setLong(1, uniqueId);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
	}

	@Override
	public boolean existsById(Long uniqueId)
	{
        return findById(uniqueId).isPresent();
	}

	@Override
	public Iterable<UniqueId> findAll()
	{
        ArrayList<UniqueId> resultList = new ArrayList<>();

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
                	UniqueId entity = buildUniqueIdFromResultSet(rs);
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
	public Optional<UniqueId> findById(Long uniqueId)
	{
        // define your result value for the Optional: may stays null!
		UniqueId returnValue = null;

        // set up your query with '?' for all variables
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME +
                     "  WHERE uniqueId=?";

        // open connection and prepare statement using 'try-with-resource'
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        // @formatter:on
        {
            // insert variables into the '?' of our prepared query
            pstmt.setLong(1, uniqueId);// 1st param is index of '?'

            // manage query-result with try-with-resources to use the auto-closable feature
            try (ResultSet rs = pstmt.executeQuery();)
            {
                // find first by id:
                if (rs.next())
                { returnValue = buildUniqueIdFromResultSet(rs); }
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
	public <S extends UniqueId> S save(S entity)
	{
        if (existsById(entity.getUniqueId()))
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
    

	private <S extends UniqueId> S insert(S entity)
	{
        // @formatter:off
        String sql =   "INSERT INTO " + TABLE_NAME + "(uniqueId,code)"
                     + "  VALUES(?, ?)"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setLong(1, entity.getUniqueId());
            pstmt.setString(2, entity.getCode());
            
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getUniqueId()).get();
        return s;
	}

	private <S extends UniqueId> S update(S entity)
	{
	     // @formatter:off
        String sql =   "UPDATE " + TABLE_NAME
                     + "  SET code=?"
                     + "  WHERE uniqueId=?"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setString(1, entity.getCode());
            pstmt.setLong(2, entity.getUniqueId());

            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getUniqueId()).get();
        return s;
	}

	private UniqueId buildUniqueIdFromResultSet(ResultSet rs) throws SQLException
	{
        // fetch each parameter from the query-result...
        Long foundID = rs.getLong("uniqueId");
        String code = rs.getString("code");

        // ... and build a new ScoreboardEntry
        return new UniqueId(foundID, code);
	}
    
}
