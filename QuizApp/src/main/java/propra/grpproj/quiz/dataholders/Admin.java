package propra.grpproj.quiz.dataholders;
/**
 * 
 * @author Daniel
 *
 */
public class Admin
{
	/**
	 * Primary Key
	 */
	private Long adminId;
	
	/**
	 * Foreign key for linking the tables
	 */
	private Long userId;

	public Admin(Long adminId, Long userId)
	{
		super();
		this.adminId = adminId;
		this.userId = userId;
	}

	@Override
	public String toString()
	{
		return "Admin [adminId=" + adminId + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminId == null) ? 0 : adminId.hashCode());
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
		Admin other = (Admin) obj;
		if (adminId == null) {
			if (other.adminId != null)
				return false;
		} else if (!adminId.equals(other.adminId))
			return false;
		return true;
	}

	public Long getAdminId()
	{
		return adminId;
	}

	public Long getUserId()
	{
		return userId;
	}	
	
}
