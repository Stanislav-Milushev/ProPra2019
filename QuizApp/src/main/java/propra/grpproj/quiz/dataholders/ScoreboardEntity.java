package propra.grpproj.quiz.dataholders;

/**
 * 
 * @author Daniel
 *
 */
public class ScoreboardEntity
{

	/**
	 * Primary key for persistence-technology management
	 */
	private Long scoreboardEntityId;
	
	/**
	 * Foreign key for linking the tables
	 */
	private Long pubEveningId;
	private Long userId;
	
	private String username;
	private int score;

	public ScoreboardEntity(Long id, String username, int score, Long userId, Long pubEveningId)
	{
		super();
		this.scoreboardEntityId = id;
		this.username = username;
		this.score = score;
		this.userId = userId;
		this.pubEveningId = pubEveningId;
	}

	@Override
	public String toString()
	{
		return "ScoreboardEntry [id=" + scoreboardEntityId + ", username=" + username + ", score=" + score + ", userId="
				+ userId + ", pubEveningId=" + pubEveningId + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((scoreboardEntityId == null) ? 0 : scoreboardEntityId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreboardEntity other = (ScoreboardEntity) obj;
		if (scoreboardEntityId == null) {
			if (other.scoreboardEntityId != null)
				return false;
		} else if (!scoreboardEntityId.equals(other.scoreboardEntityId))
			return false;
		return true;
	}

	public Long getId()
	{
		return scoreboardEntityId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public Long getUserId()
	{
		return userId;
	}

	public Long getPubEveningId()
	{
		return pubEveningId;
	}
}
