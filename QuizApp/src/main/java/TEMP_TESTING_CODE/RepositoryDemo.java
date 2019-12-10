package TEMP_TESTING_CODE;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import propra.grpproj.quiz.dataholders.ScoreboardEntity;
import propra.grpproj.quiz.repositories.sqlite.AdminRepository;
import propra.grpproj.quiz.repositories.sqlite.PubRepository;
import propra.grpproj.quiz.repositories.sqlite.ScoreboardRepository;
import propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities;
import propra.grpproj.quiz.repositories.sqlite.UserRepository;
/**
 * 
 * @author Daniel
 *
 */
public class RepositoryDemo
{

    private static final Logger LOG = LoggerFactory.getLogger(RepositoryDemo.class);

    public static void main(String[] args)
    {
        try
        {
        	
        	
            // SETUP
            cleanExistingTableAndCreateNewDummyData();
            ScoreboardRepository repo = new ScoreboardRepository();
            UserRepository repo1 = new UserRepository();
            AdminRepository repo2 = new AdminRepository();
            PubRepository repo3 = new PubRepository();
//            QuestionRepository repo4 = new QuestionRepository();
//            QuestionRoundRepository repo5 = new QuestionRoundRepository();
//            QuestionsAndAnswersRepository repo6 = new QuestionsAndAnswersRepository();
//            UniqueIdRepository repo7 = new UniqueIdRepository();

            // ACT AND DEMO

            LOG.info("Count  : " + repo.count());
            LOG.info("Find 1 : " + repo.findById(1L));
            LOG.info("Find 9 : " + repo.findById(9L));
            
            
            LOG.info("Find Admin 1 : " + repo2.findById(1L));
            LOG.info("Find Admin 2 : " + repo2.findById(2L));
            LOG.info("All Admins: " + repo2.findAll());
            LOG.info("Count  : " + repo2.count());
            LOG.info("Delete Admin 1");
            repo2.delete(repo2.findById(1L).get());
            LOG.info("Admin 1 exists? : " + repo2.existsById(1L));
            LOG.info("Count  : " + repo2.count());
            
//          LOG.info("Save 2 : " + repo3.save(2L, true, "Freddies Schalunke", 722L));                    
            LOG.info("Find 69 : " + repo3.findById(69L));
            

            LOG.info("Save 4 : " + repo.save(new ScoreboardEntity(4L, "Number4", 10, 22L, 21L)));
            LOG.info("Update 4 : " + repo.save(new ScoreboardEntity(4L, "New4Name", 9, 22L, 21L)));

            LOG.info("Count  : " + repo3.count());
            LOG.info("Count  : " + repo.moreEfficientCountImpl());
            LOG.info("All    : " + repo.findAll());
            LOG.info("4 exists? : " + repo.existsById(4L));
            LOG.info("9 exists? : " + repo.existsById(9L));
            
            LOG.info("Find 77 : " + repo1.findById(77L));

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
            statement.executeUpdate("drop table if exists user");
            statement.executeUpdate("drop table if exists admin");
            statement.executeUpdate("drop table if exists pub");
            
            statement.executeUpdate("create table scoreboard (id integer, username string, score integer, userId integer, pubEveningId integer)");
            statement.executeUpdate("create table user (userId integer, username string, password string, email string)");
            statement.executeUpdate("create table admin (adminId integer, userId integer)");
            statement.executeUpdate("create table pub (pubId integer, registered boolean, name string, userId integer)");

            
            statement.executeUpdate("insert into scoreboard values(1, 'leo', 10, 77, 21)");
            statement.executeUpdate("insert into scoreboard values(2, 'yui', 9, 55, 21)");
            statement.executeUpdate("insert into scoreboard values(3, 'affe', 7, 66, 21)");
            
            statement.executeUpdate("insert into user values(77, 'gerry', 'pw', 'gerry@mail.com')");
            
            statement.executeUpdate("insert into admin values(1, 77)");
            statement.executeUpdate("insert into admin values(2, 77)");
            statement.executeUpdate("insert into pub values(69, true, 'Gerries Schalunke', 78)");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
