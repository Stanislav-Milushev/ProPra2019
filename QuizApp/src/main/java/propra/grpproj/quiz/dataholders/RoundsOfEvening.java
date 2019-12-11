package propra.grpproj.quiz.dataholders;

/**
 * A class holding the reference-association between one round of one evening.
 */
public class RoundsOfEvening
{
    private final Long id;

    /**
     * The reference id to the evening when the event has happened
     */
    private final Long eveningRefId;

    /**
     * Pause time after this round. If this is the last round, set it to 0.
     */
    private final int pauseTime;

    /**
     * Password to enter this round as a player.
     */
    private final String password;

    public RoundsOfEvening(Long id, Long eveningRefId, int pauseTime, String password)
    {
        super();
        this.id = id;
        this.eveningRefId = eveningRefId;
        this.pauseTime = pauseTime;
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "RoundsOfEvening [id=" + id + ", eveningRefId=" + eveningRefId + ", pauseTime=" + pauseTime
                + ", password=" + password + "]";
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
        RoundsOfEvening other = (RoundsOfEvening) obj;
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

    public Long getEveningRefId()
    {
        return eveningRefId;
    }

    public int getPauseTime()
    {
        return pauseTime;
    }

    public String getPassword()
    {
        return password;
    }

}
