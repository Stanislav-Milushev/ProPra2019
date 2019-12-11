package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.utilities.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.RoundsOfEvening;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;


/**
 * 
 * @author Daniel
 *
 */
public class RoundsOfEveningRepository extends CrudRepositoryAdapter<RoundsOfEvening, Long>
{

    /**
     * The table name managed by this repository
     */
    private static final String TABLE_NAME = "roundsOfEvenings";

    /**
     * The SQL query to create this table
     */
    // @formatter:off
    private static final String SQL_TO_CREATE_TABLE = 
              "CREATE TABLE " + TABLE_NAME
                + " (id INTEGER PRIMARY KEY,"
                + " eveningRefId INTEGER,"
                + " pauseTime INTEGER,"
                + " password STRING)";
    // @formatter:on

    @Override
    public void createTable()
    {
        deleteAll();
    }

    @Override
    public Iterable<RoundsOfEvening> findAll()
    {
        String sql = "SELECT * FROM " + TABLE_NAME;

        ArrayList<RoundsOfEvening> resultList = new ArrayList<>();
        try (Connection conn = connect(); Statement statement = conn.createStatement();)
        {
            try (ResultSet rs = statement.executeQuery(sql);)
            {
                while (rs.next())
                {
                    RoundsOfEvening entity = buildRoundsOfEveningFromResultSet(rs);
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
    public Optional<RoundsOfEvening> findById(Long id)
    {
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME
                   + "  WHERE id=?";
        
        RoundsOfEvening returnValue = null;
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    returnValue = buildRoundsOfEveningFromResultSet(rs);
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
    public <S extends RoundsOfEvening> S save(S entity)
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
    public void delete(RoundsOfEvening entity)
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

    private RoundsOfEvening buildRoundsOfEveningFromResultSet(ResultSet rs) throws SQLException
    {
        Long id = rs.getLong("id");
        Long eveningRefId = rs.getLong("eveningRefId");
        int pauseTime = rs.getInt("pauseTime");
        String password = rs.getString("password");
        return new RoundsOfEvening(id, eveningRefId, pauseTime, password);
    }

    private <S extends RoundsOfEvening> S insert(S entity)
    {
        // @formatter:off
        String sql = "INSERT INTO " + TABLE_NAME
                + "(id,eveningRefId,pauseTime,password)"
                + "  VALUES(?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setLong(1, entity.getId());
            pstmt.setLong(2, entity.getEveningRefId());
            pstmt.setInt(3, entity.getPauseTime());
            pstmt.setString(4, entity.getPassword());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getId()).get();
        return s;
    }

    private <S extends RoundsOfEvening> S update(S entity)
    {
        // @formatter:off
        String sql = "UPDATE " + TABLE_NAME
                + " SET eveningRefId=?, pauseTime=?, password=?"
                + " WHERE id=?";

        try (
                Connection conn = connect(); 
                PreparedStatement pstmt = conn.prepareStatement(sql);
            ) 
        {
            pstmt.setLong(1, entity.getEveningRefId());
            pstmt.setInt(2, entity.getPauseTime());
            pstmt.setString(3, entity.getPassword());
            pstmt.setLong(4, entity.getId());

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
