package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.utilities.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.PlayerOfRound;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;


/**
 * 
 * @author Daniel
 *
 */
public class PlayerOfRoundRepository extends CrudRepositoryAdapter<PlayerOfRound, Long>
{

    /**
     * The table name managed by this repository
     */
    private static final String TABLE_NAME = "playersOfRounds";

    /**
     * The SQL query to create this table
     */
    // @formatter:off
    private static final String SQL_TO_CREATE_TABLE = 
              "CREATE TABLE " + TABLE_NAME
                + " (id INTEGER PRIMARY KEY,"
                + " roundOfEveningRefId INTEGER,"
                + " userRefId INTEGER,"
                + " score INTEGER)";
    // @formatter:on

    @Override
    public void createTable()
    {
        deleteAll();
    }

    @Override
    public Iterable<PlayerOfRound> findAll()
    {
        String sql = "SELECT * FROM " + TABLE_NAME;

        ArrayList<PlayerOfRound> resultList = new ArrayList<>();
        try (Connection conn = connect(); Statement statement = conn.createStatement();)
        {
            try (ResultSet rs = statement.executeQuery(sql);)
            {
                while (rs.next())
                {
                    PlayerOfRound entity = buildPlayerOfRoundFromResultSet(rs);
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
    public Optional<PlayerOfRound> findById(Long id)
    {
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME
                   + "  WHERE id=?";
        
        PlayerOfRound returnValue = null;
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    returnValue = buildPlayerOfRoundFromResultSet(rs);
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
    public <S extends PlayerOfRound> S save(S entity)
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
    public void delete(PlayerOfRound entity)
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

    private PlayerOfRound buildPlayerOfRoundFromResultSet(ResultSet rs) throws SQLException
    {
        Long id = rs.getLong("id");
        Long roundOfEveningRefId = rs.getLong("roundOfEveningRefId");
        Long userRefId = rs.getLong("userRefId");
        int score = rs.getInt("score");
        return new PlayerOfRound(id, roundOfEveningRefId, userRefId, score);
    }

    private <S extends PlayerOfRound> S insert(S entity)
    {
        // @formatter:off
        String sql = "INSERT INTO " + TABLE_NAME
                + "(id,roundOfEveningRefId,userRefId,score)"
                + "  VALUES(?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setLong(1, entity.getId());
            pstmt.setLong(2, entity.getRoundOfEveningRefId());
            pstmt.setLong(3, entity.getUserRefId());
            pstmt.setInt(4, entity.getScore());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getId()).get();
        return s;
    }

    private <S extends PlayerOfRound> S update(S entity)
    {
        // @formatter:off
        String sql = "UPDATE " + TABLE_NAME
                + " SET roundOfEveningRefId=?, userRefId=?, score=?"
                + " WHERE id=?";

        try (
                Connection conn = connect(); 
                PreparedStatement pstmt = conn.prepareStatement(sql);
            ) 
        {
            pstmt.setLong(1, entity.getRoundOfEveningRefId());
            pstmt.setLong(2, entity.getUserRefId());
            pstmt.setInt(3, entity.getScore());
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

