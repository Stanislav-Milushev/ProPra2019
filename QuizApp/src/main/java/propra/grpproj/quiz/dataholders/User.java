package propra.grpproj.quiz.dataholders;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
/**
 * 
 * @author Daniel
 *
 */
public class User
{
    /**
     * Primary key
     */
    private Long userId;

    private String username;
    private String password;
    private String email;
    
    //private ScoreboardService scoreboardservice;
    
    

    public User(Long userId, String username, String password, String email)
    {
        super();

        Objects.requireNonNull(userId, "Id must not be null");
        Strings.isNotBlank(username);
        Strings.isNotBlank(password);
        Strings.isNotBlank(email);

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
//    public User(ScoreboardService scoreboardservice) {
//    	super();
//    	this.scoreboardservice = scoreboardservice;
//    }
    
//  public long findFreeId()
//  {
//      // @formatter:off
//      // (1) find all users from database
//      
//      // (2) sort result list by id ascending
//      // (3) take greatest id / last entry in sorted list
//      //     => find greatest
//
//      // (4) + 1 to that id
//
//      return  1 +
//              scoreboardservice
//                  .getScoreBoard()
//                  .stream()
//                  .mapToLong( scoreboardEntity ->{ return scoreboardEntity.getId(); })
//                  .max()
//                  .orElseThrow(NoSuchElementException::new);
//      // @formatter:on
//  }

    @Override
    public String toString()
    {
        return "User [userId=" + userId + ", username=" + username + ", "
        		+ "password=" + password + ", email=" + email + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        User other = (User) obj;
        if (userId == null)
        {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Long getUserId()
    {
        return userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

}
