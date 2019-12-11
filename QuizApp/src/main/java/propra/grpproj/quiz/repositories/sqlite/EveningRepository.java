package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.utilities.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.Evening;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;

/**
 * 
 * @author Daniel
 *
 */
public class EveningRepository extends CrudRepositoryAdapter<Evening, Long>
{

    /**
     * The table name managed by this repository
     */
    private static final String TABLE_NAME = "evenings";

    /**
     * The SQL query to create this table
     */
    // @formatter:off
    private static final String SQL_TO_CREATE_TABLE = 
              "CREATE TABLE " + TABLE_NAME
                + " (id INTEGER PRIMARY KEY,"
                + " pubRefId INTEGER,"
                + " date STRING)";
    // @formatter:on

    @Override
    public void createTable()
    {
        deleteAll();
    }

    @Override
    public Iterable<Evening> findAll()
    {
        String sql = "SELECT * FROM " + TABLE_NAME;

        ArrayList<Evening> resultList = new ArrayList<>();
        try (Connection conn = connect(); Statement statement = conn.createStatement();)
        {
            try (ResultSet rs = statement.executeQuery(sql);)
            {
                while (rs.next())
                {
                    Evening entity = buildEveningFromResultSet(rs);
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
    public Optional<Evening> findById(Long id)
    {
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME
                   + "  WHERE id=?";
        
        Evening returnValue = null;
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    returnValue = buildEveningFromResultSet(rs);
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
    public <S extends Evening> S save(S entity)
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
    public void delete(Evening entity)
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

    private Evening buildEveningFromResultSet(ResultSet rs) throws SQLException
    {
        Long id = rs.getLong("id");
        Long pubRefId = rs.getLong("pubRefId");
        String date = rs.getString("date");
        return new Evening(id, pubRefId, date);
    }

    private <S extends Evening> S insert(S entity)
    {
        // @formatter:off
        String sql = "INSERT INTO " + TABLE_NAME
                + "(id,pubRefId,date)"
                + "  VALUES(?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setLong(1, entity.getId());
            pstmt.setLong(2, entity.getPubRefId());
            pstmt.setString(3, entity.getDate());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getId()).get();
        return s;
    }

    private <S extends Evening> S update(S entity)
    {
        // @formatter:off
        String sql = "UPDATE " + TABLE_NAME
                + " SET pubRefId=?, date=?"
                + " WHERE id=?";

        try (
                Connection conn = connect(); 
                PreparedStatement pstmt = conn.prepareStatement(sql);
            ) 
        {
            pstmt.setLong(1, entity.getPubRefId());
            pstmt.setString(2, entity.getDate());
            pstmt.setLong(3, entity.getId());

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
