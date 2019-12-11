package propra.grpproj.quiz.repositories.sqlite;

import static propra.grpproj.quiz.repositories.sqlite.utilities.SqliteCoreUtilities.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import propra.grpproj.quiz.dataholders.Pub;
import propra.grpproj.quiz.repositories.CrudRepository;
import propra.grpproj.quiz.repositories.CrudRepositoryAdapter;

/**
 * 
 * @author Daniel
 *
 */
public class PubRepository extends CrudRepositoryAdapter<Pub, Long>
{

    /**
     * The table name managed by this repository
     */
    private static final String TABLE_NAME = "pubs";

    /**
     * The SQL query to create this table
     */
    // @formatter:off
    private static final String SQL_TO_CREATE_TABLE = 
              "CREATE TABLE " + TABLE_NAME
                + " (id INTEGER PRIMARY KEY,"
                + " name STRING,"
                + " barkeeperUserRefId INTEGER,"
                + " officiallyRegistered BOOLEAN)";
    // @formatter:on

    public Optional<Pub> findByName(String name) {
        List<Pub> allPubs = CrudRepository.convertToList(findAll());
        return allPubs
                .stream()
                .filter(pub -> { return pub.getName().equalsIgnoreCase(name); })
                .findFirst();
    }
    
    @Override
    public void createTable()
    {
        deleteAll();
    }

    @Override
    public Iterable<Pub> findAll()
    {
        String sql = "SELECT * FROM " + TABLE_NAME;

        ArrayList<Pub> resultList = new ArrayList<>();
        try (Connection conn = connect(); Statement statement = conn.createStatement();)
        {
            try (ResultSet rs = statement.executeQuery(sql);)
            {
                while (rs.next())
                {
                    Pub entity = buildPubFromResultSet(rs);
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
    public Optional<Pub> findById(Long id)
    {
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME
                   + "  WHERE id=?";
        
        Pub returnValue = null;
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    returnValue = buildPubFromResultSet(rs);
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
    public <S extends Pub> S save(S entity)
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
    public void delete(Pub entity)
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

    private Pub buildPubFromResultSet(ResultSet rs) throws SQLException
    {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Long barkeeperUserRefId = rs.getLong("barkeeperUserRefId");
        boolean officiallyRegistered = rs.getBoolean("officiallyRegistered");
        return new Pub(id, name, barkeeperUserRefId, officiallyRegistered);
    }

    private <S extends Pub> S insert(S entity)
    {
        // @formatter:off
        String sql = "INSERT INTO " + TABLE_NAME
                + "(id,name,barkeeperUserRefId,officiallyRegistered)"
                + "  VALUES(?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setLong(1, entity.getId());
            pstmt.setString(2, entity.getName());
            pstmt.setLong(3, entity.getBarkeeperUserRefId());
            pstmt.setBoolean(4, entity.isOfficiallyRegistered());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getId()).get();
        return s;
    }

    private <S extends Pub> S update(S entity)
    {
        // @formatter:off
        String sql = "UPDATE " + TABLE_NAME
                + " SET name=?, barkeeperUserRefId=?, officiallyRegistered=?"
                + " WHERE id=?";

        try (
                Connection conn = connect(); 
                PreparedStatement pstmt = conn.prepareStatement(sql);
            ) 
        {
            pstmt.setString(1, entity.getName());
            pstmt.setLong(2, entity.getBarkeeperUserRefId());
            pstmt.setBoolean(3, entity.isOfficiallyRegistered());
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
