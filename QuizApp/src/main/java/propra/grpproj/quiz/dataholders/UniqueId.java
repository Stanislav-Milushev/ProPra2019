package propra.grpproj.quiz.dataholders;

/**
 * 
 * @author Daniel
 *
 */
public class UniqueId
{
	/**
	 * Primary Key
	 */
	private Long uniqueId;

	private String code;
	
	/**
	 * Foreign key for linking the tables
	 */
	private Long pubEveningId;
	private Long userId;

	public UniqueId(Long uniqueId, String code, Long pubEveningId, Long userId)
	{
		super();
		this.uniqueId = uniqueId;
		this.code = code;
		this.pubEveningId = pubEveningId;
		this.userId = userId;
	}

	@Override
	public String toString()
	{
		return "UniqueId [uniqueId=" + uniqueId + ", code=" + code + ", pubEveningId=" + pubEveningId + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
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
		UniqueId other = (UniqueId) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		return true;
	}

	public Long getUniqueId()
	{
		return uniqueId;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public Long getPubEveningId()
	{
		return pubEveningId;
	}

	public Long getUserId()
	{
		return userId;
	}

}
