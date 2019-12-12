package propra.grpproj.quiz;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import propra.grpproj.gui.GuiUserLogin;
import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.dataholders.Question;
import propra.grpproj.quiz.repositories.sqlite.EveningRepository;
import propra.grpproj.quiz.repositories.sqlite.PlayerOfRoundRepository;
import propra.grpproj.quiz.repositories.sqlite.PubRepository;
import propra.grpproj.quiz.repositories.sqlite.QuestionOfRoundRepository;
import propra.grpproj.quiz.repositories.sqlite.QuestionRepository;
import propra.grpproj.quiz.repositories.sqlite.RoundsOfEveningRepository;
import propra.grpproj.quiz.repositories.sqlite.UserRepository;
import propra.grpproj.quiz.repositories.sqlite.utilities.SqliteCoreUtilities;

/**
 * The main entry point of the Quiz App.
 * 
 * @author Daniel
 *
 */
public class App
{

    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    
    private final static int SERVER_PORT = 9111;

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

            LOG.info("Launching Server...");
            startTheServer();  
            
            // ---- setup and show GUI 
            
            LOG.info("Launching GUI...");
            launchGui();

           

        } catch (Exception e)
        {
            // App crash while start-up.
            e.printStackTrace();
        }
    }

	private static void launchGui()
	{
        SwingUtilities.invokeLater( ()->{
			GuiUserLogin window = new GuiUserLogin();
			window.getFrmUserLogin().setVisible(true);
        });	
        
//      SwingUtilities.invokeLater( ()->{
//			GuiMenu window = new GuiMenu();
//			window.getFrame().setVisible(true);
//		});
//      
//      SwingUtilities.invokeLater( ()->{
//      	GuiAdmin window = new GuiAdmin();
//      	window.getFrmAdmin().setVisible(true);
//      	GuiAdmin.getInstance();
//      });
//      
//      SwingUtilities.invokeLater( ()->{
//			GuiOptions window = new GuiOptions();
//			window.getFrame().setVisible(true);
//      });
//      
//      SwingUtilities.invokeLater( ()->{
//			GuiPubOwner window = new GuiPubOwner();
//			window.getFramePub().setVisible(true);
//      });
//      
//      SwingUtilities.invokeLater( ()->{
//			GuiRegister window = new GuiRegister();
//			window.getFrameRegister().setVisible(true);
//      });

	}

	private static void startTheServer()
	{
		 SocketServer socketServer = new SocketServer(SERVER_PORT);
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
