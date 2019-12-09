package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.Admin;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;

public class AdminRepository extends CrudRepositoryAdapter<Admin, Long>
{
	/**
	 * The table name managed by this repository
	 */
	private static final String TABLE_NAME = "scoreboard";

	/**
	 * The SQL query to create this table
	 */
	private static final String SQL_TO_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (adminId INTEGER PRIMARY KEY, "
			+ "FOREIGN KEY(userId) REFERENCES user (userId)) ";

	@Override
	public void delete(Admin entity)
	{
		// ensure that delete does the very same as deleteById and vice versa
		deleteById(entity.getAdminId());
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
	public void deleteById(Long adminId)
	{
		// @formatter:off
		String sql = "DELETE FROM " + TABLE_NAME + "  WHERE adminId=?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
		// @formatter:on
		{
			pstmt.setLong(1, adminId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Iterable<Admin> findAll()
	{
		ArrayList<Admin> resultList = new ArrayList<>();

		String sql = "SELECT * FROM " + TABLE_NAME;

		// @formatter:off
		try (Connection conn = connect(); Statement statement = conn.createStatement();)
		// @formatter:on
		{
			try (ResultSet rs = statement.executeQuery(sql);) {
				while (rs.next()) {
					Admin entity = buildAdminFromResultSet(rs);
					resultList.add(entity);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultList;
	}

	@Override
	public Optional<Admin> findById(Long adminId)
	{
		// define your result value for the Optional: may stays null!
		Admin returnValue = null;

		// set up your query with '?' for all variables
		// @formatter:off
		String sql = "SELECT * FROM " + TABLE_NAME + "  WHERE adminId=?";

		// open connection and prepare statement using 'try-with-resource'
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
		// @formatter:on
		{
			// insert variables into the '?' of our prepared query
			pstmt.setLong(1, adminId);// 1st param is index of '?'

			// manage query-result with try-with-resources to use the auto-closable feature
			try (ResultSet rs = pstmt.executeQuery();) {
				// find first by id:
				if (rs.next()) {
					returnValue = buildAdminFromResultSet(rs);
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
	public <S extends Admin> S save(S entity)
	{
			return insert(entity);
	}

	// ========================================================================
	// helpers
	// ========================================================================

	private <S extends Admin> S insert(S entity)
	{
        // @formatter:off
        String sql =   "INSERT INTO " + TABLE_NAME + "(adminId)"
                     + "  VALUES(?)"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setLong(1, entity.getAdminId());
            
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getAdminId()).get();
        return s;
	}

	private Admin buildAdminFromResultSet(ResultSet rs) throws SQLException
	{
		// fetch each parameter from the query-result...
		Long foundID = rs.getLong("adminId");

		// ... and build a new Admin
		return new Admin(foundID);
	}
}
