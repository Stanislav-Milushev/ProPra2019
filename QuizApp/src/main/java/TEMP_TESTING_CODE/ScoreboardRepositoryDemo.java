package TEMP_TESTING_CODE;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import propra.grpproj.quiz.dataholders.ScoreboardEntity;
import propra.grpproj.quiz.repositories.sqlite.ScoreboardRepository;
import propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities;
/**
 * 
 * @author Daniel
 *
 */
public class ScoreboardRepositoryDemo
{

    private static final Logger LOG = LoggerFactory.getLogger(ScoreboardRepositoryDemo.class);

    public static void main(String[] args)
    {
        try
        {
        	
        	
            // SETUP
            cleanExistingTableAndCreateNewDummyData();
            ScoreboardRepository repo = new ScoreboardRepository();

            // ACT AND DEMO

            LOG.info("Count  : " + repo.count());
            LOG.info("Find 1 : " + repo.findById(1L));
            LOG.info("Find 9 : " + repo.findById(9L));

            LOG.info("Save 4 : " + repo.save(new ScoreboardEntity(4L, "Number4", 10)));
            LOG.info("Update 4 : " + repo.save(new ScoreboardEntity(4L, "New4Name", 9)));

            LOG.info("Count  : " + repo.count());
            LOG.info("Count  : " + repo.moreEfficientCountImpl());
            LOG.info("All    : " + repo.findAll());
            LOG.info("4 exists? : " + repo.existsById(4L));
            LOG.info("9 exists? : " + repo.existsById(9L));

            LOG.info("Delete 1");
            repo.delete(repo.findById(1L).get());
            LOG.info("1 exists? : " + repo.existsById(1L));

            LOG.info("Delete 2 by id");
            repo.deleteById(2L);
            LOG.info("2 exists? : " + repo.existsById(2L));

            repo.deleteAll();
            LOG.info("Count after delete all : " + repo.count());

            try
            {
                repo.findAllById(null);
            } catch (Exception e)
            {
                LOG.info("findAllById did throw as expected!");
            }

            try
            {
                repo.deleteAll(null);
            } catch (Exception e)
            {
                LOG.info("deleteAll did throw as expected!");
            }

            try
            {
                repo.saveAll(null);
            } catch (Exception e)
            {
                LOG.info("saveAll did throw as expected!");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void cleanExistingTableAndCreateNewDummyData()
    {
        // @formatter:off
        try (
                Connection connection = SqliteCoreUtilities.connect();
                Statement statement = connection.createStatement();
                )
        // @formatter:on
        {
            statement.setQueryTimeout(30);
            statement.executeUpdate("drop table if exists scoreboard");
            statement.executeUpdate("create table scoreboard (id integer, username string, score integer)");
            statement.executeUpdate("insert into scoreboard values(1, 'leo', 10)");
            statement.executeUpdate("insert into scoreboard values(2, 'yui', 9)");
            statement.executeUpdate("insert into scoreboard values(3, 'affe', 7)");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
