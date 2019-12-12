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
	public double getScore(String id,int KneipenabendID, double score) throws SQLException {
		
		
		DatabaseManager db = new DatabaseManager();
	
	    score = db.getScore(id,KneipenabendID,score);
	    
		return score;
		
		
	}
	
	
	
	// Write the points from a completed quiz to the user db
	public void writeToDB (String id,int KneipenabendID, double score) throws SQLException {
		
		DatabaseManager db = new DatabaseManager();
		db.writePoints(id, KneipenabendID,score);
	}
	
	
	// delete the current score globally, if a new month starts
	private void resetScore() throws SQLException{
		
		DatabaseManager db = new DatabaseManager();
		db.resetPoints();
		
		// ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		// scheduler.scheduleAtFixedRate(Scoreboard.resetScore(), 0, 1, TimeUnit.DAYS);
		
	}

}
