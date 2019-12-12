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
import propra.grpproj.quiz.services.UserService;
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
	
	// Function to register a user
	//usertype added
	public boolean registerUser(String username, String email, String passwd, UserType usertype) throws SQLException {
		
		UserRepository userRepository = null;
		UserService ub = new UserService(userRepository);
		ub.createNewUser(username, email, passwd, usertype);
		return ub.authenticate(email, passwd);
		
	}
	
	// Function to check, if result of a query is empty
	public boolean isEmpty(String value) {
		
		return value.length() == 0;
	}
	
	
	
	// Function to authenticate login of a user
	public boolean login(String email, String password) throws SQLException {
		return false;
		
		
	
	}
	
	// Function to handle the approve of a pub
	public void approvePub(String name, String owner) throws SQLException {
		
		PubRepository pubRepository = null;
	    UserRepository userRepository = null;
		PubService pb = new PubService(pubRepository,userRepository);
		pb.acceptPub(name, owner);
		
		
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
	
	
	
	
	public void resetPoints() throws SQLException {
		
		double score = 0.0;
		
		String query = "Update user value score = ?";
		
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setDouble(1, score);
		
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
		public boolean deleteUser (String username, String passwd) throws SQLException 
		{
			UserRepository userRepository = null;
			UserService ub = new UserService(userRepository);
			ub.deleteUser(username, passwd);
			return false ;//ub.authenticate(name, passwd);
			
		}


		
		
		//setUserType nachträglich type ändern
}
