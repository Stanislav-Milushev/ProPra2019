package propra.grpproj.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import TEMP_TESTING_CODE.ScoreboardRepositoryDemo;
import propra.grpproj.quiz.dataholders.ScoreboardEntity;
import propra.grpproj.quiz.repositories.CrudRepository;
import propra.grpproj.quiz.repositories.sqlite.ScoreboardRepository;
import propra.grpproj.quiz.repositories.sqlite.SqliteCoreUtilities;
import propra.grpproj.quiz.services.ScoreboardService;
import propra.grpproj.quiz.services.UserService;

/**
 * The main entry point of the Quiz App
 */
public class App
{

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args)
    {
        try
        {
            LOG.info("Launching the Quiz App...");

            /*
             * Setup database
             */
            LOG.info("Initialize database...");
            SqliteCoreUtilities.initializeDrive();
            SqliteCoreUtilities.initializeDatabase();

            /*
             * TODO setup up with dependency injection...
             */

            LOG.info("Create services...");

            CrudRepository<ScoreboardEntity, Long> scoreboardRepository = new ScoreboardRepository();
            ScoreboardService scoreboardService = new ScoreboardService(scoreboardRepository);

            // CrudRepository<User, Long> userRepository = new UserRepository();
            UserService userService = new UserService(
                    scoreboardService /* please insert the UserRepository as type: CrudRepository<User, Long> */ );

            ScoreboardRepositoryDemo.cleanExistingTableAndCreateNewDummyData();

            /*
             * TODO setup up and show GUI...
             */
            // SwingUtilities.invokeLater( ()->{ /* gui.open() */ } );

        } catch (Exception e)
        {
            // App crash while start-up.
            e.printStackTrace();
        }
    }
    
    
    
    
}
