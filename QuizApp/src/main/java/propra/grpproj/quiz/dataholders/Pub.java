package propra.grpproj.quiz.dataholders;
/**
 * 
 * @author Daniel
 *
 */
public class Pub
{
	/**
	 * Primary key
	 */
	private Long pubId;
	
	/**
	 * Foreign key for linking the tables
	 */
	private Long userId;
	
	private boolean registered;
	private String name;
	
	public Pub(Long pubId, boolean registered, String name, Long userId)
	{
		super();
		this.pubId = pubId;
		this.registered = registered;
		this.name = name;
		this.userId = userId;
	}
	@Override
	public String toString()
	{
		return "Pub [pubId=" + pubId + ", registered=" + registered + ", name=" + name + ", userId=" + userId + "]";
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pubId == null) ? 0 : pubId.hashCode());
		result = prime * result + (registered ? 1231 : 1237);
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
		Pub other = (Pub) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pubId == null) {
			if (other.pubId != null)
				return false;
		} else if (!pubId.equals(other.pubId))
			return false;
		if (registered != other.registered)
			return false;
		return true;
	}
	public Long getPubId()
	{
		return pubId;
	}

	public boolean isRegistered()
	{
		return registered;
	}
	public void setRegistered(boolean registered)
	{
		this.registered = registered;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Long getUserId()
	{
		return userId;
	}
	
	
}
