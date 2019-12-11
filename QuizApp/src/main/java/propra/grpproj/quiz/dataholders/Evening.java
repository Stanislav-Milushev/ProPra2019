package propra.grpproj.quiz.dataholders;

/**
 * <p>
 * This is a class holding the reference to the where and when!
 * <p>
 * On the other hand, the class {@link RoundsOfEvening} references this class,
 * so that we are able to associate players and questions and rounds to one
 * "KneipenAbend"!
 * 
 * @author Daniel
 * 
 */
public class Evening
{

    private final Long id;

    /**
     * The reference id to the pub where the event has happened
     */
    private final Long pubRefId;

    /**
     * Start date and time of the evening in ISO format. E.g.: 2019-12-24T22:00:00Z
     */
    private final String date;

    public Evening(Long id, Long pubRefId, String date)
    {
        super();
        this.id = id;
        this.pubRefId = pubRefId;
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "Evening [id=" + id + ", pubRefId=" + pubRefId + ", date=" + date + "]";
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
        Evening other = (Evening) obj;
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

    public Long getPubRefId()
    {
        return pubRefId;
    }

    public String getDate()
    {
        return date;
    }

}
