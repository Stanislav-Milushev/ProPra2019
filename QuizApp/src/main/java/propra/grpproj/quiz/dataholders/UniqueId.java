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
	// FK private Long pubEveningId;
	// FK private Long userId;

	public UniqueId(Long uniqueId, String code)
	{
		super();
		this.uniqueId = uniqueId;
		this.code = code;
	}

	@Override
	public String toString()
	{
		return "UniqueId [uniqueId=" + uniqueId + ", code=" + code + "]";
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

}
