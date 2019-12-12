package propra.grpproj.logic;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import propra.grpproj.quiz.dataholders.Pub;
import propra.grpproj.quiz.repositories.sqlite.PubRepository;
import propra.grpproj.quiz.repositories.sqlite.UserRepository;
import propra.grpproj.quiz.repositories.sqlite.utilities.SqliteCoreUtilities;
import propra.grpproj.quiz.services.PubService;
import propra.grpproj.quiz.SocketDataObjects.*;


////////////////////////////////////////////////////////////////////////////
// Manager class to execute all SQL query
// @author: Marius Discher
//
//
//
// 



public class DatabaseManager {
	
	
	private ArrayList<String> pubs = new ArrayList<String>();

	Connection connection = null;
	
	Encrypt encr = new Encrypt();
	
	
	
	// Create a connection to the database
	public void connection() throws SQLException {
		
		connection = SqliteCoreUtilities.connect();
		
	}
	
	
	
	// Close the connection to the database
	public void closeconnection() throws SQLException {
		
		this.connection.close();
		
	}
	
	
	
	// Function to register a user
	//usertype added
	public boolean registerUser(String username, String email, String passwd, UserType usertype) throws SQLException {
		
		// false = cannot register the user
		boolean r_check = false;
		
		int password = encr.encrypt(passwd);
		
		int userid = getLatestID() +1;
		
		String usertyp = String.valueOf(usertype);
		
		String query = "Insert into user (userID, username, email, password, type) Values (?,?,?,?,?)";
		
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, userid);
		ps.setString (2,username);
		ps.setString(2, email);
		ps.setInt(3, password);
		ps.setString(4, usertyp);
		
		r_check = searchUser(username);
		
		
		return r_check;
	}

	
	
	// Function to check for an email of a user
	public boolean checkExist(String email) throws SQLException {
		
		// false means empty
		boolean e_check = true;
		
		connection();
		
		String query = "Select email From user Where email =" + email;
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		int columns = rs.getMetaData().getColumnCount();
		
		while (rs.next()) {
		
			for(int i = 1; i<=columns; i++) {
				e_check = isEmpty(rs.getString(i));
			}
		}
		
		rs.close();
		stmt.close();
		
		
		return e_check;
	}
	
	
	// Function to check, if result of a query is empty
	public boolean isEmpty(String value) {
		
		return value.length() == 0;
	}
	
	
	
	// Function to authenticate login of a user
	public boolean login(String email, String password) throws SQLException {
		
		boolean l_check = false;
		
		boolean e_check = false;
		
		e_check = checkExist(email);
		
		if (e_check == true ) {
			
			l_check = encr.login(email, password);
			
		}
		
		
		
		return l_check;
	}
	
	
	// Get the latest and unused userID from the Database
	public int getLatestID() throws SQLException {
		
		int id = 1;
		
		String query = "Select Max(userID) from user";
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		 for (;rs.next();) {
			 
			 id = rs.getInt(0);
		 }
		
		return id;
	}
	
	
	
	// Get all registered pubs
	public ArrayList<Pub> getPubs() throws SQLException {
		
		ArrayList<Pub> pubs = new ArrayList<Pub>();
		
		Object var;
		
		Pub pub;
		
		String query ="Select name, owner, id from pub";
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
		
		for (int i=0;rs.next();) {
			
			//pubs = (ArrayList<Pub>) rs.getObject(i);
			//var = rs.getObject(i);
			 pubs.add((Pub) rs.getObject(i));
		}
		
		return pubs;
		
		// Maybe create Pub Objects and send them to the gui
		
	}
	
	
	
	// Function to handle the approve of a pub
	public void approvePub(String name, String owner) throws SQLException {
		
		String query = "Update pub value approved = ? where name = ? AND owner = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setBoolean(1, true);
		ps.setString(2, name);
		ps.setString(3, owner);
	
		
	}
	
	
	
	// Function to get all registered users
	public void getAllUser() throws SQLException {
		
		String query = "Select name, email from user";
				
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
		
	}
	
	
	
	// Function to get the encrypted password from the database
	public int getEncryptedPassword (String email) throws SQLException {
		
		int password = 0;
		
		String query = "Select password From user Where email =" + email;
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		for (;rs.next();) {
			
			password = rs.getInt(0);
		}
		
		rs.close();
		
		stmt.close();
		
		return password;
	}
	
	
	
	// Write the score after a completed quiz to the user db
	public void writePoints (String name, double score) throws SQLException {
		
		String query = "Update user value score = ? Where username = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setDouble(1, score);
		ps.setString(2, name);
		
		
	}
	
	
	
	// Get the score from a user
	public double getScore(String name) throws SQLException {
		
		double score = 0;
		
		String query = "Select score From user Where username =" + name;
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		for (;rs.next();) {
			
			score = rs.getInt(0);
		}
		
		rs.close();
		
		stmt.close();
		
		return score;
	}
	
	
	
	// Function to reset the score from all users
	public void resetPoints() throws SQLException {
		
		double score = 0.0;
		
		String query = "Update user value score = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setDouble(1, score);
		
	}
	
	public void getAllQuestions() throws SQLException {
		
		String query = "Select * From question";
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
		
	}
	
	public void getPool(String name) throws SQLException {
		
		String query = "";
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		rs.close();
		
		stmt.close();
	}
	
	public void registerPub(String name, String address, int ownerid) throws SQLException {
		
		PubRepository pubRepository = null;
	    UserRepository userRepository = null;
		PubService pb = new PubService(pubRepository,userRepository);
		pb.createNewPub(name, address, Long.valueOf(ownerid));
		
	}
	
	
	public boolean searchUser(String name) throws SQLException {
		
		boolean check = true;
		
		String query = "Select username From user Where username=" + name;
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		int columns = rs.getMetaData().getColumnCount();
		
		while (rs.next()) {
			
			for(int i = 1; i<=columns; i++) {
				check = isEmpty(rs.getString(i));
			}
		}
		
		rs.close();
		stmt.close();
		
		
		return check;
	}
	
	
		public boolean searchPub(String name) throws SQLException {
		
		boolean check = true;
		
		String query = "Select name From pub Where name =" + name;
		
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		int columns = rs.getMetaData().getColumnCount();
		
		while (rs.next()) {
			
			for(int i = 1; i<=columns; i++) {
				check = isEmpty(rs.getString(i));
			}
		}
		
		rs.close();
		stmt.close();
		
		
		return check;
	}
		
		public int getLatestPubID () throws SQLException {
			int pubID = 0;
			
			String query = "Select Max(pubID) from pub";
			
			Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			for (;rs.next();) {
				
				pubID = rs.getInt(1);
			}
			
			return pubID;
		}
		
		public UserType getUserType (String name) throws SQLException {
			
			String user = "DEFAULT";
			
			UserType usertype;
			
			String query = "Select usertype from user Where email =" + name;
			
			Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			for (;rs.next();) {
				
				user = rs.getString(0);
			}
			
			usertype = UserType.valueOf(user);
			
			return usertype;
			
		}
		
		//setUserType nachträglich type ändern
}
