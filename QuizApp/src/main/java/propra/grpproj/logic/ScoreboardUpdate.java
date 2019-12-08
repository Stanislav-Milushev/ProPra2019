package propra.grpproj.logic;

import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
import java.time.LocalDateTime;


////////////////////////////////////////////////////////////////////////////
// Scoreboard functions
//
// @author: Marius Discher
//
//
//


public class ScoreboardUpdate {
	
	
	
	// Get the global and monthly reseted score from a user
	public double getScore(String id) throws SQLException {
		
		double score;
		DatabaseManager db = new DatabaseManager();
		db.connection();
		score = db.getScore(id);
		db.closeconnection();
		return score;
		
		
	}
	
	
	
	// Write the points from a completed quiz to the user db
	public void writeToDB (String id, float score) throws SQLException {
		
		DatabaseManager db = new DatabaseManager();
		db.connection();
		db.writePoints(id,score);
		db.closeconnection();
		
	}
	
	
	// delete the current score globally, if a new month starts
	private void resetScore() throws SQLException{
		
		DatabaseManager db = new DatabaseManager();
		
		db.connection();
		db.resetPoints();
		db.closeconnection();
		
		
		// ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		// scheduler.scheduleAtFixedRate(Scoreboard.resetScore(), 0, 1, TimeUnit.DAYS);
		
	}

}
