package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.Pub;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;

public class PubRepository extends CrudRepositoryAdapter<Pub, Long>
{
	/**
	 * The table name managed by this repository
	 */
	private static final String TABLE_NAME = "pub";

	/**
	 * The SQL query to create this table
	 */
	private static final String SQL_TO_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
			+ " (pubId INTEGER PRIMARY KEY, registered BOOLEAN, name STRING, "
			+ "FOREIGN KEY(userId) REFERENCES user (userId))";

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
	public void delete(Pub entity)
	{
		// ensure that delete does the very same as deleteById and vice versa
		deleteById(entity.getPubId());
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
	public void deleteById(Long pubId)
	{
		// @formatter:off
		String sql = "DELETE FROM " + TABLE_NAME + "  WHERE pubId=?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
		// @formatter:on
		{
			pstmt.setLong(1, pubId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean existsById(Long pubId)
	{
		return findById(pubId).isPresent();
	}

	@Override
	public Iterable<Pub> findAll()
	{
		ArrayList<Pub> resultList = new ArrayList<>();

		String sql = "SELECT * FROM " + TABLE_NAME;

		// @formatter:off
		try (Connection conn = connect(); Statement statement = conn.createStatement();)
		// @formatter:on
		{
			try (ResultSet rs = statement.executeQuery(sql);) {
				while (rs.next()) {
					Pub entity = buildPubEveningFromResultSet(rs);
					resultList.add(entity);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultList;
	}

	@Override
	public Optional<Pub> findById(Long pubId)
	{
        // define your result value for the Optional: may stays null!
		Pub returnValue = null;

        // set up your query with '?' for all variables
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME +
                     "  WHERE pubId=?";

        // open connection and prepare statement using 'try-with-resource'
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        // @formatter:on
        {
            // insert variables into the '?' of our prepared query
            pstmt.setLong(1, pubId);// 1st param is index of '?'

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
	public <S extends Pub> S save(S entity)
	{
        if (existsById(entity.getPubId()))
        {
            return update(entity);
        } else
        {
            return insert(entity);
        }
	}

	private <S extends Pub> S insert(S entity)
	{
        // @formatter:off
        String sql =   "INSERT INTO " + TABLE_NAME + "(pubId,registered,name)"
                     + "  VALUES(?, ?, ?)"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setLong(1, entity.getPubId());
            pstmt.setBoolean(2, entity.isRegistered());
            pstmt.setString(2, entity.getName());
            
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getPubId()).get();
        return s;
	}

	private <S extends Pub> S update(S entity)
	{
	     // @formatter:off
        String sql =   "UPDATE " + TABLE_NAME
                     + "  SET registered=?,name=?"
                     + "  WHERE pubId=?"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setBoolean(1, entity.isRegistered());
            pstmt.setString(2, entity.getName());
            pstmt.setLong(3, entity.getPubId());

            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getPubId()).get();
        return s;
	}

	private Pub buildPubEveningFromResultSet(ResultSet rs) throws SQLException
	{
        // fetch each parameter from the query-result...
        Long foundID = rs.getLong("pubId");
        boolean registered = rs.getBoolean("registered");
        String name = rs.getString("name");
        
        // ... and build a new Pub-Evening
        return new Pub(foundID, registered, name);
	}
}