package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.User;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;
/**
 * 
 * @author Daniel
 *
 */
public class UserRepository extends CrudRepositoryAdapter<User, Long>
{

    /**
     * The table name managed by this repository
     */
    private static final String TABLE_NAME = "user";
    
    /**
     * The SQL query to create this table
     */
    private static final String SQL_TO_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (userId INTEGER, username STRING, password STRING, email STRING)";

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
	public void delete(User entity)
	{
		deleteById(entity.getUserId());
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
	public void deleteById(Long userId)
	{
		  // @formatter:off
        String sql = "DELETE FROM " + TABLE_NAME +
                     "  WHERE userId=?";

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        // @formatter:on
        {
            pstmt.setLong(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
	}

	@Override
	public boolean existsById(Long userId)
	{
        return findById(userId).isPresent();
	}

	@Override
	public Iterable<User> findAll()
	{
		ArrayList<User> resultList = new ArrayList<>();

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
                	User user = buildUserFromResultSet(rs);
                    resultList.add(user);
                }
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return resultList;
	}

	@Override
	public Optional<User> findById(Long userId)
	{
        // define your result value for the Optional: may stays null!
        User returnValue = null;

        // set up your query with '?' for all variables
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME +
                     "  WHERE userId=?";

        // open connection and prepare statement using 'try-with-resource'
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        // @formatter:on
        {
            // insert variables into the '?' of our prepared query
            pstmt.setLong(1, userId);// 1st param is index of '?'

            // manage query-result with try-with-resources to use the auto-closable feature
            try (ResultSet rs = pstmt.executeQuery();)
            {
                // find first by id:
                if (rs.next())
                { returnValue = buildUserFromResultSet(rs); }
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
	public <S extends User> S save(S entity)
	{
        if (existsById(entity.getUserId()))
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

	private User buildUserFromResultSet(ResultSet rs) throws SQLException
	{
		// fetch each parameter from the query-result...
        Long foundID = rs.getLong("userId");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String email = rs.getString("email");

        // ... and build a new User-Entry
        return new User(foundID, username, password, email);
	}

	private <S extends User> S insert(S entity)
	{
		// @formatter:off
		String sql =   "INSERT INTO " + TABLE_NAME + "(userId,username,password,email)"
				+ "  VALUES(?, ?, ?, ?)"
				;
		
		try (
				Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				)
		{
			pstmt.setLong(1, entity.getUserId());
			pstmt.setString(2, entity.getUsername());
			pstmt.setString(3, entity.getPassword());
			pstmt.setString(4, entity.getEmail());
			
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		// @formatter:on
		
		@SuppressWarnings("unchecked")
		S s = (S) findById(entity.getUserId()).get();
		return s;
	}
	
	
	
	private <S extends User> S update(S entity)
	{
		// @formatter:off
		String sql =   "UPDATE " + TABLE_NAME
				+ "  SET username=?, password=?, email=?"
				+ "  WHERE userId=?"
				;
		
		try (
				Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				)
		{
			pstmt.setString(1, entity.getUsername());
			pstmt.setString(2, entity.getPassword());
			pstmt.setString(3, entity.getEmail());
			pstmt.setLong(4, entity.getUserId());
			
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		// @formatter:on
		
		@SuppressWarnings("unchecked")
		S s = (S) findById(entity.getUserId()).get();
		return s;
	}
}
