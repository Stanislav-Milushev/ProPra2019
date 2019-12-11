package propra.grpproj.quiz.dataholders;

/**
 * This class holds the information of a pub. Basically this is the owner aka
 * barkeeper and the name of the pub.
 */
public class Pub
{

    private final Long id;
    private final String name;

    /**
     * A reference key to the user-id in the users table for the owner of this pub
     */
    private final Long barkeeperUserRefId;

    /**
     * Is this barkeeper and this pub officially registered
     */
    private final boolean officiallyRegistered;

    public Pub(Long id, String name, Long barkeeperUserRefId, boolean officiallyRegistered)
    {
        super();
        this.id = id;
        this.name = name;
        this.barkeeperUserRefId = barkeeperUserRefId;
        this.officiallyRegistered = officiallyRegistered;
    }

    @Override
    public String toString()
    {
        return "Pub [id=" + id + ", name=" + name + ", barkeeperUserRefId=" + barkeeperUserRefId
                + ", officiallyRegistered=" + officiallyRegistered + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((barkeeperUserRefId == null) ? 0 : barkeeperUserRefId.hashCode());
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
        Pub other = (Pub) obj;
        if (barkeeperUserRefId == null)
        {
            if (other.barkeeperUserRefId != null)
                return false;
        } else if (!barkeeperUserRefId.equals(other.barkeeperUserRefId))
            return false;
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

    public String getName()
    {
        return name;
    }

    public Long getBarkeeperUserRefId()
    {
        return barkeeperUserRefId;
    }

    public boolean isOfficiallyRegistered()
    {
        return officiallyRegistered;
    }

}
