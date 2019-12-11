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

import propra.grpproj.quiz.dataholders.User;
import propra.grpproj.quiz.repositories.CrudRepository;
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
    private static final String TABLE_NAME = "users";

    /**
     * The SQL query to create this table
     */
    // @formatter:off
    private static final String SQL_TO_CREATE_TABLE = 
              "CREATE TABLE " + TABLE_NAME
                + " (id INTEGER PRIMARY KEY,"
                + " username STRING,"
                + " password STRING,"
                + " email STRING, "
                + " role STRING)";
    // @formatter:on

    public Optional<User> findByUsernameAndPassword(String username, String password)
    {
        List<User> allUsers = CrudRepository.convertToList(findAll());

        return allUsers.stream()
                .filter(user -> {
                    return user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password);
                }).findFirst();
    }
    
    public Optional<User> findByEmail( String email ) {
        List<User> allUsers = CrudRepository.convertToList(findAll());
        
        return allUsers.stream()
                .filter(user -> {
                    return user.getEmail().equalsIgnoreCase(email);
                }).findFirst();
    }

    @Override
    public void createTable()
    {
        deleteAll();
    }

    @Override
    public Iterable<User> findAll()
    {
        String sql = "SELECT * FROM " + TABLE_NAME;

        ArrayList<User> resultList = new ArrayList<>();
        try (Connection conn = connect(); Statement statement = conn.createStatement();)
        {
            try (ResultSet rs = statement.executeQuery(sql);)
            {
                while (rs.next())
                {
                    User entity = buildUserFromResultSet(rs);
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
    public Optional<User> findById(Long id)
    {
        // @formatter:off
        String sql = "SELECT * FROM " + TABLE_NAME
                   + "  WHERE id=?";
        
        User returnValue = null;
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    returnValue = buildUserFromResultSet(rs);
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
    public <S extends User> S save(S entity)
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
    public void delete(User entity)
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

    private User buildUserFromResultSet(ResultSet rs) throws SQLException
    {
        Long id = rs.getLong("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String role = rs.getString("role");
        return new User(id, username, password, email, role);
    }

    private <S extends User> S insert(S entity)
    {
        // @formatter:off
        String sql = "INSERT INTO " + TABLE_NAME
                + "(id,username,password,email,role)"
                + "  VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setLong(1, entity.getId());
            pstmt.setString(2, entity.getUsername());
            pstmt.setString(3, entity.getPassword());
            pstmt.setString(4, entity.getEmail());
            pstmt.setString(5, entity.getRole());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // @formatter:on

        @SuppressWarnings("unchecked")
        S s = (S) findById(entity.getId()).get();
        return s;
    }

    private <S extends User> S update(S entity)
    {
        // @formatter:off
        String sql = "UPDATE " + TABLE_NAME
                + " SET username=?, password=?, email=?, role=?"
                + " WHERE id=?";

        try (
                Connection conn = connect(); 
                PreparedStatement pstmt = conn.prepareStatement(sql);
            ) 
        {
            pstmt.setString(1, entity.getUsername());
            pstmt.setString(2, entity.getPassword());
            pstmt.setString(3, entity.getEmail());
            pstmt.setString(4, entity.getRole());
            pstmt.setLong(5, entity.getId());

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
