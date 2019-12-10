package propra.grpproj.quiz.dataholders;

/**
 * 
 * @author Daniel
 *
 */
public class QuestionRound
{
	/**
	 * Primary Key
	 */
	private Long roundId;
	
	/**
	 * Foreign key for linking the tables
	 */
	private Long pubEveningId;
	private int pause;

	public QuestionRound(Long roundId, int pause, Long pubEveningId)
	{
		super();
		this.roundId = roundId;
		this.pause = pause;
		this.pubEveningId = pubEveningId;
	}

	@Override
	public String toString()
	{
		return "QuestionRound [roundId=" + roundId + ", pause=" + pause + ", pubEveningId=" + pubEveningId + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + pause;
		result = prime * result + ((roundId == null) ? 0 : roundId.hashCode());
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
		QuestionRound other = (QuestionRound) obj;
		if (pause != other.pause)
			return false;
		if (roundId == null) {
			if (other.roundId != null)
				return false;
		} else if (!roundId.equals(other.roundId))
			return false;
		return true;
	}

	public Long getRoundId()
	{
		return roundId;
	}

	public int getPause()
	{
		return pause;
	}

	public void setPause(int pause)
	{
		this.pause = pause;
	}

	public Long getPubEveningId()
	{
		return pubEveningId;
	}

}
