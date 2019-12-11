package propra.grpproj.quiz;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import propra.grpproj.quiz.dataholders.Question;
import propra.grpproj.quiz.repositories.sqlite.EveningRepository;
import propra.grpproj.quiz.repositories.sqlite.PlayerOfRoundRepository;
import propra.grpproj.quiz.repositories.sqlite.PubRepository;
import propra.grpproj.quiz.repositories.sqlite.QuestionOfRoundRepository;
import propra.grpproj.quiz.repositories.sqlite.QuestionRepository;
import propra.grpproj.quiz.repositories.sqlite.RoundsOfEveningRepository;
import propra.grpproj.quiz.repositories.sqlite.UserRepository;
import propra.grpproj.quiz.repositories.sqlite.utilities.SqliteCoreUtilities;
import propra.grpproj.quiz.services.EveningService;
import propra.grpproj.quiz.services.PubService;
import propra.grpproj.quiz.services.QuestionService;
import propra.grpproj.quiz.services.UserService;
import propra.grpproj.quiz.socket.SocketServer;

/**
 * The main entry point of the Quiz App
 */
public class App
{

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private final static int SERVER_PORT = 8080;

    public static void main(String[] args)
    {
        try
        {
            LOG.info("Launching the Quiz App...");

            /*
             * Setup database
             */
            LOG.info("Initialize database driver and drop database...");
            SqliteCoreUtilities.initializeDrive();
            SqliteCoreUtilities.initializeDatabase();

            // ---- setup repository instances

            LOG.info("  Create questions-table and populate with data from CSV file...");
            List<Question> questions = Question.readAllQuestionsFrom("docs/ProPra-Fragen-final.csv");
            QuestionRepository questionRepository = new QuestionRepository();
            questionRepository.createTable();
            for (Question question : questions)
            {
                questionRepository.save(question);
            }

            LOG.info("  Create users-table...");
            UserRepository userRepository = new UserRepository();
            userRepository.createTable();

            LOG.info("  Create pubs-table...");
            PubRepository pubRepository = new PubRepository();
            pubRepository.createTable();

            LOG.info("  Create evenings-table...");
            EveningRepository eveningRepository = new EveningRepository();
            eveningRepository.createTable();

            LOG.info("  Create roundsOfEvenings-table...");
            RoundsOfEveningRepository roundsOfEveningRepository = new RoundsOfEveningRepository();
            roundsOfEveningRepository.createTable();

            LOG.info("  Create questionsOfRounds-table...");
            QuestionOfRoundRepository questionOfRoundRepository = new QuestionOfRoundRepository();
            questionOfRoundRepository.createTable();

            LOG.info("  Create playersOfRounds-table...");
            PlayerOfRoundRepository playerOfRoundRepository = new PlayerOfRoundRepository();
            playerOfRoundRepository.createTable();

            // ---- setup service instances

            LOG.info("Create services...");

            PubService pubService = new PubService(pubRepository, userRepository);
            EveningService eveningService = new EveningService(eveningRepository, pubRepository);
            UserService userService = new UserService(userRepository);
            QuestionService questionService = new QuestionService(questionRepository);

            // @formatter:off

            /*
             * 
             * TODO create and setup all your instances here using proper dependency
             * injection... /*
             * 
             */

            // LOGIC setup goes here. Please use the XxxService classes to read, write and
            // edit any domain-objects such as creating new user, new pubs, new game-laps in
            // a pub at a certain evening and add new players to that game
            
            //                                              dependency injection of XxxServices
            //                                                            |
            //                                                            |
            //                                                            v
            // LoginController loginController = new LoginController( userService )
            // AdminGuiController adminGuiController = new AdminGuiController();
            // ...
            
            
            startTheServer(pubService, eveningService, userService, questionService);

            
            /*
             * 
             * TODO setup up and show GUI...
             * 
             */

            // GUI setup goes here. Please use the XXXController classes serving the entire
            // app-logic and domain-object to the GUI, such as displaying a list of all pubs
            // or creating new users and/or admins (pretty much the same as in the comment
            // above for the logic!).

            //                               dependency injection of XXXServices
            //                                             |
            //                                             |
            //                                             v
            // GuiWindow guiWindow = new GuiWindow( loginController, adminGuiController, ... , XxxController );
            // SwingUtilities.invokeLater( ()->{ /* guiWindow.open() */ } );
            // this launches 1 demo GUI and you may want to launch a second one to demonstrate multi-client stuff!
            
            // SwingUtilities.invokeLater( ()->{ /* guiWindow.open() */ } );
            // SwingUtilities.invokeLater( ()->{ /* guiWindow.open() */ } );
            // SwingUtilities.invokeLater( ()->{ /* guiWindow.open() */ } );
            // SwingUtilities.invokeLater( ()->{ /* guiWindow.open() */ } );

            // @formatter:on

        } catch (Exception e)
        {
            // App crash while start-up.
            e.printStackTrace();
        }
    }

    /**
     * This method launches the server in it's own thread pool. Hopefully the server
     * awaits and accepts clients now!
     * 
     * @param pubService
     * @param eveningService
     * @param userService
     * @param questionService
     */
    private static void startTheServer(
            PubService pubService, EveningService eveningService, UserService userService,
            QuestionService questionService
    )
    {
        SocketServer socketServer = new SocketServer(SERVER_PORT, pubService, eveningService, userService,
                questionService);
        ExecutorService executor = Executors.newFixedThreadPool(2,
                runnable -> { return new Thread(runnable, "SocketServerThread"); });
        Future<?> future = executor.submit(socketServer);
        executor.submit(() -> {
            // submit a second task, that awaits the socketServer to get terminated and if
            // that has happened,
            // we destroy this thread-pool itself!
            // This all should be executed if the Master-Gui gets closed...
            try
            {
                future.get();
            } catch (Exception e)
            {
                /* app was closed, silence please! */
            } finally
            {
                executor.shutdownNow();
            }
        });
    }

}
