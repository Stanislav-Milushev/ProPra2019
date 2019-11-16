package propra.grpproj.quiz.repositories.sqlite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Methods to be reused with database operations. E.g. a connect method that is
 * to be used by any repository-implementation.
 */
public class SqliteCoreUtilities
{

    /**
     * TODO Set to false in final release
     */
    private static final boolean DROP_DB_ON_INIT = true;

    /**
     * THe path in the file system to the sqlite *.db file.
     */
    private static final String PATH_TO_DATABASE_URL = "database/sqlite/repository.db";

    /**
     * The full url to connect to database.
     */
    private static final String FULL_JDBC_DATABASE_URL = "jdbc:sqlite:" + PATH_TO_DATABASE_URL;

    /**
     * Registers the sqlite driver
     * 
     * @throws ClassNotFoundException
     */
    public static void initializeDrive() throws ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
    }

    /**
     * Drops the database when in developer mode
     * 
     * @throws IOException
     */
    public static void initializeDatabase() throws IOException
    {
        if (DROP_DB_ON_INIT)
        { Files.deleteIfExists(Paths.get(PATH_TO_DATABASE_URL)); }

        // TODO ensure path to directory exists!!! (database/sqlite)

        // may set up things like creating a administrator account if not present in
        // user-repository!

    }

    /**
     * Connect to the sqlite database.
     * 
     * @return a connection to the URL
     * @throws SQLException if a database access error occurs or the url is null
     */
    public static Connection connect() throws SQLException
    {
        return DriverManager.getConnection(FULL_JDBC_DATABASE_URL);
    }

}
