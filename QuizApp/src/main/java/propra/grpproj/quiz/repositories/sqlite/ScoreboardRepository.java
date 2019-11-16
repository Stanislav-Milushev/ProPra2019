package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.ScoreboardEntity;
import propra.grpproj.quiz.repositories.CrudRepository;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;

/**
 * The Repository to store ScoreboardEntities.
 */
public class ScoreboardRepository extends CrudRepositoryAdapter<ScoreboardEntity, Long>
{

    /**
     * The table name managed by this repository
     */
    private static final String TABLE_NAME = "scoreboard";

    /**
     * The SQL query to create this table
     */
    private static final String SQL_TO_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (id INTEGER, username STRING, score INTEGER)";

    @Override
    public long count()
    {
        return CrudRepository.convertToList(findAll()).size();
    }

    /**
     * Just to keep the working code some where
     * 
     * @see ScoreboardRepository#count()
     */
    public long moreEfficientCountImpl()
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
    public void delete(ScoreboardEntity entity)
    {
        // ensure that delete does the very same as deleteById and vice versa
        deleteById(entity.getId());
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
    public void deleteById(Long id)
    {
        // @formatter:off
        String sql = "DELETE FROM " + TABLE_NAME +
                     "  WHERE id=?";

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        // @formatter:on
        {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsById(Long id)
    {
        return findById(id).isPresent();
    }

    @Override
    public Iterable<ScoreboardEntity> findAll()
    {
        ArrayList<ScoreboardEntity> resultList = new ArrayList<>();

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
                    ScoreboardEntity entity = buildScoreboardEntityFromResultSet(rs);
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
    public Optional<ScoreboardEntity> findById(Long id)
    {
        // define your result value for the Optional: may stays null!
        ScoreboardEntity returnValue = null;

        // set up your query with '?' for all variables
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME +
                     "  WHERE id=?";

        // open connection and prepare statement using 'try-with-resource'
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        // @formatter:on
        {
            // insert variables into the '?' of our prepared query
            pstmt.setLong(1, id);// 1st param is index of '?'

            // manage query-result with try-with-resources to use the auto-closable feature
            try (ResultSet rs = pstmt.executeQuery();)
            {
                // find first by id:
                if (rs.next())
                { returnValue = buildScoreboardEntityFromResultSet(rs); }
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
    public <S extends ScoreboardEntity> S save(S entity)
    {
        if (existsById(entity.getId()))
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

    private ScoreboardEntity buildScoreboardEntityFromResultSet(ResultSet rs) throws SQLException
    {
        // fetch each parameter from the query-result...
        Long foundID = rs.getLong("id");
        String username = rs.getString("username");
        int score = rs.getInt("score");

        // ... and build a new ScoreboardEntry
        return new ScoreboardEntity(foundID, username, score);
    }

    private <S extends ScoreboardEntity> S insert(S entity)
    {
        // @formatter:off
        String sql =   "INSERT INTO " + TABLE_NAME + "(id,username,score)"
                     + "  VALUES(?, ?, ?)"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setLong(1, entity.getId());
            pstmt.setString(2, entity.getUsername());
            pstmt.setLong(3, entity.getScore());
            
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getId()).get();
        return s;
    }

    private <S extends ScoreboardEntity> S update(S entity)
    {
     // @formatter:off
        String sql =   "UPDATE " + TABLE_NAME
                     + "  SET username=?, score=?"
                     + "  WHERE id=?"
                     ;

        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
            )
        {
            pstmt.setString(1, entity.getUsername());
            pstmt.setLong(2, entity.getScore());
            pstmt.setLong(3, entity.getId());

            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getId()).get();
        return s;
    }

}
