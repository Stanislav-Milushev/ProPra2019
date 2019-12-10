package propra.grpproj.quiz.dataholders;
/**
 * 
 * @author Daniel
 *
 */
public class PubEvening
{
	/**
	 * Primary Key
	 */
	private Long pubEveningId;
	
	/**
	 * Foreign key for linking the tables
	 */
	private Long pubId;
	
	private int timeForAnswering;
	
	public PubEvening(Long pubEveningId, int timeForAnswering, Long pubId)
	{
		super();
		this.pubEveningId = pubEveningId;
		this.timeForAnswering = timeForAnswering;
		this.pubId = pubId;
	}

	@Override
	public String toString()
	{
		return "PubEvening [pubEveningId=" + pubEveningId + ", timeForAnswering=" + timeForAnswering + ", pubId=" + pubId + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pubEveningId == null) ? 0 : pubEveningId.hashCode());
		result = prime * result + timeForAnswering;
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
		PubEvening other = (PubEvening) obj;
		if (pubEveningId == null) {
			if (other.pubEveningId != null)
				return false;
		} else if (!pubEveningId.equals(other.pubEveningId))
			return false;
		if (timeForAnswering != other.timeForAnswering)
			return false;
		return true;
	}

	public int getTimeForAnswering()
	{
		return timeForAnswering;
	}

	public void setTimeForAnswering(int timeForAnswering)
	{
		this.timeForAnswering = timeForAnswering;
	}

	public Long getPubEveningId()
	{
		return pubEveningId;
	}

	public Long getPubId()
	{
		return pubId;
	}
}
