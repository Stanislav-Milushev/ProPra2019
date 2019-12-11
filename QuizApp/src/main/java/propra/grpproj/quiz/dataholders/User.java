package propra.grpproj.quiz.dataholders;

/**
 * <p>
 * This class holds the information of one user.
 * <p>
 * A user can be referenced by {@link PlayerOfRound}.
 *
 */
public class User
{

    private final Long id;

    private final String username;
    private final String password;
    private final String email;

    /**
     * One of: ["admin", "barkeeper", "player"]
     */
    private final String role;

    public User(Long id, String username, String password, String email, String role)
    {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
                + role + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (id == null)
        {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getEmail()
    {
        return email;
    }

    public String getRole()
    {
        return role;
    }

}
