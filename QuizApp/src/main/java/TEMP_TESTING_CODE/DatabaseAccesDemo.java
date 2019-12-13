package TEMP_TESTING_CODE;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import propra.grpproj.quiz.SocketDataObjects.UserType;
import propra.grpproj.quiz.dataholders.Pub;
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

/**
 * 
 * @author Daniel
 *
 */
public class DatabaseAccesDemo
{

	private static final Logger LOG = LoggerFactory.getLogger(DatabaseAccesDemo.class);

	public static void main(String[] args)
	{

		try {

			CreateNewDummyData();

			// ---- SETUP
			EveningRepository repo1 = new EveningRepository();
			PlayerOfRoundRepository repo2 = new PlayerOfRoundRepository();
			PubRepository repo3 = new PubRepository();
			QuestionOfRoundRepository repo4 = new QuestionOfRoundRepository();
			QuestionRepository repo5 = new QuestionRepository();
			RoundsOfEveningRepository repo6 = new RoundsOfEveningRepository();
			UserRepository repo7 = new UserRepository();

			UserService serv1 = new UserService(repo7);
			EveningService serv2 = new EveningService(repo1, repo3);
			QuestionService serv3 = new QuestionService(repo5);
			PubService serv4 = new PubService(repo3, repo7);

			// ---- DEMO

			LOG.info("Find 1  : " + repo1.findById(1L));
			LOG.info("Find 2  : " + repo2.findById(2L));
			LOG.info("Find 3  : " + repo3.findById(3L));
			LOG.info("Find 4  : " + repo4.findById(4L));
			LOG.info("Find 5  : " + repo5.findById(5L));
			LOG.info("Find 6  : " + repo6.findById(6L));
			LOG.info("Find 7  : " + repo7.findById(7L));

			LOG.info("Find all  : " + repo1.findAll());
			LOG.info("Find all  : " + repo2.findAll());
			LOG.info("Find all  : " + repo3.findAll());
			LOG.info("Find all  : " + repo4.findAll());
			LOG.info("Find all  : " + repo5.findAll());
			LOG.info("Find all  : " + repo6.findAll());
			LOG.info("Find all  : " + repo7.findAll());

			LOG.info("Delete Willis Pub");
			repo3.delete(repo3.findById(31L).get());
			LOG.info("Willis Pub exists? : " + repo2.existsById(1L));
			LOG.info("Find 31  : " + repo3.findById(31L));
			LOG.info("Save 31 : " + repo3.save(new Pub(31L, "Willis next Pub", 3331L, false)));
			LOG.info("Update 31 : " + repo3.save(new Pub(31L, "Willis next Pub", 3331L, true)));

			repo3.deleteAll();
			LOG.info("Find 3 : " + repo3.findById(3L));

			try {
				repo3.findAllById(null);
			} catch (Exception e) {
				LOG.info("findAllById did throw as expected!");
			}

			try {
				repo3.saveAll(null);
			} catch (Exception e) {
				LOG.info("saveAll did throw as expected!");
			}

			// ---- ServiceDemo
			LOG.info("=============================================================================");
			LOG.info("=============================================================================");
			
			serv3.createNewQuestion(99L, "Wie?", "Weil", "Deshalb", "Darum", "Isso", "A", "Tjaja");
			LOG.info("Find 99  : " + repo5.findById(99L));
			
			serv4.createNewPub("Hodors Schenke", "Kasperstr. 1", 33L);
			LOG.info("Find all  : " + repo3.findAll());
			
			LOG.info("Gerries UserType? : " + serv1.getUserType("Gerry"));
			
			serv1.createNewUser("Jimmy", "jimmy@gmx.de", "pw", UserType.ADMIN);
			LOG.info("Find   : " + repo7.findById(1L));
			
			LOG.info("Gerrie authenticated? : " + serv1.authenticate("Gerry", "pw"));
			
			serv1.deleteUser("Gerry", "pw");
			LOG.info("Gerry exists? : " + repo7.existsById(7L));
			
			serv1.authenticate("Gerry", "pw");
			
			
			serv2.createNewEvening("BrexitQuiz", "2019-12-24_04:54", 20);
			LOG.info("Find all  : " + repo1.findAll());
			
			


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void CreateNewDummyData()
	{
		try (Connection connection = SqliteCoreUtilities.connect(); Statement statement = connection.createStatement();)

		{
			statement.setQueryTimeout(30);
			statement.executeUpdate("drop table if exists evenings");
			statement.executeUpdate("drop table if exists playersOfRounds");
			statement.executeUpdate("drop table if exists pubs");
			statement.executeUpdate("drop table if exists questionsOfRounds");
			statement.executeUpdate("drop table if exists questions");
			statement.executeUpdate("drop table if exists roundsOfEvenings");
			statement.executeUpdate("drop table if exists users");

			statement.executeUpdate("create table evenings (id integer, pubRefId integer, date string)");
			statement.executeUpdate(
					"create table playersOfRounds (id integer, roundOfEveningRefId integer, userRefId integer, score integer)");
			statement.executeUpdate(
					"create table pubs (id integer, name string, barkeeperUserRefId integer, officiallyRegistered boolean)");
			statement.executeUpdate(
					"create table questionsOfRounds (id integer, roundOfEveningRefId integer, questionRefId integer)");
			statement.executeUpdate(
					"create table questions (id integer, questionText string, answerA string, answerB string, answerC string, answerD string, correctAnswer string, description string)");
			statement.executeUpdate(
					"create table roundsOfEvenings (id integer, eveningRefId integer, pauseTime integer, password string)");
			statement.executeUpdate(
					"create table users (id integer, username string, password string, email string, role string)");

			statement.executeUpdate("insert into evenings values(1, 11, '2019-12-24')");
			statement.executeUpdate("insert into playersOfRounds values(2, 22, 222, 2222)");
			statement.executeUpdate("insert into pubs values(3,'Gerries Schalunke', 333, true)");
			statement.executeUpdate("insert into pubs values(31,'Willis Pub', 3331, true)");
			statement.executeUpdate("insert into questionsOfRounds values(4, 44, 444)");
			statement.executeUpdate(
					"insert into questions values(5, 'was?', 'achso', 'naja', 'ok', 'A', 'ist so', 'describe me')");
			statement.executeUpdate("insert into roundsOfEvenings values(6, 66, 666, 'pw')");
			statement.executeUpdate("insert into users values(7, 'Gerry', 'pw', 'bla@blub.de', 'admin')");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
